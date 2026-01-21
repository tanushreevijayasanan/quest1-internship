package org.example.lisp.interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class InterpreterTest {

    @Test
    void addsNumbers() {
        Interpreter interpreter = new Interpreter();
        Object result = interpreter.evalAll("(+ 1 2)");
        assertEquals(3.0, result);
    }

    @Test
    void subtractsNumbers() {
        Interpreter interpreter = new Interpreter();
        Object result = interpreter.evalAll("(- 5 2)");
        assertEquals(3.0, result);
    }

    @Test
    void multipliesNumbers() {
        Interpreter interpreter = new Interpreter();
        Object result = interpreter.evalAll("(* 2 3)");
        assertEquals(6.0, result);
    }

    @Test
    void dividesNumbers() {
        Interpreter interpreter = new Interpreter();
        Object result = interpreter.evalAll("(/ 6 2)");
        assertEquals(3.0, result);
    }
}
    