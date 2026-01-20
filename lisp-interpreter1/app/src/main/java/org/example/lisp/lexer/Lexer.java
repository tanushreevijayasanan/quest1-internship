package lisp.lexer;

public class Lexer {
    private final String input;
    private int position = 0;
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

    Token nextToken() {
        while (Character.isWhitespace(peek())) advance();

        char c = peek();
        if (c == '\0') 
            return new Token(TokenType.EOF, "");

        if (c == '(') {
            advance();
            return new Token(TokenType.LPAREN, "(");
        }

        if (c == ')') {
            advance();
            return new Token(TokenType.RPAREN, ")");
        }

        if (Character.isDigit(c)) {
            StringBuilder sb = new StringBuilder();
            while (Character.isDigit(peek())) sb.append(advance());
                return new Token(TokenType.NUMBER, sb.toString());
        }

        StringBuilder sb = new StringBuilder();
        while (!Character.isWhitespace(peek()) &&
           peek() != '(' &&
           peek() != ')' &&
           peek() != '\0') {
           sb.append(advance());
        }

        return new Token(TokenType.SYMBOL, sb.toString());
    }

}
