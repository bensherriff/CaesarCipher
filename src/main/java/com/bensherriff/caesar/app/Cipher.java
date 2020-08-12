package com.bensherriff.caesar.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static com.bensherriff.caesar.app.Util.transform;
import static com.bensherriff.caesar.app.Util.validate;

public class Cipher {

    private static final Logger LOGGER = LogManager.getLogger(Cipher.class.getName());

    private static final Hacker hacker = Hacker.getInstance();

    private Cipher() {
        // Static Instance
    }

    /**
     * Encrypt a message with a given key. Shifts the characters over by the key value.
     * @param message Plaintext message to be encrypted.
     * @param key Value used shift the message charact ers and encrypt the message.
     * @return - The encrypted string.
     */
    public static String encrypt(String message, int key) {
        LOGGER.info("Encrypting the message with key: " + key);
        return transform(message, validate(key));
    }

    /**
     * Decrypt a message with a given key.
     * @param message Encrypted message to be decrypted.
     * @param key Value used to shift the message characters and decrypt the message.
     * @return The decrypted string.
     */
    public static String decrypt(String message, int key) {
        LOGGER.info("Decrypting the message with key: " + key);
        return transform(message, -validate(key));
    }

    /**
     * Make educated and calculated guesses to crack the encrypted message. The longer the message, the higher the
     * chance at accurately decrypting the message.
     * @param message The encrypted message to be hacked.
     * @return The best guess at the decrypted message.
     */
    public static String crack(String message) {
        LOGGER.info("Attempting to crack the message");
        Guess<Integer, Double> guess = null;

        // If message is simple enough, attempt a brute force
        if (message.length() <= 100 || Util.MaxValue(Util.convertToFrequencyMap(message)).getValue() >= 15) {
            guess = hacker.bruteForceHack(message);
        }

        if (Objects.nonNull(guess) && guess.certainty() > 0.5) {
            // Do Nothing
        }
        if (Objects.nonNull(guess) && guess.certainty() <= 0.5) {
            Guess<Integer, Double> monogramHack = hacker.monogramHack(message);
            guess = guess.compareTo(monogramHack);
        } else {
            Guess<Integer, Double> bruteForce = hacker.bruteForceHack(message);
            Guess<Integer, Double> monogramHack = hacker.monogramHack(message);
            guess = bruteForce.compareTo(monogramHack);
        }
        LOGGER.debug("Guessing with key " + guess.key() + " (" + guess.certaintyPercent() + "%)");
        return decrypt(message, guess.key());
    }
}
