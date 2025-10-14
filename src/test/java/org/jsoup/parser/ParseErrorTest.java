package org.jsoup.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Test Case 5
public class ParseErrorTest {
    @Test
    void testGetErrorMessageNewLine() {
        ParseError err = new ParseError(1, "Unexpected token. \nInvalid tag");
        assertEquals("Unexpected token. \nInvalid tag", err.getErrorMessage());
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

