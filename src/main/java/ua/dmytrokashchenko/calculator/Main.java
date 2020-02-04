package ua.dmytrokashchenko.calculator;

import ua.dmytrokashchenko.calculator.service.Calculator;
import ua.dmytrokashchenko.calculator.service.ReversePolishNotationCalculatorImpl;
import ua.dmytrokashchenko.calculator.service.ReversePolishNotationParser;
import ua.dmytrokashchenko.calculator.service.ReversePolishNotationParserImpl;
import ua.dmytrokashchenko.calculator.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ReversePolishNotationParser parser = new ReversePolishNotationParserImpl();
        Calculator calculator = new ReversePolishNotationCalculatorImpl(parser);
        ConsoleView consoleView = new ConsoleView(calculator);
        consoleView.run();
    }
}
