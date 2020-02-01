package com.bensherriff.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hacker {

    private final static Logger logger = LogManager.getLogger(Hacker.class.getName());

    private Frequency frequency = new Frequency();

    public void bruteForceHack() {

    }

    public int monogramHack(String msg) {
        Map<Character, Double> map = convertToFrequencyMap(msg);
        return guessCharacter(map);
    }

    public int triGramHack(String msg) {
        Map<String, Double> map = new HashMap<>();
        int totalWords = 0;
        String[] words = msg.split("\\W+");
        Pattern pattern = Pattern.compile("\\b[A-Za-z]{3}\\b");
        for (String word : words) {
            Matcher m = pattern.matcher(word);
            if (m.find()) {
                map.putIfAbsent(word, 0.0);
                map.replace(word, map.get(word) + 1.0);
                totalWords++;
            }
        }
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            entry.setValue((entry.getValue() / totalWords)*100);
        }
        map = this.frequency.sortWords(map);
        return guessWord(map);
    }

    private Map<Character, Double> convertToFrequencyMap(String msg) {
        Map<Character, Double> map = new HashMap<>();
        int totalCharacters = 0;

        for (int i = 0; i < msg.length(); i++) {
            if (Character.isAlphabetic(msg.charAt(i))) {
                Double currentValue = map.getOrDefault(msg.charAt(i), 0.0);
                map.put(Character.toLowerCase(msg.charAt(i)), currentValue + 1.0);
                totalCharacters++;
            }
        }
        for (Map.Entry<Character, Double> entry : map.entrySet()) {
            entry.setValue((entry.getValue() / totalCharacters)*100);
        }
        return this.frequency.sortCharacters(map);
    }

    private int guessCharacter(Map<Character, Double> map) {
        Map.Entry<Character, Double> mapEntry = map.entrySet().iterator().next();
        Map.Entry<Character, Double> frequencyEntry = frequency.getMap().entrySet().iterator().next();
        return Math.abs((int) mapEntry.getKey() - frequencyEntry.getKey());
    }

    private int guessCharacters(Map<Character, Double> map) {
        Map<Integer, Integer> keyGuess = new HashMap<>();
        Iterator<Map.Entry<Character, Double>> frequencyEntry = frequency.getMap().entrySet().iterator();
        for (Map.Entry<Character, Double> mapEntry : map.entrySet()) {
            int currentKeyGuess = Math.abs((int) mapEntry.getKey() - frequencyEntry.next().getKey());
            keyGuess.putIfAbsent(currentKeyGuess, 0);
            keyGuess.replace(currentKeyGuess, keyGuess.get(currentKeyGuess) + 1);
            logger.debug(currentKeyGuess + ": " + keyGuess.get(currentKeyGuess));
        }
        return frequency.sortIntegers(keyGuess).entrySet().iterator().next().getKey();
    }

    private int guessWord(Map<String, Double> map) {
        Map.Entry<String, Double> mapEntry = map.entrySet().iterator().next();
        int keyGuess1 =  Math.abs((int) mapEntry.getKey().charAt(0) - 't');
        int keyGuess2 =  Math.abs((int) mapEntry.getKey().charAt(1) - 'h');
        int keyGuess3 =  Math.abs((int) mapEntry.getKey().charAt(2) - 'e');
        if (keyGuess1 == keyGuess2) {
            return keyGuess1;
        } else {
            return 0;
        }
    }
}
