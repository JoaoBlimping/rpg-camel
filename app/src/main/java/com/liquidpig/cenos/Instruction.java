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
        FUCK, // if this gets called then the program will crash and dump.
        JUMP, // Moves the execution cursor by a relative value.
        JUMP_IF, // Move the execution cursor by a relative value if top of the stack is true also pops.
        PRECALL, // stores an index in the function table to be converted to a call.
        CALL, // places current execution counter on stack and then jumps absolutely.
        ENTER, // jumps absolutely to function but does not place execution counter on stack.
        EXTERNAL_CALL, // runs an external piece of code. TODO: yeet
        RETURN, // Jumps absolutely to location on top of stack and consumes.
        PUSH, // place constant on stack.
        POP, // deletes top of stack.
        POP_LOCAL, // sets a numbered local variable by popping from stack
        PUSH_LOCAL, // pushes the value of a local variable to the stack.
        SWAP, // swaps top two items on stack.
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        PRINT,
        PRINT_LINE
    }

    public Code type = Code.FUCK;
    public int value = 0;
}
