package org.jsoup.jsoupForChaiyongTest;

/* Copyright (C) 2025 Mook - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilMookTest {
    @Test
    public void tr1_baseCase_startsWthNewlineLengthGreaterThanOne() {
        String input = "\nHello";
        Assertions.assertTrue(StringUtil.startsWithNewline(input), "TR1 failed: Input '\\nHello' should return true.");
    }
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
//   Make it Full cover case using this 2 below methods
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
