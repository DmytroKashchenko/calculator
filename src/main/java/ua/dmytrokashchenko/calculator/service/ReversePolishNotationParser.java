package ua.dmytrokashchenko.calculator.service;

import java.util.List;

public interface ReversePolishNotationParser {

    List<String> parse(String expression);

}
