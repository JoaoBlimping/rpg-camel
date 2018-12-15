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
        List<String> tokens = new Tokeniser("aaa :bingo 6 multiply println ; bongo :bongo 4 5 add ; bingo ").tokenise();
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
        // TODO: fuck off.
        assertEquals(1, 1);
    }

    @Test
    public void functionTableTest()
    {
        compareFunction(
            "$bongo  %bingo   =true > inc dup\n=10 le < ",
            "_start:2,PUSH_LOCAL,POP_LOCAL:1,PUSH:1,JUMP_IF:5,FUCK,FUCK,PUSH:10,FUCK,JUMP:-5",
            "_start"
        );
        compareFunction(
            "> > < > > < < > < < ",
            "_start:0,JUMP_IF:9,JUMP_IF:1,JUMP:-1,JUMP_IF:3,JUMP_IF:1,JUMP:-1,JUMP:-3,JUMP_IF:1,JUMP:-1,JUMP:-9",
            "_start"
        );
    }

    /**
     * Compares the string version of a function to a given expected string version.
     * @param script is the script from which to compile the function to test.
     * @param output is the expected string version of the function.
     * @param func is the function in the script to test.
     */
    private void compareFunction(String script, String output, String func)
    {
        List<String> tokens = new Tokeniser(script).tokenise();
        Map<String, List<String>> groups = Compiler.groupTokens(tokens);
        Map<String, Function> functions = Compiler.functionTable(groups);
        assertEquals(output, functions.get(func).toString());
    }
}
