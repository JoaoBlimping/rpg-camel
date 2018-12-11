package com.liquidpig.cenos;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;



/**
 * Converts text files into CENOS bytecode that can be executed for your pleasure.
 */
public class Compiler
{
    public static final char START_FUNCTION = ':';
    public static final String END_FUNCTION = ";";


    public static Map<String, List<String>> groupTokens(List<String> tokens)
    {
        Map<String, List<String>> table = new HashMap<String, List<String>>();
        table.put("_start", new LinkedList<String>());
        String func = null;
        int cursor = 0;
        while (cursor < tokens.size())
        {
            String token = tokens.get(cursor);
            if (token.charAt(0) == START_FUNCTION)
            {
                if (func != null) throw new CompilationException("Functions cannot be nested: "+cursor);
                func = token.substring(1);
                if (table.containsKey(func)) throw new CompilationException("Duplicate definition of token "+func);
                table.put(func, new LinkedList<String>());
            }
            else if (token.equals(END_FUNCTION))
            {
                if (func == null) throw new CompilationException("Function end with no start: "+cursor);
                func = null;
            }
            else if (func != null) table.get(func).add(token);
            else table.get("_start").add(token);
            cursor++;
        }
        return table;
    }

    public static Map<String, Function> functionTable(List<List<String>> groups)
    {
        // analyse for local variables. we can do this in one pass building symbol table as we go.
        // convert straight tokens into instructions.
        // figure out how the fuck we will do the control structures. I am thinking maybe we should make thing function
        // recursive.
        return null;
    }
}
