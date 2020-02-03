package ua.dmytrokashchenko.calculator;

import ua.dmytrokashchenko.calculator.service.ConsoleExpressionReader;
import ua.dmytrokashchenko.calculator.service.ExpressionReader;

public class Main {
    public static void main(String[] args) {
        ExpressionReader expressionReader = new ConsoleExpressionReader();
        String s = expressionReader.readInput();
        System.out.println(s);
    }
}
