package org.example.lisp.ast;

import java.util.List;

import org.example.lisp.visitor.Visitor;
public class ListNode implements Node {
    private final List<Node> elements;

    public ListNode(List<Node> elements) {
        this.elements = List.copyOf(elements);
    }

    public List<Node> getElements() {
        return List.copyOf(elements);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
    
}
