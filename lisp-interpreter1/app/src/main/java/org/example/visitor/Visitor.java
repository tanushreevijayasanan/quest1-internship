package lisp.visitor;
import lisp.ast.*;
public interface Visitor<T> {
    T visit(SymbolNode symbolNode);
    T visit(NumberNode numberNode);
    T visit(ListNode listNode);
}
