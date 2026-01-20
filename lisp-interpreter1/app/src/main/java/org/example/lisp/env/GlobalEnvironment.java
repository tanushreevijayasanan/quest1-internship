package lisp.env;
import java.util.HashMap;
import java.util.Map;

public class GlobalEnvironment {
    private final Map<String, Object> bindings;
    public GlobalEnvironment() {
        this.bindings = new HashMap<>();
    }

    public void define(String name, Object value) {
        bindings.put(name, value);
    }

    public Object lookup(String name) {
        return bindings.get(name);
    }

}
