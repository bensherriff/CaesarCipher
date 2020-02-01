package com.bensherriff.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cipher {

    private final static Logger logger = LogManager.getLogger(Cipher.class.getName());

    /**
     *
     * @param msg - Input plaintext string
     * @param shiftKey - Shift value to encrypt string with
     * @return - Encrypted string
     */
    public static String encrypt(String msg, int shiftKey) {
        logger.info("Encrypting message with key: " + shiftKey);
        char[] result = new char[msg.length()];
        shiftKey = validate(shiftKey);

        for (int i = 0; i < msg.length(); i++) {
            result[i] = transform(msg.charAt(i), shiftKey);
        }
        return new String(result);
    }

    public static String decrypt(String msg, int shiftKey) {
        logger.info("Decrypting message with key: " + shiftKey);
        char[] result = new char[msg.length()];
        shiftKey = -validate(shiftKey);

        for (int i = 0; i < msg.length(); i++) {
            result[i] = transform(msg.charAt(i), shiftKey);
        }
        return new String(result);
    }

    public static String hack(String msg) {
        logger.info("Hacking message");
        Hacker hacker = new Hacker();
        int key = hacker.monogramHack(msg);
        return decrypt(msg, key);
    }

    private static int validate(int shiftKey) {
        return Math.abs(shiftKey % 26);
    }

    private static char transform(char letter, int shiftKey) {
        if (Character.isLowerCase(letter)) {
            int temp = ((int) letter - 97 + shiftKey);
            if (temp < 0) {
                temp = (26 + temp)%26 + 97;
            } else {
                temp = temp%26 + 97;
            }
            return (char) temp;
        } else if (Character.isUpperCase(letter)) {
            int temp = ((int) letter - 65 + shiftKey);
            if (temp < 0) {
                temp = (26 + temp)%26 + 65;
            } else {
                temp = temp%26 + 65;
            }
            return (char) temp;
        } else {
            return letter;
        }
    }
}
