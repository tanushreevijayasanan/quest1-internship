package lisp.lexer;

public class Token {
    private TokenType type;
    private String lexeme;

    Token(TokenType type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }

    public String toString() {
        return type + "('" + lexeme + "')";
    }

}
