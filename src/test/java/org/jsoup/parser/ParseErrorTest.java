package org.jsoup.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for ParseError, updated to use the available public constructor.
 * This requires the ParseError(int, String) constructor to be public.
 */
public class ParseErrorTest {
    @Test
    void testGetErrorMessageNewLine() {
        ParseError err = new ParseError(1, "Unexpected token. /nInvalid tag");
        assertEquals("Unexpected token. /nInvalid tag", err.getErrorMessage());
    }

    @Test
    void testGetErrorMessageNoNewLine() {
        ParseError err = new ParseError(1, "Unexpected token");
        assertEquals("Unexpected token", err.getErrorMessage());
    }

    @Test
    void testGetErrorMessageEmptyStringNoNewLine() {
        ParseError err = new ParseError(1, "");
        assertEquals("", err.getErrorMessage());
    }

    @Test
    void testGetErrorMessageNullNoNewLine() {
        ParseError err = new ParseError(1, null);
        assertNull(err.getErrorMessage());
    }
}

