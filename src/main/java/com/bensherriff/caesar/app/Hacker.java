package com.bensherriff.caesar.app;

import com.bensherriff.caesar.data.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Hacker {

    private static final Logger LOGGER = LogManager.getLogger(Hacker.class.getName());

    private static final Timer timer = new Timer();

    private static Hacker hacker;
    private Map<String, Double> wordFrequencyMap;
    private Map<Character, Double> monogramFrequencyMap;
    private Map<String, Double> bigramFrequencyMap;
    private Map<String, Double> wordsMap;

    private Hacker() {
        // Static Instance
    }

    public static Hacker getInstance() {
        if (Objects.isNull(hacker)) {
            hacker = new Hacker();
        }
        return hacker;
    }

    public void setData(Data data) {
        wordFrequencyMap = Util.buildMap(data.getWordFrequency());
        monogramFrequencyMap = Util.sortGreatestToLowest(Util.buildMap(data.getMonogramFrequency(), Character.class));
        bigramFrequencyMap = Util.buildMap(data.getBigramFrequency());
        wordsMap = Util.buildMap(data.getWords());
    }

    public Guess<Integer, Double> bruteForceHack(String message) {
        timer.start();
        Map<Integer, Double> map = new HashMap<>(26);
        double totalCertainty = 0;
        for (int key = 0; key < 26; key++) {
            map.put(key, 0d);
            String stringGuess = Util.transform(message, -key).toLowerCase();
            List<String> words = new ArrayList<>(Arrays.asList(stringGuess.split("\\W++")));
            for (String word : words) {
                if (wordFrequencyMap.containsKey(word)) {
                    LOGGER.debug("Found word '" + word + "' using key: " + key);
                    map.put(key, map.get(key) + 1d);
                    totalCertainty++;
                }
            }
        }
        // Key used to encrypt
        Map.Entry<Integer, Double> bestGuess = Util.MaxValue(map);
        Guess<Integer, Double> guess = new Guess(bestGuess.getKey(), ((totalCertainty > 0) ? bestGuess.getValue()/totalCertainty : -1d));
        timer.stop();
        LOGGER.debug("Brute Force key '" + guess.key() + "' with certainty " + guess.certaintyPercent() + " - " + timer.output());
        return guess;
    }

    /**
     *
     * @param message
     * @return The best guess at the key.
     */
    public Guess<Integer, Double> monogramHack(String message) {
        timer.start();
        Guess<Integer, Double> guess;
        Map<Character, Double> map = Util.sortGreatestToLowest(Util.convertToFrequencyMap(message));
        // If word size is sufficiently small enough, set the most frequent letter in the message to be
        // the most frequent letter in the monogramFrequencyMap
        Map.Entry<Character, Double> maxEntry = Util.MaxValue(map);
        if ((message.length() <= 100 && maxEntry.getValue() >= 15) || (maxEntry.getValue() >= 30)) {
            Character c2 = Util.MaxValue(monogramFrequencyMap).getKey();
            timer.stop();
            guess = new Guess<>(Util.distanceBetweenCharacters(maxEntry.getKey(), c2), monogramFrequencyMap.get(c2) / 100);
            LOGGER.debug("Monogram key '" + guess.key() + "' with certainty " + guess.certaintyPercent() + " - " + timer.output());
            return guess;
        }
        Map<Integer, Integer> guessMap = new HashMap<>(26);
        double totalCertainty = 0;
        for (Map.Entry<Character, Double> inputEntry : map.entrySet()) {
            for (Map.Entry<Character, Double> entry : monogramFrequencyMap.entrySet()) {
                Character c1 = inputEntry.getKey();
                Character c2 = entry.getKey();
                int currentGuess = Util.distanceBetweenCharacters(c1, c2);
                LOGGER.debug(currentGuess + " for " + c1 + " and " + c2);
                guessMap.putIfAbsent(currentGuess, 0);
                guessMap.put(currentGuess, guessMap.get(currentGuess) + 1);
                totalCertainty++;
            }
        }
        Map.Entry<Integer, Integer> bestGuess = Collections.max(guessMap.entrySet(), Map.Entry.comparingByValue());
        guess = new Guess<>(bestGuess.getKey(), ((totalCertainty > 0) ? bestGuess.getValue().doubleValue()/totalCertainty : 0d));
        timer.stop();
        LOGGER.debug("Monogram key '" + guess.key() + "' with certainty " + guess.certaintyPercent() + " - " + timer.output());
        return guess;
    }

    //    public int triGramHack(String message) {
//        Map<String, Double> map = new HashMap<>();
//        int totalWords = 0;
//        String[] words = message.split("\\W+");
//        Pattern pattern = Pattern.compile("\\b[A-Za-z]{3}\\b");
//        for (String word : words) {
//            Matcher m = pattern.matcher(word);
//            if (m.find()) {
//                map.putIfAbsent(word, 0.0);
//                map.replace(word, map.get(word) + 1.0);
//                totalWords++;
//            }
//        }
//        for (Map.Entry<String, Double> entry : map.entrySet()) {
//            entry.setValue((entry.getValue() / totalWords)*100);
//        }
////        map = this.frequency.sortWords(map);
//        return guessWord(map);
//    }

//    private int guessCharacters(Map<Character, Double> map) {
//        Map<Integer, Integer> keyGuess = new HashMap<>();
////        Iterator<Map.Entry<Character, Double>> frequencyEntry = frequency.getMap().entrySet().iterator();
////        for (Map.Entry<Character, Double> mapEntry : map.entrySet()) {
////            int currentKeyGuess = Math.abs((int) mapEntry.getKey() - frequencyEntry.next().getKey());
////            keyGuess.putIfAbsent(currentKeyGuess, 0);
////            keyGuess.replace(currentKeyGuess, keyGuess.get(currentKeyGuess) + 1);
////            LOGGER.debug(currentKeyGuess + ": " + keyGuess.get(currentKeyGuess));
////        }
////        return frequency.sortIntegers(keyGuess).entrySet().iterator().next().getKey();
//        return 0;
//    }

//    private int guessWord(Map<String, Double> map) {
//        Map.Entry<String, Double> mapEntry = map.entrySet().iterator().next();
//        int keyGuess1 =  Math.abs((int) mapEntry.getKey().charAt(0) - 't');
//        int keyGuess2 =  Math.abs((int) mapEntry.getKey().charAt(1) - 'h');
//        int keyGuess3 =  Math.abs((int) mapEntry.getKey().charAt(2) - 'e');
//        if (keyGuess1 == keyGuess2) {
//            return keyGuess1;
//        } else {
//            return 0;
//        }
//    }
}
