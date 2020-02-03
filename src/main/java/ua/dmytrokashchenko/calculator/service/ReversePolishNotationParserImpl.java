package ua.dmytrokashchenko.calculator.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

public class ReversePolishNotationParserImpl implements ReversePolishNotationParser {
    private Pattern NUMBER = Pattern.compile("^[0-9]+(\\.[0-9]+)?$");
    private Pattern OPERATION = Pattern.compile("[+\\-*/]");
    private Pattern POWER = Pattern.compile("[\\^]");
    private Pattern OPENING_BRACKET = Pattern.compile("[(]");
    private Pattern CLOSING_BRACKET = Pattern.compile("[)]");

    public List<String> parse(String expression) {
        Stack<String> stack = new Stack<String>();
        List<String> result = new LinkedList<String>();



        return result;
    }
}
