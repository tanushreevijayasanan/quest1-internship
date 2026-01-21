package org.example.lisp.parser;

import java.util.ArrayList;
import java.util.List;

import org.example.lisp.ast.ListNode;
import org.example.lisp.ast.Node;
import org.example.lisp.ast.NumberNode;
import org.example.lisp.ast.SymbolNode;
import org.example.lisp.lexer.Lexer;
import org.example.lisp.lexer.Token;
import org.example.lisp.lexer.TokenType;

public class Parser {
    private final Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.nextToken();
    }

    public void advance() {
        currentToken = lexer.nextToken();
    }

    public List<Node> parseAll(){
        List<Node> expressions = new ArrayList<>();
        while (currentToken.getType() != TokenType.EOF){
            expressions.add(parseExpression());
        }
        return expressions;
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
