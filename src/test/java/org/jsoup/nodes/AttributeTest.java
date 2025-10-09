package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttributeTest {
    @Test
    public void html() {
        Attribute attr = new Attribute("key", "value &");
        assertEquals("key=\"value &amp;\"", attr.html());
        assertEquals(attr.html(), attr.toString());
    }

    @Test
    public void htmlWithLtAndGtInValue() {
        Attribute attr = new Attribute("key", "<value>");
        assertEquals("key=\"&lt;value&gt;\"", attr.html());
    }

    @Test public void testWithSupplementaryCharacterInAttributeKeyAndValue() {
        String s = new String(Character.toChars(135361));
        Attribute attr = new Attribute(s, "A" + s + "B");
        assertEquals(s + "=\"A" + s + "B\"", attr.html());
        assertEquals(attr.html(), attr.toString());
    }

    @Test public void validatesKeysNotEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Attribute(" ", "Check"));
    }

    @Test public void validatesKeysNotEmptyViaSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            Attribute attr = new Attribute("One", "Check");
            attr.setKey(" ");
        });
    }

    @Test public void booleanAttributesAreEmptyStringValues() {
        Document doc = Jsoup.parse("<div hidden>");
        Attributes attributes = doc.body().child(0).attributes();
        assertEquals("", attributes.get("hidden"));

        Attribute first = attributes.iterator().next();
        assertEquals("hidden", first.getKey());
        assertEquals("", first.getValue());
        assertFalse(first.hasDeclaredValue());
        assertTrue(Attribute.isBooleanAttribute(first.getKey()));
    }

    @Test public void settersOnOrphanAttribute() {
        Attribute attr = new Attribute("one", "two");
        attr.setKey("three");
        String oldVal = attr.setValue("four");
        assertEquals("two", oldVal);
        assertEquals("three", attr.getKey());
        assertEquals("four", attr.getValue());
        assertNull(attr.parent);
    }

    @Test void settersAfterParentRemoval() {
        // tests key and value set on a retained attribute after disconnected from parent
        Attributes attrs = new Attributes();
        attrs.put("foo", "bar");
        Attribute attr = attrs.attribute("foo");
        assertNotNull(attr);
        attrs.remove("foo");
        assertEquals("foo", attr.getKey());
        assertEquals("bar", attr.getValue());
        attr.setKey("new");
        attr.setValue("newer");
        assertEquals("new", attr.getKey());
        assertEquals("newer", attr.getValue());
    }

    @Test public void hasValue() {
        Attribute a1 = new Attribute("one", "");
        Attribute a2 = new Attribute("two", null);
        Attribute a3 = new Attribute("thr", "thr");

        assertTrue(a1.hasDeclaredValue());
        assertFalse(a2.hasDeclaredValue());
        assertTrue(a3.hasDeclaredValue());
    }

    @Test public void canSetValueToNull() {
        Attribute attr = new Attribute("one", "val");
        String oldVal = attr.setValue(null);
        assertEquals("one", attr.html());
        assertEquals("val", oldVal);

        oldVal = attr.setValue("foo");
        assertEquals("", oldVal); // string, not null
    }

    @Test void booleanAttributesAreNotCaseSensitive() {
        // https://github.com/jhy/jsoup/issues/1656
        assertTrue(Attribute.isBooleanAttribute("required"));
        assertTrue(Attribute.isBooleanAttribute("REQUIRED"));
        assertTrue(Attribute.isBooleanAttribute("rEQUIREd"));
        assertFalse(Attribute.isBooleanAttribute("random string"));

        String html = "<a href=autofocus REQUIRED>One</a>";
        Document doc = Jsoup.parse(html);
        assertEquals("<a href=\"autofocus\" required>One</a>", doc.selectFirst("a").outerHtml());

        Document doc2 = Jsoup.parse(html, Parser.htmlParser().settings(ParseSettings.preserveCase));
        assertEquals("<a href=\"autofocus\" REQUIRED>One</a>", doc2.selectFirst("a").outerHtml());
    }

    @Test void orphanNamespace() {
        Attribute attr = new Attribute("one", "two");
        assertEquals("", attr.namespace());
    }
    @Test
    void returnsDecodedAttribute_whenInputsAreValid() {
        // Functionality-based: normal path; postcondition = decoded value, parent=null
        Attribute attr = Attribute.createFromEncoded("title", "Hello&#32;World"); // &#32; decodes to normal space
        assertEquals("title", attr.getKey());
        assertEquals("Hello World", attr.getValue());
        // parent is set to null by factory; if no public getter, we assert observable fields only
        // Optionally assert string form if your Attribute.toString exposes internals in your codebase:
        // assertEquals("Attribute[key=title, value=Hello World, parent=null]", attr.toString());
    }
//    @Test
//    void throwsNullPointerException_whenEncodedValueIsNull() {
//        // Functionality-based: special value relationship (null) → exception path
//        assertThrows(NullPointerException.class,
//                () -> Attribute.createFromEncoded("title", null));
//    }
    @Test
    void returnsEmptyValue_whenEncodedValueIsEmpty() {
        // Functionality-based: special value (blank) still returns a valid attribute
        Attribute attr = Attribute.createFromEncoded("title", "");
        assertEquals("title", attr.getKey());
        assertEquals("", attr.getValue());
    }
    @Test
    void decodesCommonEntities_correctly() {
        Attribute attr = Attribute.createFromEncoded("data", "Tom &amp; Jerry");
        assertEquals("Tom & Jerry", attr.getValue());
    }
}
