package org.example.lisp.interpreter;

import org.example.lisp.env.GlobalEnvironment;
import org.example.lisp.visitor.EvalVisitor;
import org.example.lisp.ast.Node;
import org.example.lisp.lexer.Lexer;
import org.example.lisp.parser.Parser;
import org.example.lisp.function.BuiltInFunction;


public class Interpreter{
    private final GlobalEnvironment globalEnv;
    private final EvalVisitor evalVisitor;

    public Interpreter() {
        this.globalEnv = GlobalEnvironment.INSTANCE;
        this.evalVisitor = new EvalVisitor();

        registerBuiltIns();
    }

    public void registerBuiltIns(){
        globalEnv.define("+", new BuiltInFunction("+", args -> {
            double sum = 0;
            for (Object arg : args) {
                sum += (Double) arg;
            }
            return sum;
        }));

        globalEnv.define("-", new BuiltInFunction("-", args -> {
            if (args.isEmpty()) {
                throw new RuntimeException("'-' requires at least one argument");
            }
            double result = (Double) args.get(0);
            for (int i = 1; i < args.size(); i++) {
                result -= (Double) args.get(i);
            }
            return result;
        }));

        globalEnv.define("*", new BuiltInFunction("*", args -> {
            double product = 1;
            for (Object arg : args) {
                product *= (Double) arg;
            }
            return product;
        }));

        globalEnv.define("/", new BuiltInFunction("/", args -> {
            if (args.isEmpty()) {
                throw new RuntimeException("'/' requires at least one argument");
            }
            double result = (Double) args.get(0);
            for (int i = 1; i < args.size(); i++) {
                result /= (Double) args.get(i);
            }
            return result;
        }));

        globalEnv.define("=", new BuiltInFunction("=", args -> {
            if (args.size() != 2) {
                throw new RuntimeException("'=' requires exactly two arguments");
            }
            return args.get(0).equals(args.get(1));
        }));

        globalEnv.define("<", new BuiltInFunction("<", args -> {
            if (args.size() != 2) {
                throw new RuntimeException("'<' requires exactly two arguments");
            }
            return (Double) args.get(0) < (Double) args.get(1);
        }));

        globalEnv.define(">", new BuiltInFunction(">", args -> {
            if (args.size() != 2) {
                throw new RuntimeException("'>' requires exactly two arguments");
            }
            return (Double) args.get(0) > (Double) args.get(1);
        }));
    }

    public Object eval(String source){
        Lexer lexer = new Lexer(source);
        Parser parser = new Parser(lexer);
        Node ast = parser.parseExpression();
        return ast.accept(evalVisitor);
    }

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        String source = "(+ 1 2 3 4 5)";
        Object result = interpreter.eval(source);
        System.out.println("Result: " + result);
    }
}