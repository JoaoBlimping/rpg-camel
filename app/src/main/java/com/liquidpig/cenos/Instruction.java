package com.liquidpig.cenos;

/**
 * Represents a single action that can be executed by the virtual machine.
 */
public class Instruction
{
    /**
     * This is the list of all possible instruction codes.
     */
    public enum Code
    {
        JUMP, // Moves the execution cursor by a relative value.
        JUMP_IF, // Move the execution cursor by a relative value if top of the stack is true also pops.
        PRECALL, // stores an index in the function table to be converted to a call.
        CALL, // places current execution counter on stack and then jumps absolutely.
        EXTERNAL_CALL, // runs an external piece of code. TODO: yeet
        RETURN, // Jumps absolutely to location on top of stack and consumes.
        PUSH, // place constant on stack.
        POP, // deletes top of stack.
        SET, // sets a numbered local variable.
        GET, // gets a numbered local variable.
        SWAP, // swaps top two items on stack.
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        PRINT,
        PRINT_LINE
    }

    public final Code type;
    public final int value;

    /**
     * Puts the parameters of the instruction in and creates it permanently.
     * @param type is the type of instruction that this will be.
     * @param value is the piece of data that goes with the instruction type.
     */
    public Instruction(Code type, int value)
    {
        this.type = type;
        this.value = value;
    }
}
