package lisp.function;

import java.util.List;
import java.util.function.Function;

public class BuiltInFunction {
    private final String name;
    private final Function<List<Object>, Object> implementation;
    public BuiltInFunction(String name, Function<List<Object>, Object> implementation) {
        this.name = name;
        this.implementation = implementation;
    }

    public Object apply(List<Object> args) {
        return implementation.apply(args);
    }

    public String getName() {
        return name;
    }
}
