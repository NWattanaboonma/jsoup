package org.jsoup.select;

/* Copyright (C) 2025 Phonlapat Urairong - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.LeafNode;
import org.jsoup.nodes.TextNode;

import static org.junit.jupiter.api.Assertions.*;

class TestMatches {
    private static LeafNode firstTextNode(Document doc, String css) {
        Element el = doc.selectFirst(css);
        if (el == null) throw new IllegalStateException("No element: " + css);
        if (el.childNodeSize() == 0) el.appendText("");
        return (LeafNode) el.childNode(0);
    }

    // TR!
    @Test @DisplayName("TR1: size==1, Non_Structural, No_Element, No_Match => false (LeafNode)")
    void tr1_singleEvaluator_textNode_noMatch() {
        Document doc = Jsoup.parse("<div>text</div>");
        LeafNode leaf = firstTextNode(doc, "div");
        CombiningEvaluator.Or eval = new CombiningEvaluator.Or(
                new Evaluator.Tag("p")
        );
        assertFalse(eval.matches(doc, leaf));
    }

    // TR2
    @Test @DisplayName("TR2: size==1, Structural, No_Element, No_Match => false (LeafNode)")
    void tr2_singleEvaluator_textNode_inP_noMatch() {
        Document doc = Jsoup.parse("<p>content</p>");
        LeafNode leaf = firstTextNode(doc, "p");
        CombiningEvaluator.Or eval = new CombiningEvaluator.Or(
                new Evaluator.Tag("p")
        );
        assertFalse(eval.matches(doc, leaf));
    }

    // TR3
    @Test @DisplayName("TR3: size==0, Structural, No_Element, No_Match => false (LeafNode)")
    void tr3_zeroEvaluators_textNode_noMatch() {
        Document doc = Jsoup.parse("<p></p>");
        LeafNode leaf = firstTextNode(doc, "p");
        CombiningEvaluator.Or eval = new CombiningEvaluator.Or();
        assertFalse(eval.matches(doc, leaf));
    }

    // TR4
    @Test @DisplayName("TR4: size>1, Mixed, Element, Any_Match => true (Element overload)")
    void tr4_multipleEvaluators_mixed_element_anyMatch() {
        Document doc = Jsoup.parse("<div class='header'></div>");
        Element element = doc.selectFirst("div");
        CombiningEvaluator.Or eval = new CombiningEvaluator.Or(
                new Evaluator.Tag("p"),
                new Evaluator.Class("header")
        );
        assertTrue(eval.matches(doc, element));
    }

    // TR5
    @Test @DisplayName("TR5: size>1, Structural, Element, Any_Match => true (Element overload)")
    void tr5_multipleEvaluators_structural_element_anyMatch() {
        Document doc = Jsoup.parse("<div></div>");
        Element element = doc.selectFirst("div");
        CombiningEvaluator.Or eval = new CombiningEvaluator.Or(
                new Evaluator.Tag("div"),
                new Evaluator.Tag("span")
        );
        assertTrue(eval.matches(doc, element));
    }


    static class TextEqualsOkEvaluator extends Evaluator {
        @Override public boolean matches(Element root, Element el) { return false; }
        @Override public boolean matches(Element root, LeafNode leaf) {
            return (leaf instanceof TextNode) && "ok".equals(((TextNode) leaf).text());
        }
    }

    @Test @DisplayName("LeafNode true-path: custom evaluator matches text node => true")
    void leafNode_true_path_withCustomEvaluator() {
        Document doc = Jsoup.parse("<div>ok</div>");
        LeafNode leaf = firstTextNode(doc, "div");
        CombiningEvaluator.Or eval = new CombiningEvaluator.Or(
                new Evaluator.Tag("span"),
                new TextEqualsOkEvaluator()
        );
        assertTrue(eval.matches(doc, leaf));
    }
}
