package ru.core.calculator.components;

import ru.core.calculator.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RPNConverter {

    public static List<String> convertToRPN(List<String> mathExpression) {

        var rpnExpression = new ArrayList<String>();
        var operatorsStack = Collections.asLifoQueue(new LinkedList<String>());

        for (var i = 0; i < mathExpression.size(); i++) {

            var token = mathExpression.get(i);

            // TODO - Refactor from if-else into tokens hierarchy or enum and tie operation with them as higher order functions
            if (token.equals(Constants.LEFT_BRACKET)) {

                operatorsStack.add(token);

            } else if (token.equals(Constants.RIGHT_BRACKET)) {

                while (!operatorsStack.isEmpty() && !operatorsStack.peek().equals(Constants.LEFT_BRACKET)) {
                    rpnExpression.add(operatorsStack.remove());
                }

                operatorsStack.remove();

            } else if (Operator.supportedOperators.containsKey(token) ) {

                if (Operator.potentialUnaryOperators.containsKey(token) && isUnaryOperator(i, mathExpression)) {
                    token = Operator.potentialUnaryOperators.get(token).symbol;
                }

                while (!operatorsStack.isEmpty() && Operator.supportedOperators.containsKey(operatorsStack.peek())) {

                    Operator currentOperator = Operator.supportedOperators.get(token);
                    Operator lastOperator = Operator.supportedOperators.get(operatorsStack.peek());

                    if (currentOperator.comparePrecedence(lastOperator) <= 0) {

                        rpnExpression.add(operatorsStack.remove());
                        continue;
                    }
                    break;
                }

                operatorsStack.add(token);

            } else {
                rpnExpression.add(token);
            }
        }

        while (!operatorsStack.isEmpty()) {
            rpnExpression.add(operatorsStack.remove());
        }

        return rpnExpression;
    }

    private static boolean isUnaryOperator(int operatorIndex, List<String> mathExpression) {

        while (operatorIndex > 0) {
            String previousToken = mathExpression.get(operatorIndex - 1);

            if (Operator.supportedOperators.containsKey(previousToken)) {
                return true;
            } else if (Character.isDigit(previousToken.charAt(0))) {
                return false;
            }
            operatorIndex--;
        }

        return true;
    }
}
