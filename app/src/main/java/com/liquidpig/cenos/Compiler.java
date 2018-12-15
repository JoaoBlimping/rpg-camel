package com.liquidpig.cenos;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;



/**
 * Converts text files into CENOS byteInstruction.Code that can be executed for your pleasure.
 */
public class Compiler
{
    public static final char START_FUNCTION = ':';
    public static final String END_FUNCTION = ";";

    public static final char CALL = '*';
    public static final char ENTER = '&';
    public static final char PUSH = '=';
    public static final char PUSH_LOCAL = '$';
    public static final char POP_LOCAL = '%';

    public static final String TRUE = "true";
    public static final String FALSE = "false";

    public static final String SKIP = ">";
    public static final String BACK = "<";
    public static final String END_SKIP = "/";
    public static final String AND = "and";
    public static final String OR = "or";
    public static final String BINARY_AND = "andbi";
    public static final String BINARY_OR = "orbi";


    /**
     * Takes a file worth of tokens and groups them up into named functions. All tokens that are outside of a function
     * are grouped together into a _start function. Functions must not be nested, and there can not be function ends
     * without starts, and there can not be functions that start but do not end.
     * @param tokens is the list of tokens that we shall turn into functions.
     * @return a map of lists where the keys are the function names, and the lists of strings are each token in that
     *         function.
     */
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
        if (func != null) throw new CompilationException("File ends while inside function.");
        return table;
    }

    /**
     * Converts grouped lists of tokens into grouped lists of compiled instructions.
     * @param groups is the list of tokens.
     * @return a map of function objects which are derived from the grouped tokens.
     */
    public static Map<String, Function> functionTable(Map<String, List<String>> groups)
    {
        // analyse for local variables. we can do this in one pass building symbol table as we go.
        // convert straight tokens into instructions.
        // figure out how the fuck we will do the control structures. with a stack.
        Map<String, Function> functions = new HashMap<String, Function>();

        for (String group: groups.keySet())
        {
            Stack<Integer> structures = new Stack<Integer>();

            Map<String, Integer> locals = new HashMap<String, Integer>();
            List<Instruction> instructions = new LinkedList<Instruction>();
            int i = 0;
            for (String token: groups.get(group))
            {
                char first = token.charAt(0);
                Instruction instruction = new Instruction();
                instruction.type = Instruction.Code.FUCK;
                switch (first)
                {
                    case CALL:
                        break;
                    case ENTER:
                        break;
                    case PUSH_LOCAL:
                        String variable = token.substring(1);
                        instruction.type = Instruction.Code.PUSH_LOCAL;
                        instruction.value = local(locals, variable);
                        break;
                    case POP_LOCAL:
                        variable = token.substring(1);
                        instruction.type = Instruction.Code.POP_LOCAL;
                        instruction.value = local(locals, variable);
                        break;
                    case PUSH:
                        String constant = token.substring(1);
                        instruction.type = Instruction.Code.PUSH;
                        if (constant.equals(TRUE)) instruction.value = 1;
                        else if (constant.equals(FALSE)) instruction.value = 0;
                        else instruction.value = Integer.parseInt(constant);
                        // TODO: floats and shit.
                        break;
                    default:
                        switch (token)
                        {
                            case SKIP:
                                structures.push(i);
                                instruction.type = Instruction.Code.JUMP_IF;
                                break;
                            case BACK:
                                int ifLocation = structures.pop();
                                Instruction condition = instructions.get(ifLocation);
                                condition.type = Instruction.Code.JUMP_IF;
                                condition.value = i - ifLocation;
                                instruction.type = Instruction.Code.JUMP;
                                instruction.value = ifLocation - i;
                                break;
                            case END_SKIP:
                                ifLocation = structures.pop();
                                condition = instructions.get(ifLocation);
                                condition.type = Instruction.Code.JUMP_IF;
                                condition.value = i - ifLocation + 1;
                                break;
                        }
                }
                instructions.add(instruction);
                i++;
            }

            functions.put(group, new Function(instructions, group, locals.size()));
        }
        return functions;
    }

    /**
     * Add a local variable to the list of local variables if it's not there, and returns it's number in the list.
     * @param locals is the map of local varialbes and their number.
     * @param name is the name of the local variable about which we are enquiring.
     * @return the numerical code of this local variable.
     */
    private static int local(Map<String, Integer> locals, String name)
    {
        if (!locals.containsKey(name)) locals.put(name, locals.size());
        return locals.get(name);
    }
}
