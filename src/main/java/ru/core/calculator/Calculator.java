package ru.core.calculator;

import ru.core.calculator.components.Operator;
import ru.core.calculator.components.RPNConverter;
import ru.core.calculator.components.Tokenizer;
import ru.core.calculator.components.Validator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Calculator {
    public String evaluateUserInput(String userInput) {

        Validator.validateUserInput(userInput);
        List<String> tokens = Tokenizer.tokenizeMathExpressionString(userInput);
        List<String> rpnExpressionTokens = RPNConverter.convertToRPN(tokens);

        return evaluateRPNExpression(rpnExpressionTokens);

    }

    public String evaluateRPNExpression(List<String> rpnExpression) {

        var evaluationStack = Collections.asLifoQueue(new LinkedList<String>());

        for (var token : rpnExpression) {
            if (Operator.supportedOperators.containsKey(token)) {
                Operator.supportedOperators.get(token).computeFunction.accept(evaluationStack);
            } else {
                evaluationStack.add(token);
            }
        }

        return evaluationStack.remove();
    }
}
