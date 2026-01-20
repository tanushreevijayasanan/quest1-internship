package org.example.lisp.visitor;
import org.example.lisp.ast.NumberNode;
import org.example.lisp.ast.SymbolNode;
import org.example.lisp.ast.ListNode;

public interface Visitor<T> {
    T visit(SymbolNode symbolNode);
    T visit(NumberNode numberNode);
    T visit(ListNode listNode);
}
