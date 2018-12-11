package com.liquidpig.cenos;

/**
 * An exception that gets thrown when tokenisation goes wrong which basically means that your string is not a valid
 * script and we have something that is stopping us from even making it into tokens correctly.
 */
public class TokenisationException extends RuntimeException
{
    /**
     * Puts the error message into the superclass.
     * @param message is the details of the exception.
     */
    public TokenisationException(String message)
    {
        super(message);
    }
}
