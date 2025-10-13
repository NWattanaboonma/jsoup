package org.jsoup.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

//Q1
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
