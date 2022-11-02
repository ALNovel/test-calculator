package ru.core.calculator.components;

import java.util.Scanner;

// TODO - Use a proper logger insted of sout
public class Console {

    static Scanner in = new Scanner(System.in);
    static String initialMessage = "Please, input a valid mathematical expression:";

    static String resultMessage = "Result of your calculation: ";
    static String errorMessage = "Entered mathematical expression is incorrect, please check its validity.\nClosing terminal";

    static public String getUserInput() {
        return in.nextLine();
    }

    static public void displayInitialMessage() {
        System.out.println(initialMessage);
    }

    static public void displayResultMessage(String result) {
        System.out.println(resultMessage + result);
    }

    static public void displayErrorMessage() {
        System.out.println(errorMessage);
    }

}
