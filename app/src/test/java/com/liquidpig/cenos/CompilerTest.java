package com.liquidpig.cenos;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;


/**
 * Tests that the compiler is compiling that which we desire.
 */
public class CompilerTest
{
    @Test
    public void groupTest()
    {
        Tokeniser tokeniser = new Tokeniser("aaa :bingo 6 multiply println ; bongo :bongo 4 5 add ; bingo ");
        List<String> tokens = tokeniser.tokenise();
        Map<String, List<String>> functions = Compiler.groupTokens(tokens);
        List<String> bingo = functions.get("bingo");
        List<String> bongo = functions.get("bongo");
        List<String> start = functions.get("_start");

        assertEquals("6", bingo.get(0));
        assertEquals("multiply", bingo.get(1));
        assertEquals("println", bingo.get(2));

        assertEquals("4", bongo.get(0));
        assertEquals("5", bongo.get(1));
        assertEquals("add", bongo.get(2));

        assertEquals("aaa", start.get(0));
        assertEquals("bongo", start.get(1));
        assertEquals("bingo", start.get(2));
    }

    @Test
    public void groupExceptionTest()
    {
        assertEquals(1, 1);
    }

    public void 
}
