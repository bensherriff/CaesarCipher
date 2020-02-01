package com.bensherriff.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import java.io.IOException;

public class ReaderTest {

    @Test
    public void loadPropertiesTest() throws IOException {
        Reader.loadProperties("data.properties");
        assertEquals(Reader.getProperty("cipher.threshold"), "1000");
    }
}
