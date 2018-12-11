package com.liquidpig.cenos;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a section of code.
 */
public class Function
{
    public final List<Instruction> instructions;
    public final String name;
    public final int locals;
    public int start = -1;

    /**
     * Builds the fucntion.
     * @param instructions is it's list of instructions which have already been compiled.
     * @param name is the name of the function.
     * @param locals is the number of local variables the function has.
     */
    public Function(List<Instruction> instructions, String name, int locals)
    {
        this.instructions = instructions;
        this.name = name;
        this.locals = locals;
    }
}
