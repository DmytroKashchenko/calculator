package ua.dmytrokashchenko.calculator.service;

import java.util.Scanner;

public class ConsoleExpressionReader implements ExpressionReader {
    public String readInput() {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        scanner.close();
        return expression;
    }
}
