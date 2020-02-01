package com.bensherriff.app;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class CipherTest {

    @Test
    void encryptTest() {
        assertEquals("yjxy", Cipher.encrypt("test", 5));
        assertEquals("yjxy", Cipher.encrypt("test", -5));
        assertEquals("test", Cipher.encrypt("test", 0));
        assertEquals("test", Cipher.encrypt("test", 26));
        assertEquals("xiwx", Cipher.encrypt("test", 30));
        assertEquals("xiwx", Cipher.encrypt("test", -30));
    }

    @Test
    void decryptTest() {
        assertEquals("test", Cipher.decrypt("yjxy", 5));
        assertEquals("test", Cipher.decrypt("yjxy", -5));
        assertEquals("test", Cipher.decrypt("test", 0));
        assertEquals("test", Cipher.decrypt("test", 26));
        assertEquals("test", Cipher.decrypt("xiwx", 30));
        assertEquals("test", Cipher.decrypt("xiwx", -30));
    }
}
