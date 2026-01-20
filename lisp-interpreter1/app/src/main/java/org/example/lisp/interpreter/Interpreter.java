package lisp.interpreter;

import lisp.env.GlobalEnvironment;
import lisp.visitor.EvalVisitor;
import lisp.ast.Node;

public class Interpreter{
    private final GlobalEnvironment globalEnv;
    private final EvalVisitor evalVisitor;

    public Interpreter() {
        this.globalEnv = new GlobalEnvironment();
        this.evalVisitor = new EvalVisitor(globalEnv);

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

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        System.out.println(interpreter.eval("(+ 1 2 3)"));        
        System.out.println(interpreter.eval("(* 2 3 4)"));        
        System.out.println(interpreter.eval("(define x 10)"));
        System.out.println(interpreter.eval("(+ x 5)"));          
        System.out.println(interpreter.eval("(if (< 3 5) 100 200)"));
    }
}