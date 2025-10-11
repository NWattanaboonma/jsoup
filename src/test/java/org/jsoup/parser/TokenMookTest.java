package org.jsoup.parser;

import org.jsoup.helper.ValidationException;
import org.jsoup.nodes.Attributes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TokenMookTest {
    // A TreeBuilder is required for the Tag constructor.
    private final TreeBuilder treeBuilder = new HtmlTreeBuilder();
    @Test
    public void tr1_noAttributesEmptyKey() {


        // Setup: A StartTag with no attributes.
        Token.StartTag tag = new Token.StartTag(treeBuilder);
        tag.nameAttr("div", new Attributes()); // Ensure attributes object exists but is empty

        // Input: An empty string key.
        String key = "";

        // Execution & Assertion
        assertEquals(false, tag.hasAttributeIgnoreCase(key), "Should return false for an empty key with no attributes");
    }
    @Test
    public void tr2_oneAttributeSameCase() {
        // Test Blocks: (Attribute == 1, Valid_Key, Attribute found with same case)
        // Input (Setup, Input): <div id="main">, "id"
        // Expected output: True

        // Setup: A StartTag with one attribute.
        Token.StartTag tag = new Token.StartTag(treeBuilder);
        Attributes attrs = new Attributes();
        attrs.put("id", "main");
        tag.nameAttr("div", attrs);

        // Input: A valid key with the same case.
        String key = "id";

        // Execution & Assertion
        assertEquals(true, tag.hasAttributeIgnoreCase(key), "Should find attribute with the same case");
    }
    @Test
    public void tr3_multipleAttributesNullKey() {

        Token.StartTag tag = new Token.StartTag(treeBuilder);
        Attributes attrs = new Attributes();
        attrs.put("id", "main");
        attrs.put("class", "content");
        tag.nameAttr("div", attrs);
        String key = null;

        assertThrows(ValidationException.class, () -> {
            tag.hasAttributeIgnoreCase(key);
        });
    }
}
