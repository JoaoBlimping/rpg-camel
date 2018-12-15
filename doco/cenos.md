# CENOS
This is the scripting system for the engine because the alternatives are garbage.

## Script Object Structure
### Execution Cursor
Integer pointing to the next instruction to execute.

### Main Stack
This is where the main data ends up. When you enter "5" or something, it goes onto the main stack.
This is what most of the instructions operate on. This is also where return addresses are placed.

### Variable List
You are allowed to save stuff into variables outside of the main stack, and these are local to the
current function.

### Variable List Pointer
This value is used to maintain scope. When you enter a function it jumps up the list by the number
of local variables that the previous function had, and when you return from a function it jumps back
down again by the number of variables that function used.

### Function Table
This is a hashmap that stores functions. A function has a name and a variable count and an array of
instructions which makes up it's body. The count of variables is needed so that they can be put on
a variable stack type thing for scoping purposes. Names are not needed for the variables as this
gets optimised out.

Functions that are not implemented but are called also appear in the function table to save their
place should they eventually appear. That way, when an environment is loaded in, all it's
functions that are actually used are basically already compiled.

Each function also has a pointer to where it's instructions start in the text section so that we
can go through and change call commands to point to the location in the text and not the function in
the table.

### text section
This is where the full lot of instructions is written down.


## Instruction Types
Each built in function will have it's own instruction type, as will the secret instructions.

### JUMP
Stores an integer for a relative jump.

### JUMPIF
Stores an integer for a relative jump. consumes top of stack and jumps if it's true.

### PRECALL
Stores an integer which points to a given function in the function table to jump to. This
instruction should not get called and is just a placeholder until it can be replaced by a CALL
command.

### CALL
Puts current location on stack and then jumps absolutely to location in stored integer.

### RETURN
Takes the current number off the stack and jumps to that numbered instruction. Stores an integer
value with the number of variables this function had.

### EXTERNAL CALL
yeah this one will actually call a method that has been linked in to the vm from outside, and it
does so by taking the top item off the stack which is a number and using it to select a registered
function by numerical code.

The number of other things it takes off the stack is unknown but you should know based on what you
are calling.

### PUSH
adds a 4 byte int to the stack. When you write an int constant it does push, and when you write a
float constant it also does push but with the binary representation of a float as an int. When you
write a string constant it writes a sequence of pushes writing the string characters as ascii. The
bytes are squashed into the 4 byte numbers, but the null terminator at the end gets it's own push,
also, the null terminator is placed on the stack first and it works backwards from there.

### ADD
Obvious, it takes two items off stack and places back the addition of them. Doesn't need to store
anything.

### YIELD
This is basically a lot like return except it puts the yielded value onto the stack and then on top
of that it places the instruction number of the function that the yield is so that when you
continue the function it can skip straight to that point. Obvbiously this is going to need some
compile time shit to figure out what number it is in the function and all that.

I have realised that there are going to be some big problems with yielding and local variables as
you can not really trust in a yielding function that local variables will stay the same between
yields. Ok maybe I will just say that's intended functionality, so you cannot trust that local
variables stay consistent between yields.

### CONTINUE
counterpart of yield which also takes a number off the stack and adds that to the jump it makes,
with the idea that that number is the yield location thingo.





## Tokens to ByteCode
Yeah I know it's a bit of an extra effort that we are gonna have bytecode, but otherwise the actual
vm has to do annoying and inefficient stuff so I can not avoid it you fuckers. Ok now how do we
figure this out. hmmmm.

By the way, I call it bytecode, but it is actually 5 bytes. One byte for the instruction code type
thing. and then 4 bytes for extra shit like jump location.


### Step 1, break file into functions.
Yeah all the functions are found and their tokens are seperated out. The main body of the file is
also considered as a function but it's also kept an eye on so that we can run it automatically.
Maybe it will just get written into the function list at the end with a special magic name.

### Step 2, analyse the functions.
Yeah so for each function we must find the number of local variables used, and also check for called
functions that are not defined to add to the list

### Step 3, create function table.
For all of the functions found create entry in the function table with their name and local variable
count. Then go through and convert all of the tokens in each function into instructions.

PRECALL will have to be used instead of call at this point because we have not built the text body
and some functions may not yet be defined.

If you are just building an environment for other scripts to import then you are now done.

### Step 4, add environment (optional)
ok in this stage we take in another function table from somewhere else, and any undefined functions
that we have that are defined in that one are copied over. That is pretty much all there is to this
step. The cool thing is that because we had them in our function table already in an undefined form
they already have their own place in the table.

### Step 5, build text body (optional)
In this stage we just write the instructions in each function into one big blob and in each function
we write a pointer to where it's code starts in the blob.
