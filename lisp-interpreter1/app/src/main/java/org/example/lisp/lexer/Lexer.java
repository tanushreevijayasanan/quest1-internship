package lisp.lexer;

public class Lexer {
    private final String input;
    private static int position = 0;
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
        if (c == '\0') return new Token(TokenType.EOF, "");
        if (character.isDigit(c)) {
            StringBuilder num = new StringBuilder();
            while (Character.isDigit(peek())) {
                num.append(advance());
            }
            return new Token(TokenType.NUMBER, num.toString());
        }
        if(character.isLetter(c)) {
            StringBuilder sym = new StringBuilder();
            while (Character.isLetterOrDigit(peek())) {
                sym.append(advance());
            }
            return new Token(TokenType.SYMBOL, sym.toString());
        }
        advance();
        return switch(c){
            case '+' -> new Token(TokenType.PLUS, "+");
            case '-' -> new Token(TokenType.MINUS, "-");
            case '*' -> new Token(TokenType.STAR, "*");
            case '/' -> new Token(TokenType.SLASH, "/");
            case '=' -> new Token(TokenType.EQUAL, "=");
            case ';' -> new Token(TokenType.SEMICOLON, ";");
            default -> throw new RuntimeException("Unexpected character: " + c);
        }
}
