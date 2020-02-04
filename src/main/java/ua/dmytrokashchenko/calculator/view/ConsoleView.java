package ua.dmytrokashchenko.calculator.view;

import ua.dmytrokashchenko.calculator.service.Calculator;
import ua.dmytrokashchenko.calculator.service.ConsoleExpressionReader;
import ua.dmytrokashchenko.calculator.service.ExpressionReader;

public class ConsoleView {
    private static final ExpressionReader CONSOLE_EXPRESSION_READER = new ConsoleExpressionReader();
    private final Calculator calculator;

    public ConsoleView(Calculator calculator) {
        this.calculator = calculator;
    }

    public void run() {
        System.out.println("Enter your math expression:");
        double result = calculator.calculate(CONSOLE_EXPRESSION_READER.readInput());
        System.out.println("Result: " + result);

    }
}
