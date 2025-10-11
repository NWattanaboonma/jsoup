package org.jsoup.internal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilMookTest {
    @Test
    public void tr1_baseCase_startsWthNewlineLengthGreaterThanOne() {
        String input = "\nHello";
        assertTrue(StringUtil.startsWithNewline(input), "TR1 failed: Input '\\nHello' should return true.");
    }
//   The Come from the second return of the test subject.
    @Test
    public void tr2_doesNotStartWithNewlineLengthGreaterThanOne() {
        String input = "Hello";
        assertFalse(StringUtil.startsWithNewline(input), "TR2 failed: Input 'Hello' should return false.");
    }
    @Test
    public void tr5_startsWithNewlineLengthEqualsOne() {
        String input = "\n";
        assertTrue(StringUtil.startsWithNewline(input), "TR5 failed: Input '\\n' should return true.");
    }
//   The under 2 are not the case for the BCC
    @Test
    public void testNullStringReturnsFalse() {
        // This test covers the 'string == null' branch.
        assertFalse(StringUtil.startsWithNewline(null), "A null string should return false.");
    }
    @Test
    public void testEmptyStringReturnsFalse() {
        // This test covers the 'string.length() == 0' branch.
        assertFalse(StringUtil.startsWithNewline(""), "An empty string should return false.");
    }

}
