package org.example.lisp.ast;
import org.example.lisp.visitor.Visitor;
public interface Node {
    <T> T accept(Visitor<T> visitor);
}
