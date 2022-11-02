package ru.core.calculator.components;

import ru.core.calculator.utils.Constants;

import java.util.HashMap;
import java.util.Queue;
import java.util.function.Consumer;

public enum Operator{

    ADDITION(Constants.PLUS_SIGN, 0, stack -> {
        var x = Double.parseDouble(stack.remove());
        var y = Double.parseDouble(stack.remove());
        stack.add(Double.toString(x + y));
    }),
    SUBTRACTION(Constants.MINUS_SIGN, 0, stack -> {
        var x = Double.parseDouble(stack.remove());
        var y = Double.parseDouble(stack.remove());
        stack.add(Double.toString(y - x));
    }),
    MULTIPLICATION(Constants.MULTIPLY_SIGN, 5, stack -> {
        var x = Double.parseDouble(stack.remove());
        var y = Double.parseDouble(stack.remove());
        stack.add(Double.toString(x * y));
    }),
    DIVISION(Constants.DIVIDE_SIGN, 5, stack -> {
        var x = Double.parseDouble(stack.remove());
        var y = Double.parseDouble(stack.remove());
        stack.add(Double.toString(y / x));
    }),
    NEGATION(Constants.UNARY_MINUS_ALIAS, 10, stack -> {
        var x = Double.parseDouble(stack.remove());
        stack.add(Double.toString(-x));
    });

    final public String symbol;
    final int precedence;
    final public Consumer<Queue<String>> computeFunction;


    public static final HashMap<String, Operator> supportedOperators = new HashMap<>();
    public static final HashMap<String, Operator> potentialUnaryOperators = new HashMap<>();

    static {
        for (Operator operator : Operator.values()) {
            supportedOperators.put(operator.symbol, operator);
        }

        potentialUnaryOperators.put(Constants.MINUS_SIGN, NEGATION);
    }

    Operator(String symbol, int precedence, Consumer<Queue<String>> computeFunction) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.computeFunction = computeFunction;
    }

    // TODO - define Comparator instead of pseudo-Comparator function
    // (Comparable compareTo is final in Enums, so can't use that.)
    public int comparePrecedence(Operator operator) {
        return this.precedence - operator.precedence;
    }
}
