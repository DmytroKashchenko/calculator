package ua.dmytrokashchenko.calculator;

import ua.dmytrokashchenko.calculator.service.ConsoleExpressionReader;
import ua.dmytrokashchenko.calculator.service.ExpressionReader;
import ua.dmytrokashchenko.calculator.service.ReversePolishNotationParser;
import ua.dmytrokashchenko.calculator.service.ReversePolishNotationParserImpl;

public class Main {
    public static void main(String[] args) {
        ExpressionReader expressionReader = new ConsoleExpressionReader();
        ReversePolishNotationParser parser = new ReversePolishNotationParserImpl();
        String s = expressionReader.readInput();
        parser.parse(s);
    }
}
