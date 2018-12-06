Scripts - 02/12/2018
====================
Ok so we want javascript scripts to be run inside the engine and that is all good and well, but we
have gotta figure out how this will work with the engine's time. I intend to use generators in
javascript for pretty much all scripts except ones that are real short.

So the way that this will work is that it initially calls the generator which returns the generator
state thingy. Then each subsequent tick of the script is just calling next on that iterator object.
