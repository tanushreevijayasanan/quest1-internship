package org.example.lisp.env;
import java.util.HashMap;
import java.util.Map;

public enum GlobalEnvironment {
    INSTANCE;
    private final Map<String, Object> bindings = new HashMap<>();

    public void define(String name, Object value) {
        bindings.put(name, value);
    }

    public Object lookup(String name) {
        return bindings.get(name);
    }

}
