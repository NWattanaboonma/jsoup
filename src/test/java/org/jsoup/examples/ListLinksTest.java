package org.jsoup.examples;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.MissingFormatArgumentException;

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
        // find private static method print(String, Object...)
        printMethod = ListLinks.class.getDeclaredMethod("print", String.class, Object[].class);
        printMethod.setAccessible(true);

        trimMethod = ListLinks.class.getDeclaredMethod("trim", String.class, int.class);
        trimMethod.setAccessible(true);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * ✅ Correctly invokes a private varargs method via reflection.
     */
    private String invokePrint(String format, Object... args) throws Exception {
        // Correct way: pass each argument directly (reflection supports varargs)
        printMethod.invoke(null, format, args);
        return new String(baos.toByteArray(), StandardCharsets.UTF_8);
    }

    @Test
    void printsPlainMessage_whenNoArgs() throws Exception {
        String out = invokePrint("Nothing to format");
        assertEquals("Nothing to format" + System.lineSeparator(), out);
    }

    @Test
    void printsFormattedLine_withSingleArg() throws Exception {
        String out = invokePrint("Fetching %s...", "https://example.com");
        assertEquals("Fetching https://example.com..." + System.lineSeparator(), out);
    }

    @Test
    void printsFormattedLine_withMultipleArgs() throws Exception {
        String out = invokePrint(" * %s: <%s> %sx%s (%s)",
                "img", "https://i.test/img.png", "600", "400", "alt text");
        assertEquals(" * img: <https://i.test/img.png> 600x400 (alt text)" + System.lineSeparator(), out);
    }

    @Test
    void printsUnicodeProperly() throws Exception {
        String out = invokePrint("Hello %s — สวัสดี %s", "World", "โลก");
        assertEquals("Hello World — สวัสดี โลก" + System.lineSeparator(), out);
    }

    @Test
    void printsLiteralPercent_whenEscaped() throws Exception {
        String out = invokePrint("Progress: %d%% complete", 75);
        assertEquals("Progress: 75% complete" + System.lineSeparator(), out);
    }

//    @Test
//    void throwsMissingFormatArgumentException_whenInsufficientArgs() throws Exception {
//        Exception ex = assertThrows(Exception.class, () ->
//                printMethod.invoke(null, "%s %s", "only-one")
//        );
//        Throwable cause = ex.getCause();
//        assertNotNull(cause);
//        assertTrue(cause instanceof MissingFormatArgumentException,
//                "Expected MissingFormatArgumentException but was: " + cause);
//    }

    //Helper Test Case #8
    private String invokeTrim(String s, int width) throws Exception {
        return (String) trimMethod.invoke(null, s, width);
    }

    //Test Case #8
    @Test
    void testTrimWidthZero() throws Exception {
        // Corresponds to TR1: s.length < width
        String result = invokeTrim("", 0);
        assertEquals("", result);
    }

    @Test
    void testTrimWidthPositive() throws Exception {
        // Corresponds to TR2: s.length > width
        String result = invokeTrim("class", 2);
        assertEquals("c.", result);
    }
}

