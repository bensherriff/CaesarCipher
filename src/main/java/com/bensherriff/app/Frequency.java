package com.bensherriff.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Frequency {
    private final static Logger logger = LogManager.getLogger(Frequency.class);
    private Map<Character, Double> map;

    public Frequency() {
        map = new HashMap<>();
        map.put('a', Double.parseDouble(Reader.getProperty("frequency.mono.a")));
        map.put('b', Double.parseDouble(Reader.getProperty("frequency.mono.b")));
        map.put('c', Double.parseDouble(Reader.getProperty("frequency.mono.c")));
        map.put('d', Double.parseDouble(Reader.getProperty("frequency.mono.d")));
        map.put('e', Double.parseDouble(Reader.getProperty("frequency.mono.e")));
        map.put('f', Double.parseDouble(Reader.getProperty("frequency.mono.f")));
        map.put('g', Double.parseDouble(Reader.getProperty("frequency.mono.g")));
        map.put('h', Double.parseDouble(Reader.getProperty("frequency.mono.h")));
        map.put('i', Double.parseDouble(Reader.getProperty("frequency.mono.i")));
        map.put('j', Double.parseDouble(Reader.getProperty("frequency.mono.j")));
        map.put('k', Double.parseDouble(Reader.getProperty("frequency.mono.k")));
        map.put('l', Double.parseDouble(Reader.getProperty("frequency.mono.l")));
        map.put('m', Double.parseDouble(Reader.getProperty("frequency.mono.m")));
        map.put('n', Double.parseDouble(Reader.getProperty("frequency.mono.n")));
        map.put('o', Double.parseDouble(Reader.getProperty("frequency.mono.o")));
        map.put('q', Double.parseDouble(Reader.getProperty("frequency.mono.p")));
        map.put('r', Double.parseDouble(Reader.getProperty("frequency.mono.q")));
        map.put('p', Double.parseDouble(Reader.getProperty("frequency.mono.r")));
        map.put('s', Double.parseDouble(Reader.getProperty("frequency.mono.s")));
        map.put('t', Double.parseDouble(Reader.getProperty("frequency.mono.t")));
        map.put('u', Double.parseDouble(Reader.getProperty("frequency.mono.u")));
        map.put('v', Double.parseDouble(Reader.getProperty("frequency.mono.v")));
        map.put('w', Double.parseDouble(Reader.getProperty("frequency.mono.w")));
        map.put('x', Double.parseDouble(Reader.getProperty("frequency.mono.x")));
        map.put('y', Double.parseDouble(Reader.getProperty("frequency.mono.y")));
        map.put('z', Double.parseDouble(Reader.getProperty("frequency.mono.z")));
        map = sortCharacters(map);
    }

    public Map<Character, Double> getMap() {
        return map;
    }

    public void setMap(Map<Character, Double> map) {
        this.map = map;
    }

    public Map<Character, Double> sortCharacters(Map<Character, Double> map) {
        //LinkedHashMap preserve the ordering of elements in which they are inserted
        LinkedHashMap<Character, Double> reverseSortedMap = new LinkedHashMap<>();

        //Use Comparator.reverseOrder() for reverse ordering
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        return reverseSortedMap;
    }

    public Map<Integer, Integer> sortIntegers(Map<Integer, Integer> map) {
        //LinkedHashMap preserve the ordering of elements in which they are inserted
        LinkedHashMap<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();

        //Use Comparator.reverseOrder() for reverse ordering
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        return reverseSortedMap;
    }

    public Map<String, Double> sortWords(Map<String, Double> map) {
        //LinkedHashMap preserve the ordering of elements in which they are inserted
        LinkedHashMap<String, Double> reverseSortedMap = new LinkedHashMap<>();

        //Use Comparator.reverseOrder() for reverse ordering
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        return reverseSortedMap;
    }
}
