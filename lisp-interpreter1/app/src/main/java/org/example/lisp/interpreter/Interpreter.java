package org.example.lisp.interpreter;
import java.util.List;
import java.util.Scanner;

import org.example.lisp.ast.Node;
import org.example.lisp.env.GlobalEnvironment;
import org.example.lisp.function.BuiltInFunction;
import org.example.lisp.lexer.Lexer;
import org.example.lisp.parser.Parser;
import org.example.lisp.visitor.EvalVisitor;
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
                double divisor = (Double) args.get(i);
                if (divisor == 0) {
                    throw new RuntimeException("division by zero");
                }
                result /= divisor;
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
    
    public Object evalAll(String source){
        Lexer lexer = new Lexer(source);
        Parser parser = new Parser(lexer);
        List<Node> expressions = parser.parseAll();
        Object result = null;
        for(Node ast : expressions){
            result = ast.accept(evalVisitor);
        }
        return result;
    }
    public void repl(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to the Lisp Interpreter. type 'exit' to quit.");
        while (true){
            System.out.println("lisp >");
            if (!scanner.hasNextLine()){
                break;
            }
            String input = scanner.nextLine().trim();
            if (input.equals("exit")){
                System.out.println("bye bye!");
                break;
            }   
            if (input.isEmpty()){
                continue;
            }
            try{
                Object result = evalAll(input);
                System.out.println("=> " + result);
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        interpreter.repl();
    }
}