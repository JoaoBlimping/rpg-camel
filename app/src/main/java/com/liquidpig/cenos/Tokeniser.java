package com.liquidpig.cenos;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

/**
 * Parses strings into tokens. For performance reasons I INSIST that all scripts must end with
 * whitespace which should not be a problem.
 * TODO: this file needs some love but right now what it shows us is that it works.
 */
public class Tokeniser
{
    private static final Set<Character> WHITESPACES = new HashSet<Character>(Arrays.asList(
        new Character[] {' ','\t','\n'}
    ));
    private static final char QUOTE = '"';
    private static final char COMMENT = '#';
    private static final char NEWLINE = '\n';

    private final String content;
    private int cursor = 0;
    private List<String> tokens = new LinkedList<String>();

    /**
     * Creates the parser and puts in the string to parse.
     * @param content is the content to parse.
     */
    public Tokeniser(String content)
    {
        if (!WHITESPACES.contains(content.charAt(content.length() - 1)))
        {
            throw new TokenisationException("Script must end with whitespace.");
        }
        this.content = content;
    }

    /**
     * Carries out the tokenising.
     * Basically each word is a token, but also quoted sections count as a single token, and # signs
     * delete everything until the next \n (like comments).
     * @return a list containing each token.
     */
    public List<String> tokenise()
    {
        while (this.cursor < this.content.length())
        {
            char c = content.charAt(this.cursor);
            if (WHITESPACES.contains(c)) this.cursor++;
            else if (c == QUOTE) this.quoted();
            else if (c == COMMENT) this.comment();
            else this.token();
        }
        return this.tokens;
    }

    public void quoted()
    {
        int start = this.cursor + 1;
        for (this.cursor = start; this.content.charAt(this.cursor) != QUOTE; this.cursor++)
        {
            if (this.cursor == this.content.length() - 1)
            {
                throw new TokenisationException("Quotes must be closed.");
            }
        }
        tokens.add(this.content.substring(start, this.cursor));
        this.cursor++;
    }

    public void comment()
    {
        int start = this.cursor;
        for (this.cursor = start; this.content.charAt(this.cursor) != NEWLINE; this.cursor++) {}
    }

    public void token()
    {
        int start = this.cursor;
        // TODO: yes I know.
        for (this.cursor = start; this.content.charAt(this.cursor) != QUOTE && this.content.charAt(this.cursor) != COMMENT && !WHITESPACES.contains(this.content.charAt(this.cursor)); this.cursor++) {}
        tokens.add(this.content.substring(start, this.cursor));
    }
}
