package ua.dmytrokashchenko.calculator.service;

import ua.dmytrokashchenko.calculator.domain.Element;

import java.util.List;

public interface ReversePolishNotationParser {

    List<Element> parse(String expression);

}
