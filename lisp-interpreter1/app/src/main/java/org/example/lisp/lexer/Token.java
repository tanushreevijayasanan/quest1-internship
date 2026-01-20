package org.example.lisp.lexer;

public class Token {
    private TokenType type;
    private String lexeme;

    Token(TokenType type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }

    public TokenType getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public String toString() {
        return type + "('" + lexeme + "')";
    }

}
