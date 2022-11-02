package ru.core.calculator;

import ru.core.calculator.components.Console;

public class App
{
    public static void main( String[] args )
    {
        Calculator calculator = new Calculator();

        try {
            Console.displayInitialMessage();
            String calculationResult = calculator.evaluateUserInput(Console.getUserInput());
            Console.displayResultMessage(calculationResult);
        } catch (Exception ex) {
            // TODO - Implement proper exception handling with responsive message
            Console.displayErrorMessage();
        }
    }
}
