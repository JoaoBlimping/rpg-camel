package com.liquidpig.cenos;


public class Script
{
    public static final int LOCALS_SIZE = 256;

    private Map<String, Function> functionTable;
    private List<Instruction> text;
    private int[LOCALS_SIZE] locals;
    private Stack<Integer> stack;
    private int counter = 0;



    public int step(int delta)
    {
        stack.push(delta);
        Instruction instruction = text.get(counter);
        switch (instruction.type)
        {
            case Instruction.Code.FUCK:
                // TODO: make something bad happen here.
                break;
            case Instruction.Code.
        }


    }
}
