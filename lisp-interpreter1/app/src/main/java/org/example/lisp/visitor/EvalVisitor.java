package org.example.lisp.visitor;
import java.util.List;
import java.util.stream.Collectors;

import org.example.lisp.ast.ListNode;
import org.example.lisp.ast.Node;
import org.example.lisp.ast.NumberNode;
import org.example.lisp.ast.SymbolNode;
import org.example.lisp.env.GlobalEnvironment;
import org.example.lisp.function.BuiltInFunction;

public class EvalVisitor implements Visitor<Object>{
    private final GlobalEnvironment globalEnv;
    private static final int if_syntaxSize = 3;

    
    public EvalVisitor() {
        this.globalEnv = GlobalEnvironment.INSTANCE;
    }

    @Override
    public Object visit(SymbolNode symbolNode){
        Object value = globalEnv.lookup(symbolNode.getName());
        if (value == null) {
            throw new RuntimeException("undefined symbol " + symbolNode.getName());
        }
        return value;
    }

    @Override
    public Object visit(NumberNode numberNode){
        return numberNode.getValue();
    }

    @Override
    public Object visit(ListNode listNode){
        List<Node> elements = listNode.getElements();
        if (elements.isEmpty()) {
            throw new RuntimeException("can't evaluate empty list");
        }

        Node first = elements.get(0);
        Object firstEval;
        if (first instanceof SymbolNode){
            firstEval = globalEnv.lookup(((SymbolNode) first).getName());
            String name = ((SymbolNode) first).getName();
            switch(name){
                case "define":
                    return handleDefine(elements.subList(1, elements.size()));
                case "if":
                    return handleIf(elements.subList(1, elements.size()));
                default:
                    break;
            }
        } else{
            firstEval = first.accept(this);
        }

        if (firstEval instanceof BuiltInFunction){
            BuiltInFunction func = (BuiltInFunction) firstEval;
            List<Object> args = elements.subList(1, elements.size()).stream()
                    .map(node -> node.accept(this))
                    .collect(Collectors.toList());
            return func.apply(args);
        } else{
            throw new RuntimeException("first element is not a function");
        }
    }
    private Object handleDefine(List<Node> args){
        if (args.size() != 2 || !(args.get(0) instanceof SymbolNode)){
            throw new RuntimeException("invalid define syntax");
        }
        String name = ((SymbolNode) args.get(0)).getName();
        Object value = args.get(1).accept(this);
        globalEnv.define(name, value);
        return value;
    }

    private Object handleIf(List<Node> args){
        if (args.size() != if_syntaxSize) {
            throw new RuntimeException("invalid if syntax");
        }
        Object condition = args.get(0).accept(this);
        boolean cond;
        if(condition instanceof Boolean){
            cond = (Boolean) condition;
        } else if(condition instanceof Double){
            cond = ((Double) condition) != 0.0;
        } else{
            throw new RuntimeException("if condition must be boolean or double");
        }
        return cond ? args.get(1).accept(this) : args.get(2).accept(this);
    }
}