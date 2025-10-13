package org.jsoup.nodes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestAttribute {
    @Test
    void testEqualsWithSameObject() {
        Attribute a = new Attribute("key", "val");
        boolean result = a.equals(a);
        assertTrue(result);  // ออบเจกต์เดียวกัน
    }
    @Test
    void testEqualsWithNullAttribute() {
        Attribute attr1 = new Attribute("key", "val");
        boolean result = attr1.equals(null);
        assertFalse(result);
    }
    @Test
    void testEqualsWithNullObject() {
        Object o =  new Object();
        boolean result = o.equals(null);
        assertFalse(result);
    }
    @Test
    void testDifferentValueAndKeyShouldNotBeEqual() {
        Attribute attr1 = new Attribute("href", "https://openai.com");
        Attribute attr2 = new Attribute("src", "https://chat.openai.com");
        boolean result = attr1.equals(attr2);
        assertFalse(result);
    }
    @Test
    void testDifferentKeyShouldNotBeEqual() {
        Attribute attr1 = new Attribute("href", "https://openai.com");
        Attribute attr2 = new Attribute("src", "https://openai.com");
        boolean result = attr1.equals(attr2);
        assertFalse(result);
    }
    @Test
    void testDifferentValueShouldNotBeEqual() {
        Attribute attr1 = new Attribute("href", "https://openai.com");
        Attribute attr2 = new Attribute("href", "https://chat.openai.com");
        boolean result = attr1.equals(attr2);
        assertFalse(result);
    }
    @Test
    void testEqualAttributesShouldBeEqual() {
        Attribute attr1 = new Attribute("href", "https://openai.com");
        Attribute attr2 = new Attribute("href", "https://openai.com");
        boolean result1 = attr1.equals(attr2);
        boolean result2 = attr2.equals(attr1);
        assertTrue(result1);
        assertTrue(result2);
    }
    @Test
    void testEqualsWithDifferentClass() {
        Attribute attr = new Attribute("key", "value");
        Object o = "some string";
        boolean result = attr.equals(o);
        assertFalse(result, "equals() must return false when o in difference class");
    }
}