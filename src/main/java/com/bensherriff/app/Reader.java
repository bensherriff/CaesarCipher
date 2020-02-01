package com.bensherriff.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class Reader {

    private final static Logger logger = LogManager.getLogger(Reader.class.getName());

    private static Properties properties;

    public static Properties getProperties() {
        return properties;
    }
    public static void loadProperties(String filename) throws IOException {
        logger.debug("Properties loaded from: " + filename);
        properties = new Properties();
        try (InputStream input = Reader.class.getClassLoader().getResourceAsStream(filename)) {
            assert input != null;
            properties.load(input);
        }
    }

    public static String getProperty(String property) {
        return properties.getProperty(property);
    }

}
