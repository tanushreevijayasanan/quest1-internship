package lisp.interpreter;

import lisp.env.GlobalEnvironment;
import lisp.visitor.EvalVisitor;
import lisp.ast.Node;

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
        throw new UnsupportedOperationException("lexer and parser not implemented yet");
    }

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
    }
}