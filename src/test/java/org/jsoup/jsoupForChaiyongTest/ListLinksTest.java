package org.jsoup.jsoupForChaiyongTest;

import org.jsoup.examples.ListLinks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class ListLinksTest {

    private PrintStream originalOut;
    private ByteArrayOutputStream baos;
    private Method printMethod;
    private Method trimMethod; // Method for the trim function

    @BeforeEach
    void setUp() throws Exception {
        originalOut = System.out;
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos, true, "UTF-8"));

        trimMethod = ListLinks.class.getDeclaredMethod("trim", String.class, int.class);
        trimMethod.setAccessible(true);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    //Helper Test Case #8
    private String invokeTrim(String s, int width) throws Exception {
        return (String) trimMethod.invoke(null, s, width);
    }

    //Test Case #8
    @Test
    void testTrimWidthZero() throws Exception {
        String result = invokeTrim("", 0);
        assertEquals("", result);
    }

    @Test
    void testTrimWidthPositive() throws Exception {
        String result = invokeTrim("class", 2);
        assertEquals("c.", result);
    }
}

