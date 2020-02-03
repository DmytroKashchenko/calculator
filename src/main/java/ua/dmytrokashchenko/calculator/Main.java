package ua.dmytrokashchenko.calculator;

import ua.dmytrokashchenko.calculator.service.*;

public class Main {
    public static void main(String[] args) {
        ExpressionReader expressionReader = new ConsoleExpressionReader();
        ReversePolishNotationParser parser = new ReversePolishNotationParserImpl();
        Calculator calculator = new ReversePolishNotationCalculatorImpl(parser);
        double result = calculator.calculate(expressionReader.readInput());
        System.out.println("Result: " + result);
    }
}
