package org.jsoup.parser;

/* Copyright (C) 2025 Thitiwan Keattitat - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */
import org.jsoup.helper.ValidationException;
import org.jsoup.nodes.Attributes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TokenTest {

    private final TreeBuilder treeBuilder = new HtmlTreeBuilder();

    @Test
    public void tr1_noAttributesEmptyKey() {
        Token.StartTag tag = new Token.StartTag(treeBuilder);
        tag.nameAttr("div", new Attributes());
        String key = "";

        assertEquals(false, tag.hasAttributeIgnoreCase(key), "Should return false for an empty key with no attributes");
    }
    @Test
    public void tr2_oneAttributeSameCase() {
        Token.StartTag tag = new Token.StartTag(treeBuilder);
        Attributes attrs = new Attributes();
        attrs.put("id", "main");
        tag.nameAttr("div", attrs);
        String key = "id";

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

        assertThrows(ValidationException.class, () -> { tag.hasAttributeIgnoreCase(key);});
    }
}
