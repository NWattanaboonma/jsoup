package org.jsoup.jsoupForChaiyongTest;

/* Copyright (C) 2025 Auay - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

import org.jsoup.examples.Wikipedia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.IllegalFormatConversionException;
import java.util.MissingFormatArgumentException;

import static org.junit.jupiter.api.Assertions.*;

public class WikipediaTest {

    private PrintStream originalOut;
    private ByteArrayOutputStream consoleOutput;
    private Method logMethod;
    private final String utf8 = StandardCharsets.UTF_8.name();

    @BeforeEach
    void setUp() throws Exception {
        originalOut = System.out;
        consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput, true, utf8));

        logMethod = Wikipedia.class.getDeclaredMethod("log", String.class, String[].class);
        logMethod.setAccessible(true);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    //Helper Test Case 9
    private String invokeLog(String msg, String... vals) throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException {
        logMethod.invoke(null, new Object[]{msg, vals});
        return consoleOutput.toString(utf8);
    }

    //Test Case 9
    @Test
    void testLogEmptyMsgAndEmptyArgs() throws Exception {
        String output = invokeLog("");
        //log method always prints a newline, so expect a line separator.
        assertEquals(System.lineSeparator(), output);
    }

    @Test
    void testLogTooFewArgs() throws Exception {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            invokeLog("number %s, %s", "one");
        });
        Throwable cause = exception.getCause();
        assertInstanceOf(MissingFormatArgumentException.class, cause);
    }

    @Test
    void testLogEmptyMsg() throws Exception {
        String output = invokeLog("","one", "two");
        assertEquals(System.lineSeparator(),output);
    }

    @Test
    void testLogTooManyArgs() throws Exception {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            invokeLog("number %s, %d", "one", "two", "three");
        });
        Throwable cause = exception.getCause();
        assertInstanceOf(IllegalFormatConversionException.class, cause);
    }

    @Test
    void testLogMissFormat() throws Exception {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            invokeLog("number %s, %d", "one", "two");
        });
        Throwable cause = exception.getCause();
        assertInstanceOf(IllegalFormatConversionException.class, cause);
    }

}
