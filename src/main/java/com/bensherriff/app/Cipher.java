package com.bensherriff.app;

import com.bensherriff.app.Frequency;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Cipher {

    private final static Logger logger = LogManager.getLogger(Cipher.class.getName());

    /**
     *
     * @param msg - Input plaintext string
     * @param shiftKey - Shift value to encrypt string with
     * @return - Encrypted string
     */
    public static String encrypt(String msg, int shiftKey) {
//        logger.info("Encrypting: " + msg);
        char[] result = new char[msg.length()];
        shiftKey = validate(shiftKey);

        for (int i = 0; i < msg.length(); i++) {
            result[i] = transform(msg.charAt(i), shiftKey);
        }
        return new String(result);
    }

    public static String decrypt(String msg, int shiftKey) {
//        logger.info("Decrypting: " + msg);
        char[] result = new char[msg.length()];
        shiftKey = -validate(shiftKey);

        for (int i = 0; i < msg.length(); i++) {
            result[i] = transform(msg.charAt(i), shiftKey);
        }
        return new String(result);
    }

    public static String crack(String msg) {
//        logger.info("Cracking: " + msg);
        int totalCharacters = 0;
        Map<Character, Double> characters = new HashMap<>();
        Frequency frequency = new Frequency();
        for (int i = 0; i < msg.length(); i++) {
            if (Character.isAlphabetic(msg.charAt(i))) {
                Double currentValue = characters.getOrDefault(msg.charAt(i), 0.0);
                characters.put(Character.toLowerCase(msg.charAt(i)), currentValue + 1.0);
                totalCharacters++;
            }
        }
        for (Map.Entry<Character, Double> entry : characters.entrySet()) {
            entry.setValue((entry.getValue() / totalCharacters)*100);
//            logger.info(entry.getKey() + " " + entry.getValue());
        }

        characters = frequency.sort(characters);
        Iterator<Map.Entry<Character, Double>> frequencyIterator = frequency.getMap().entrySet().iterator();
        Map<Character, Character> result = new HashMap<>();
        for (Map.Entry<Character, Double> entry : characters.entrySet()) {
            Map.Entry<Character,Double> frequencyEntry = frequencyIterator.next();
            result.put(entry.getKey(), frequencyEntry.getKey());
            logger.info(entry.getKey() + " with " + frequencyEntry.getKey());
            logger.info(entry.getKey() + " with " + decrypt(entry.getKey().toString(), 1));
            logger.info("");
        }
        StringBuilder sb = new StringBuilder(msg);
        for (int i = 0; i < msg.length(); i++) {
            if (Character.isLowerCase(msg.charAt(i))) {
//                logger.info("Replacing " + msg.charAt(i) + " with " + result.get(msg.charAt(i)));
                sb.setCharAt(i, result.get(msg.charAt(i)));
            } else if (Character.isUpperCase(msg.charAt(i))) {
//                logger.info("Replacing " + msg.charAt(i) + " with " + Character.toUpperCase(result.get(Character.toLowerCase(msg.charAt(i)))));
                sb.setCharAt(i, Character.toUpperCase(Character.toUpperCase(result.get(Character.toLowerCase(msg.charAt(i))))));
            }
        }

        return sb.toString();
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
