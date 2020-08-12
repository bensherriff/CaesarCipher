package com.bensherriff.caesar.app;

import com.bensherriff.caesar.data.DataEntry;
import com.bensherriff.caesar.data.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Util {

    private static final Logger LOGGER = LogManager.getLogger(Util.class.getName());

    public static final String SEPARATOR = File.separator;

    private static Properties properties;

    private Util() {
        // Static Class
    }

    public static void loadProperties(String fileName) throws IOException {
        LOGGER.debug("Loading properties from " + fileName);
        properties = new Properties();
        try (InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(fileName)) {
            if (Objects.nonNull(inputStream)) {
                properties.load(inputStream);
            } else {
                LOGGER.error("Failed to read from file " + fileName);
            }
        }
    }

    public static String getProperty(String property) {
        return properties.getProperty(property);
    }

//    public static String readFromFile(String fileName) throws IOException {
//        LOGGER.debug("Reading from file " + fileName);
//        try (InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(fileName)) {
//            if (Objects.nonNull(inputStream)) {
//                return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
//                    .lines()
//                    .collect(Collectors.joining("\n"));
//            } else {
//                LOGGER.error("Failed to read from file " + fileName);
//                return "";
//            }
//        }
//    }

    public static void writeToFile(String string, String fileName) {

    }

    public static String serialize(Object object) throws JAXBException {
        if (Objects.nonNull(object)) {
            StringWriter stringWriter = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(object, stringWriter);
            return stringWriter.toString();
        } else {
            LOGGER.warn("Object is null, returning empty String");
            return "";
        }
    }

    public static Object deserialize(String fileName, Class<?> c) throws JAXBException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        LOGGER.debug("Deserializing from " + fileName + " to " + c.getCanonicalName());
        if (Objects.nonNull(fileName)) {
            File file = new File(Objects.requireNonNull(Util.class.getClassLoader().getResource(fileName)).getFile());
            JAXBContext jaxbContext = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return c.cast(unmarshaller.unmarshal(file));
        } else {
            LOGGER.warn("String is null or empty, returning new " + c.getCanonicalName() + " object");
            return c.getDeclaredConstructor().newInstance();
        }
    }

//    public static Object deserialize(String string, Class<?> c) throws JAXBException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        if (Objects.nonNull(string) && !string.equals("")) {
//            JAXBContext jaxbContext = JAXBContext.newInstance(c);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//            return c.cast(unmarshaller.unmarshal(new StreamSource(string)));
//        } else {
//            LOGGER.warn("String is null or empty, returning new " + c.getCanonicalName() + " object");
//            return c.getDeclaredConstructor().newInstance();
//        }
//    }

    /**
     * Ensure that the key can only be shifted by the amount of letters in the English alphabet.
     * @param key The key used to shift characters.
     * @return The validated key within the 26 possible options.
     */
    public static int validate(int key) {
        return Math.abs(key % 26);
    }

    /**
     * Shift the characters in the message by the key value
     * @param message
     * @param key
     * @return
     */
    public static String transform(String message, int key) {
        char[] result = new char[message.length()];
        for (int i = 0; i < message.length(); i++) {
            result[i] = transform(message.charAt(i), key);
        }
        return new String(result);
    }

    /**
     * Shift the character over by the key value to a new alphabet character.
     * @param letter The letter to be transformed.
     * @param key The number of characters to shift the letter by.
     * @return The new transformed letter, shifted by the key value.
     */
    public static char transform(char letter, int key) {
        if (Character.isLowerCase(letter)) {
            int temp = ((int) letter - 97 + key);
            if (temp < 0) {
                temp = (26 + temp)%26 + 97;
            } else {
                temp = temp%26 + 97;
            }
            return (char) temp;
        } else if (Character.isUpperCase(letter)) {
            int temp = ((int) letter - 65 + key);
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

    public static int distanceBetweenCharacters(Character c1, Character c2) {
        return Math.abs((int) c1 - (int) c2);
    }

    public static Map<String, Double> buildMap(Table frequency) {
        return buildMap(frequency, String.class);
    }

    public static <T> Map<T, Double> buildMap(Table frequency, Class<T> c) {
        Map<T, Double> map = new HashMap<>();
        for (DataEntry dataEntry : frequency.getEntry()) {
            map.put(convertInstanceOfObject(dataEntry.getName(), c), dataEntry.getValue());
        }
        return map;
    }

    public static <T> T convertInstanceOfObject(Object object, Class<T> c) {
        if (String.class.isAssignableFrom(object.getClass()) && Character.class.isAssignableFrom(c)) {
            if (String.valueOf(object).length() == 1) {
                return c.cast(String.valueOf(object).charAt(0));
            } else {
                LOGGER.warn("Assigning value to first letter of string '" + object + "'");
            }
        } else {
            try {
                return c.cast(object);
            } catch (ClassCastException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return null;
    }

    public static <T> Map<T, Double> sortGreatestToLowest(Map<T, Double> map) {
        LinkedHashMap<T, Double> reverseSortedMap = new LinkedHashMap<>();

        //Use Comparator.reverseOrder() for reverse ordering
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        return reverseSortedMap;
    }

    public static <T> Map.Entry<T, Double> MaxValue(Map<T, Double> map) {
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue());
    }

    /**
     * Convert a message into a map of characters with the frequency they appear.
     * @param message Message to be converted.
     * @return Map of (character, frequency) pairs
     */
    public static Map<Character, Double> convertToFrequencyMap(String message) {
        Map<Character, Double> map = new HashMap<>();
        double totalCharacters = 0;

        for (char character : message.toCharArray()) {
            Character key = Character.toLowerCase(character);
            if (Character.isAlphabetic(key)) {
                map.putIfAbsent(key, 0d);
                totalCharacters++;
                map.put(key, map.get(key) + 1d);
            }
        }

        for (Map.Entry<Character, Double> entry : map.entrySet()) {
            entry.setValue((entry.getValue() / ((totalCharacters == 0) ? 1d : totalCharacters)) * 100);
        }
        return map;
    }
}
