package lisp.parser;

import java.util.ArrayList;
import java.util.List;

import lisp.lexer.*;
import lisp.ast.*;

public class Parser {
    private final Lexer lexer;
    private Token currentToken;

    Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.nextToken();
    }

    public void advance() {
        currentToken = lexer.nextToken();
    }

    public Node parseExpression() {
        if (currentToken.getType() == TokenType.NUMBER){
            Node n = new NumberNode(Double.parseDouble(currentToken.getLexeme()));
            advance();
            return n;
        }

        if (currentToken.getType() == TokenType.SYMBOL){
            Node n = new SymbolNode(currentToken.getLexeme());
            advance();
            return n;
        }

        if (currentToken.getType() == TokenType.LPAREN){
            advance();
            List<Node> elements = new ArrayList<>();
            while (currentToken.getType() != TokenType.RPAREN){
                elements.add(parseExpression());
            }
            advance();
            return new ListNode(elements);
        }
        throw new RuntimeException("unexpected token: " + currentToken);
    }
}
