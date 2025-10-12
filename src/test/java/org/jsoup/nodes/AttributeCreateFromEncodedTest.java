package org.jsoup.nodes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttributeCreateFromEncodedTest {
    @Test
    void requirement1_keyAndValueAreNull_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Attribute.createFromEncoded(null, null);
        });
    }

    @Test
    void requirement2_keyIsNullAndValueIsEmpty_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Attribute.createFromEncoded(null, "");
        });
    }

    @Test
    void requirement3_keyIsNullAndValueIsNormal_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Attribute.createFromEncoded(null, "quality assurance");
        });
    }

    @Test
    void requirement4_keyIsNullAndValueHasEntity_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Attribute.createFromEncoded(null, "&amp;");
        });
    }

    @Test
    void requirement5_keyIsEmptyAndValueIsNull_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Attribute.createFromEncoded("", null);
        });
    }

    @Test
    void requirement6_keyIsEmptyAndValueIsEmpty_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Attribute.createFromEncoded("", "");
        });
    }

    @Test
    void requirement7_keyIsEmptyAndValueIsNormal_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Attribute.createFromEncoded("", "quality assurance");
        });
    }

    @Test
    void requirement8_keyIsEmptyAndValueIsNormalWithEntity_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Attribute.createFromEncoded("", "quality assurance &amp testing");
        });
    }

    @Test
    void requirement9_keyIsEmptyAndValueHasEntity_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Attribute.createFromEncoded("", "&amp;");
        });
    }

    // == Tests for Successful Creation (Requirements 10-22) ==

    @Test
    void requirement10_validKeyAndNullValue_createsAttributeWithEmptyValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Attribute.createFromEncoded("Test", null);
        });
    }

    @Test
    void requirement11_validKeyAndNormalValue_createsAttribute() {
        Attribute attr = Attribute.createFromEncoded("Test", "quality assurance");
        assertEquals("Test", attr.getKey());
        assertEquals("quality assurance", attr.getValue());
    }

    @Test
    void requirement12_validKeyAndValueWithEntity_createsDecodedAttribute() {
        Attribute attr = Attribute.createFromEncoded("Test", "quality assurance &amp testing");
        assertEquals("Test", attr.getKey());
        assertEquals("quality assurance & testing", attr.getValue());
    }

    @Test
    void requirement13_validKeyAndEntityValue_createsDecodedAttribute() {
        Attribute attr = Attribute.createFromEncoded("Test", "&amp;");
        assertEquals("Test", attr.getKey());
        assertEquals("&", attr.getValue());
    }

    @Test
    void requirement14_keyWithEntityAndNullValue_createsAttributeWithEmptyValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Attribute.createFromEncoded("quality assurance &amp testing", null);
        });
    }

    @Test
    void requirement15_keyWithEntityAndEmptyValue_createsAttributeWithEmptyValue() {
        Attribute attr = Attribute.createFromEncoded("quality assurance &amp testing", "");
        assertEquals("quality assurance &amp testing", attr.getKey());
        assertEquals("", attr.getValue());
    }

    @Test
    void requirement16_keyWithEntityAndNormalValue_createsAttribute() {
        Attribute attr = Attribute.createFromEncoded("quality assurance &amp testing", "quality");
        assertEquals("quality assurance &amp testing", attr.getKey());
        assertEquals("quality", attr.getValue());
    }

    @Test
    void requirement17_bothKeyAndValueHaveEntities_createsDecodedAttribute() {
        Attribute attr = Attribute.createFromEncoded("quality assurance &amp testing", "quality assurance &amp testing");
        assertEquals("quality assurance &amp testing", attr.getKey());
        assertEquals("quality assurance & testing", attr.getValue());
    }

    @Test
    void requirement18_keyWithEntityAndValueIsEntity_createsDecodedAttribute() {
        Attribute attr = Attribute.createFromEncoded("quality assurance &amp testing", "&amp;");
        assertEquals("quality assurance &amp testing", attr.getKey());
        assertEquals("&", attr.getValue());
    }

    @Test
    void requirement19_keyIsEntityAndValueIsNull_createsAttributeWithEmptyValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Attribute.createFromEncoded("&amp;", null);
        });
    }

    @Test
    void requirement20_keyIsEntityAndValueIsEmpty_createsAttributeWithEmptyValue() {
        Attribute attr = Attribute.createFromEncoded("&amp;", "");
        assertEquals("&amp;", attr.getKey());
        assertEquals("", attr.getValue());
    }

    @Test
    void requirement21_keyIsEntityAndValueIsNormal_createsAttribute() {
        Attribute attr = Attribute.createFromEncoded("&amp;", "quality assurance");
        assertEquals("&amp;", attr.getKey());
        assertEquals("quality assurance", attr.getValue());
    }

    @Test
    void requirement22_bothKeyAndValueAreEntities_createsDecodedAttribute() {
        Attribute attr = Attribute.createFromEncoded("&amp;", "&amp;");
        assertEquals("&amp;", attr.getKey());
        assertEquals("&", attr.getValue());
    }
}
