package ru.core.calculator.components;

import ru.core.calculator.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    public static List<String> tokenizeMathExpressionString(String input) {
        List<String> tokens = new ArrayList<>();
        String preProcessedInput = input.replaceAll(" ", "");

        boolean isParsingOperand = false;
        int startOfOperandIndex = 0;

        for (var i = 0; i < preProcessedInput.length(); i++) {
            char currentChar = preProcessedInput.charAt(i);

            // TODO - Simplify logical conditions
            if (!isParsingOperand && Character.isDigit(currentChar)) {
                startOfOperandIndex = i;
                isParsingOperand = true;
            } else if (isParsingOperand &&
                    !(Character.isDigit(currentChar) || Character.toString(currentChar).equals(Constants.DECIMAL_POINT))) {
                tokens.add(preProcessedInput.substring(startOfOperandIndex, i));
                tokens.add(Character.toString(currentChar));
                isParsingOperand = false;
            } else if (!(Character.isDigit(currentChar) || Character.toString(currentChar).equals(Constants.DECIMAL_POINT))) {
                tokens.add(Character.toString(currentChar));
            }
        }

        if (isParsingOperand) {
            int lastIndex = preProcessedInput.length();
            tokens.add(preProcessedInput.substring(startOfOperandIndex, lastIndex));
        }

        return tokens;
    }
}
