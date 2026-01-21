package org.example.lisp.lexer;

public class Lexer {
    private final String input;
    private int position = 0;
    private static final char EOF_CHAR = '\0';
    private static final char LPAREN_CHAR = '(';
    private static final char RPAREN_CHAR = ')';

    public Lexer(String input) {
        this.input = input;
    }

    private char peek() {
        if (position >= input.length()) {
            return '\0';
        }
        return input.charAt(position);
    }

    private char advance() {
        return input.charAt(position++);
    }

    public Token nextToken() {
        while (Character.isWhitespace(peek())) advance();

        char c = peek();
        if (c == EOF_CHAR) 
            return new Token(TokenType.EOF, "");

        if (c == LPAREN_CHAR) {
            advance();
            return new Token(TokenType.LPAREN, "(");
        }

        if (c == RPAREN_CHAR) {
            advance();
            return new Token(TokenType.RPAREN, ")");
        }

        if (Character.isDigit(c)) {
            StringBuilder sb = new StringBuilder();
            while (Character.isDigit(peek())) sb.append(advance());
                return new Token(TokenType.NUMBER, sb.toString());
        }

        StringBuilder sb = new StringBuilder();
        while (!Character.isWhitespace(peek()) && peek() != '(' && peek() != ')' && peek() != '\0') {
           sb.append(advance());
        }

        return new Token(TokenType.SYMBOL, sb.toString());
    }

}
