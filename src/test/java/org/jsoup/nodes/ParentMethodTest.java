package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParentMethodTest {

    @Test
    void parent_onOrphanElement_null() {
        Element e = new Element("span");
        assertNull(e.parent(), "Orphan element parent() should be null");
    }

    @Test
    void parent_onElement_insideElement_returnsElement() {
        Document doc = Jsoup.parse("<div><span>t</span></div>");
        Element span = doc.selectFirst("span");
        assertNotNull(span);
        Node p = span.parent();
        assertTrue(p instanceof Element, "parent() should be an Element for nested elements");
        assertEquals("div", ((Element) p).tagName());
    }

    @Test
    void parent_onTextNode_returnsContainingElement() {
        Document doc = Jsoup.parse("<p>Text</p>");
        // If ::text is not available in your jsoup version, use textNodes()
        Element p = doc.selectFirst("p");
        TextNode tn = p.textNodes().get(0);
        Node pn = tn.parent();
        assertNotNull(pn);
        assertTrue(pn instanceof Element);
        assertEquals("p", ((Element) pn).tagName());
    }

    @Test
    void parent_onRootElement_isDocument() {
        Document doc = Jsoup.parse("<html><body></body></html>");
        Element html = doc.child(0);
        Node parent = html.parent();
        assertNotNull(parent, "Root element parent() should not be null");
        assertTrue(parent instanceof Document, "Root element parent() should be Document");
    }

    @Test
    void parent_changes_after_wrap_and_remove() {
        Element orphan = new Element("span").text("Hello");
        assertNull(orphan.parent());

        orphan.wrap("<div></div>");
        Node parentAfterWrap = orphan.parent();
        assertNotNull(parentAfterWrap);
        assertTrue(parentAfterWrap instanceof Element);
        assertEquals("div", ((Element) parentAfterWrap).tagName());

        orphan.remove();
        assertNull(orphan.parent(), "After remove(), parent() should be null again");
    }

    @Test
    void parent_switches_on_reparenting_appendChild() {
        Element a = new Element("ul");
        Element b = new Element("ol");
        Element li = new Element("li").text("x");

        a.appendChild(li);
        assertSame(a, li.parent());

        b.appendChild(li);
        assertSame(b, li.parent(), "After reparenting, parent() should switch to new parent");
    }

    @Test
    void parent_updates_after_unwrap() {
        Document doc = Jsoup.parse("<div><b><i>t</i></b></div>");
        Element div = doc.selectFirst("div");
        Element b = doc.selectFirst("b");
        Element i = doc.selectFirst("i");

        assertSame(b, i.parent(), "Sanity: i’s parent is b before unwrap");
        b.unwrap(); // remove b, keep children in place
        assertSame(div, i.parent(), "After unwrap(), i’s parent should become div");
    }

    @Test
    void parent_after_replaceWith_orphaning_replaced_node() {
        Document doc = Jsoup.parse("<div><em>old</em></div>");
        Element em = doc.selectFirst("em");
        Element strong = new Element("strong").text("new");

        Node originalParent = em.parent();
        assertNotNull(originalParent);
        em.replaceWith(strong);

        assertNull(em.parent(), "Replaced node should be orphan");
        assertSame(originalParent, strong.parent(), "Replacement node’s parent should be original parent");
    }

    @Test
    void parent_unchanged_by_before_after_sibling_insertions() {
        Document doc = Jsoup.parse("<ul><li>one</li></ul>");
        Element li = doc.selectFirst("li");
        Node parentBefore = li.parent();

        li.before("<li>zero</li>");
        li.after("<li>two</li>");

        assertSame(parentBefore, li.parent(), "Sibling insertions should not change parent()");
        assertEquals(3, parentBefore.childNodeSize(), "Parent should now have three li children");
    }
    
    @Test
    void parent_on_comment_and_dataNode() {
        Document doc = Jsoup.parse("<div><!--c--><script>var a=1;</script></div>");
        Element div = doc.selectFirst("div");

        Comment comment = (Comment) div.childNode(0);
        assertSame(div, comment.parent(), "Comment parent() should be containing Element");

        Element script = doc.selectFirst("script");
        DataNode data = (DataNode) script.childNode(0);
        assertSame(script, data.parent(), "DataNode parent() should be the script Element");
    }
}
