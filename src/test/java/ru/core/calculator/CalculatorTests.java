package ru.core.calculator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTests {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Addition should work correctly")
    public void testAddition() {
        Assertions.assertEquals(calculator.evaluateUserInput("4+5"), "9.0");
    }

    @Test
    @DisplayName("Subtraction should work correctly")
    public void testSubtraction() {
        Assertions.assertEquals(calculator.evaluateUserInput("40-5"), "35.0");
    }

    @Test
    @DisplayName("Multiplication should work correctly")
    public void testMultiplication() {
        Assertions.assertEquals(calculator.evaluateUserInput("4*5"), "20.0");
    }


    @Test
    @DisplayName("Division should work correctly")
    public void testDivision() {
        Assertions.assertEquals(calculator.evaluateUserInput("40/5"), "8.0");
    }

    @Test
    @DisplayName("Negation in first operator should work correctly")
    public void testNegationFirst() {
        Assertions.assertEquals(calculator.evaluateUserInput("-4-5"), "-9.0");
    }

    @Test
    @DisplayName("Negation in the middle of expression should work correctly")
    public void testNegationMiddle() {
        Assertions.assertEquals(calculator.evaluateUserInput("4--5"), "9.0");
    }

    @Test
    @DisplayName("Negation in the middle of expression with brackets should work correctly")
    public void testNegationBrackets() {
        Assertions.assertEquals(calculator.evaluateUserInput("4+(-5)"), "-1.0");
    }

    @Test
    @DisplayName("Decimal and integer input should work together correctly")
    public void testDecimalIntegerCombinationInput() {
        Assertions.assertEquals(calculator.evaluateUserInput("4.3*0.5"), "2.15");
    }

    @Test
    @DisplayName("Decimal input operations should work correctly")
    public void testDecimal() {
        Assertions.assertEquals(calculator.evaluateUserInput("4/0.5"), "8.0");
    }

    @Test
    @DisplayName("Expressions with brackets should work correctly")
    public void testBrackets() {
        Assertions.assertEquals(calculator.evaluateUserInput("(-(4+3)*12-(-4/(-2.0)))"), "-86.0");
    }

    @Test
    @DisplayName("Expressions with whitespaces should work correctly")
    public void testSpaces() {
        Assertions.assertEquals(calculator.evaluateUserInput("(-(4  +3)* 12-( -4/(-2   .0)) )"), "-86.0");
    }

}
