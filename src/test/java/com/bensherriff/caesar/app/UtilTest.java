package com.bensherriff.caesar.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import java.io.IOException;

public class UtilTest {

    @Test
    public void loadPropertiesTest() throws IOException {
        Util.loadProperties("project.properties");
        assertEquals(Util.getProperty("cipher.threshold"), "1000");
    }
}
