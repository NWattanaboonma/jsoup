package org.jsoup.jsoupForChaiyongTest;

/* Copyright (C) 2025 Nuttipong Wattanaboonma - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

import org.jsoup.examples.ListLinks;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.IllegalFormatConversionException;
import java.util.MissingFormatArgumentException;

import static org.junit.jupiter.api.Assertions.*;
public class MyUtilsTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private static Method printMethod; // Holds the private print method

    @BeforeAll
    static void getPrivateMethod() throws NoSuchMethodException {
        printMethod = ListLinks.class.getDeclaredMethod("print", String.class, Object[].class);
        printMethod.setAccessible(true);
    }
    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }
    @Test
    void testRequirement2() throws Exception {
        printMethod.invoke(null, "", new Object[]{});
        assertEquals(System.lineSeparator(), outContent.toString());
    }
    @Test
    void testRequirement3() {
        String one = "one";
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Object[] arguments = new Object[]{ "number %s, %s", new Object[]{one} };
            printMethod.invoke(null, arguments);
        });

        assertTrue(exception.getCause() instanceof MissingFormatArgumentException);
    }
    @Test
    void testRequirement6() throws Exception {
        String one = "one";
        String two = "two";
        Object[] arguments = new Object[]{ "", new Object[]{one, two} };
        printMethod.invoke(null, arguments);
        assertEquals(System.lineSeparator(), outContent.toString());
    }
    @Test
    void testRequirement8() {
        String one = "one";
        String two = "two";
        String three = "three";
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Object[] arguments = new Object[]{ "number %s, %d", new Object[]{one, two, three} };
            printMethod.invoke(null, arguments);
        });
        assertTrue(exception.getCause() instanceof IllegalFormatConversionException);
    }
    @Test
    void testRequirement9() {
        String one = "one";
        String two = "two";
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Object[] arguments = new Object[]{ "number %d, %d", new Object[]{one, two} };
            printMethod.invoke(null, arguments);
        });
        assertTrue(exception.getCause() instanceof IllegalFormatConversionException);
    }
}
