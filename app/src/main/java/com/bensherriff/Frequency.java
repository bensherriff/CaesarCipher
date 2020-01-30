package com.bensherriff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Frequency {
    private final static Logger logger = LogManager.getLogger(Frequency.class);
    private Map<String, Double> map;

    public Map<String, Double> getMap() {
        return map;
    }

    public void setMap(Map<String, Double> map) {
        this.map = map;
    }

    void init() {
        logger.info("Initializing frequency");
        this.map = new HashMap<>();
        this.map.put("a", 8.12);
        this.map.put("b", 1.49);
        this.map.put("c", 2.71);
        this.map.put("d", 4.32);
        this.map.put("e", 12.02);
        this.map.put("f", 2.30);
        this.map.put("g", 2.03);
        this.map.put("h", 5.92);
        this.map.put("i", 7.31);
        this.map.put("j", 0.10);
        this.map.put("k", 0.69);
        this.map.put("l", 3.98);
        this.map.put("m", 2.61);
        this.map.put("n", 6.95);
        this.map.put("o", 7.68);
        this.map.put("p", 1.82);
        this.map.put("q", 0.11);
        this.map.put("r", 6.02);
        this.map.put("s", 6.28);
        this.map.put("t", 9.10);
        this.map.put("u", 2.88);
        this.map.put("v", 7.31);
        this.map.put("w", 2.09);
        this.map.put("x", 0.17);
        this.map.put("y", 2.11);
        this.map.put("z", 0.07);
    }
}
