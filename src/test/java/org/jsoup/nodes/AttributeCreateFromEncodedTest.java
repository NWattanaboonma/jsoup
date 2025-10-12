package org.jsoup.nodes;

import org.jsoup.helper.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//Eak Code
public class AttributeCreateFromEncodedTest {
    @Test
    void tr01() {
        Attribute attr = Attribute.createFromEncoded("title", "Hello");
        assertNotNull(attr);
        assertEquals("title", attr.getKey());
        assertEquals("Hello", attr.getValue());
    }
    @Test
    void tr02() {
        assertThrows(ValidationException.class, () -> {
            Attribute.createFromEncoded("anyKey", null); // Note: Key cannot be null here
        });
    }
    @Test
    void tr05() {
        assertThrows(ValidationException.class, () -> {
            Attribute.createFromEncoded("", "quality assurance");
        });
    }
    @Test
    void tr06() {
        String keyWithEntity = "quality assurance &amp testing";
        Attribute attr = Attribute.createFromEncoded(keyWithEntity, "Hello");
        assertNotNull(attr);
        assertEquals(keyWithEntity, attr.getKey());
        assertEquals("Hello", attr.getValue());
    }
    @Test
    void tr07() {
        Attribute attr = Attribute.createFromEncoded("&amp;", "Hello");
        assertNotNull(attr);
        assertEquals("&amp;", attr.getKey());
        assertEquals("Hello", attr.getValue());
    }
    @Test
    void tr11() {
        assertThrows(ValidationException.class, () -> {
            Attribute.createFromEncoded(null, "");
        });
    }
    @Test
    void tr12() {
        assertThrows(ValidationException.class, () -> {
            Attribute.createFromEncoded(null, "quality assurance &amp testing");
        });
    }
    @Test
    void tr13() {
        assertThrows(ValidationException.class, () -> {
            Attribute.createFromEncoded(null, "&amp;");
        });
    }
    @Test
    void tr14() {
        assertThrows(ValidationException.class, () -> {
            Attribute.createFromEncoded("anyKey", null);
        });
    }
    @Test
    void tr15() {
        assertThrows(ValidationException.class, () -> {
            Attribute.createFromEncoded("quality assurance &amp testing", null);
        });
    }
    @Test
    void tr16() {
        assertThrows(ValidationException.class, () -> {
            Attribute.createFromEncoded("&amp;", null);
        });
    }
}
