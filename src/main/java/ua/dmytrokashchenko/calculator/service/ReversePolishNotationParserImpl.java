package ua.dmytrokashchenko.calculator.service;

import ua.dmytrokashchenko.calculator.domain.Element;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReversePolishNotationParserImpl implements ReversePolishNotationParser {
    private static final Pattern ELEMENT = Pattern.compile("([0-9]+(\\.[0-9]+)?)|([(])|([)])|([+\\-])|([*/])|([\\^])");

    public List<Element> parse(String expression) {
        checkExpression(expression);
        expression = prepareExpression(expression);
        List<Element> elements = splitByElement(expression);
        return getNotationFromElements(elements);
    }

    private List<Element> splitByElement(String expression) {
        List<Element> result = new LinkedList<>();
        Matcher matcher = ELEMENT.matcher(expression);
        while (matcher.find()) {
            result.add(new Element(matcher.group()));
        }
        handleNegativeValues(result);
        return result;
    }

    private void handleNegativeValues(List<Element> result) {
        if (result.get(0).getValue().equals("-")) {
            result.add(0, new Element("0"));
        }
        for (int i = 1; i < result.size(); i++) {
            if (result.get(i).getValue().matches("[+\\-*/]")
                    && result.get(i - 1).getValue().matches("[+\\-*/]")) {
                result.get(i + 1).negate();
                result.set(i, null);
            }
        }
        result.removeAll(Collections.singleton(null));
    }

    private void checkExpression(String expression) {
        if (Objects.isNull(expression)) {
            throw new IllegalArgumentException("Empty expression");
        }
    }

    private String prepareExpression(String expression) {
        expression = expression.replaceAll(",", ".");
        expression = expression.replaceAll(" ", "");
        return expression;
    }

    private List<Element> getNotationFromElements(List<Element> elements) {
        Stack<Element> stack = new Stack<>();
        List<Element> result = new LinkedList<>();

        for (Element element : elements) {
            if (!element.isOperand()) {
                result.add(element);
                continue;
            }
            if (!stack.empty()) {
                if (element.getValue().equals("(")) {
                    stack.push(element);
                    continue;
                }
                if (element.getValue().equals(")")) {
                    while (!stack.empty() && !stack.peek().getValue().equals("(")) {
                        result.add(stack.pop());
                    }
                    if (!stack.empty() && stack.peek().getValue().equals("(")) {
                        stack.pop();
                    }
                    continue;
                }
                while (!stack.empty() && element.getPriority() <= stack.peek().getPriority()) {
                    result.add(stack.pop());
                }
            }
            stack.push(element);
        }
        while (!stack.empty()) {
            result.add(stack.pop());
        }
        result.removeAll(Arrays.asList(new Element("("), new Element(")")));
        return result;
    }
}
