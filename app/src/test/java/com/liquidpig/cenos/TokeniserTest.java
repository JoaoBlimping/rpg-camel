package com.liquidpig.cenos;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

/**
 * Tests that the point objects are doing their shit right.
 */
public class TokeniserTest
{
    @Test
    public void simpleTest()
    {
        Tokeniser tokeniser = new Tokeniser("   abc   uhg           ojoijg ");
        List<String> tokens = tokeniser.tokenise();
        assertEquals(3, tokens.size());
        assertEquals("abc", tokens.get(0));
        assertEquals("uhg", tokens.get(1));
        assertEquals("ojoijg", tokens.get(2));
    }

    @Test
    public void messyTest()
    {
        Tokeniser tokeniser = new Tokeniser(" a bop verg\"ning # ning gerg\"f#  tgrtg erf \ngop ");
        List<String> tokens = tokeniser.tokenise();
        assertEquals(6, tokens.size());
        assertEquals("a", tokens.get(0));
        assertEquals("bop", tokens.get(1));
        assertEquals("verg", tokens.get(2));
        assertEquals("ning # ning gerg", tokens.get(3));
        assertEquals("f", tokens.get(4));
        assertEquals("gop", tokens.get(5));
    }
}
