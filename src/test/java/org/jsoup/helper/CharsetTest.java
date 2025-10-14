package org.jsoup.helper;

/* Copyright (C) 2025 Phonlapat Urairong - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CharsetTest {
    @Test
    public void charetInputValueIsString() {
        HttpConnection.Response response = new HttpConnection.Response();
        response.charset("Testdata");
        assertEquals("Testdata", response.charset());
    }

    @Test public void charetInputValueIsEmptyString() {
        HttpConnection.Response response = new HttpConnection.Response();
        response.charset("");
        assertEquals("", response.charset());
    }
    @Test public void charetInputValueIsNull() {
        HttpConnection.Response response = new HttpConnection.Response();
        response.charset(null);
        assertNull(response.charset());
    }
    @Test public void charetInputValueIsInterger() {
        HttpConnection.Response response = new HttpConnection.Response();
        response.charset(String.valueOf(123));
        assertEquals("123", response.charset());
    }
}
