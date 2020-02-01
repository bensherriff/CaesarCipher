package com.bensherriff.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Frequency {
    private final static Logger logger = LogManager.getLogger(Frequency.class);
    private Map<Character, Double> map;

    public Map<Character, Double> getMap() {
        if (map == null) {
            init();
        }
        return map;
    }

    public void setMap(Map<Character, Double> map) {
        this.map = map;
    }

    public Map<Character, Double> sort(Map<Character, Double> map) {
        //LinkedHashMap preserve the ordering of elements in which they are inserted
        LinkedHashMap<Character, Double> reverseSortedMap = new LinkedHashMap<>();

//Use Comparator.reverseOrder() for reverse ordering
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        return reverseSortedMap;
    }

    private void init() {
        logger.info("Initializing frequency");
        this.map = new HashMap<>();
        // Data sourced from
        // https://en.wikipedia.org/wiki/Letter_frequency#Relative_frequencies_of_letters_in_the_English_language
        this.map.put('a', 8.167);
        this.map.put('b', 1.492);
        this.map.put('c', 2.782);
        this.map.put('d', 4.253);
        this.map.put('e', 12.702);
        this.map.put('f', 2.228);
        this.map.put('g', 2.015);
        this.map.put('h', 6.094);
        this.map.put('i', 6.966);
        this.map.put('j', 0.153);
        this.map.put('k', 0.772);
        this.map.put('l', 4.025);
        this.map.put('m', 2.406);
        this.map.put('n', 6.749);
        this.map.put('o', 7.507);
        this.map.put('p', 1.929);
        this.map.put('q', 0.095);
        this.map.put('r', 5.987);
        this.map.put('s', 6.327);
        this.map.put('t', 9.056);
        this.map.put('u', 2.758);
        this.map.put('v', 0.978);
        this.map.put('w', 2.360);
        this.map.put('x', 0.150);
        this.map.put('y', 1.974);
        this.map.put('z', 0.077);
        this.map = sort(this.map);
    }
}
