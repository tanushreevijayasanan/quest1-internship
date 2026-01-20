package org.example.lisp.ast;

import org.example.lisp.visitor.Visitor;
import java.util.List;
public class ListNode implements Node {
    private final List<Node> elements;

    public ListNode(List<Node> elements) {
        this.elements = elements;
    }

    public List<Node> getElements() {
        return elements;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
    
}
