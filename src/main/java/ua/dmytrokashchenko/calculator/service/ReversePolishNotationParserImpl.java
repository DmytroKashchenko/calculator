package ua.dmytrokashchenko.calculator.service;

import ua.dmytrokashchenko.calculator.domain.Element;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReversePolishNotationParserImpl implements ReversePolishNotationParser {
    private static final Pattern ELEMENT = Pattern.compile("([0-9]+(\\.[0-9]+)?)|([(])|([)])|([+\\-])|([*/])|([*/])|([\\^])");

    public List<Element> parse(String expression) {
        checkExpression(expression);
        expression = prepareExpression(expression);

        Stack<Element> stack = new Stack<>();
        List<Element> result = new LinkedList<>();

        List<Element> elements = splitByElement(expression);


        for (Element element : elements) {
            if (!element.isOperand()) {
                result.add(element);
//                System.out.println("Current element: " + element);
//                System.out.println("Stack now: " + stack);
//                System.out.println("This is result string: " + result);
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
                    continue;
                }

                while (!stack.empty() && element.getPriority() >= stack.peek().getPriority()) {
                    // операція виштовхує зі стека всі попередні операції з більшим або однаковим пріоритетом у вихідний рядок;
                    result.add(stack.pop());
                }
            }
            stack.push(element);

//            System.out.println("Current element: " + element);
//            System.out.println("Stack now: " + stack);
//            System.out.println("This is result string: " + result);
        }


        while (!stack.empty()) {
            result.add(stack.pop());
        }


        result.removeAll(Arrays.asList(new Element("("), new Element(")")));

        System.out.println(stack);
        System.out.println(result);

        return result;
    }


    private List<Element> splitByElement(String expression) {
        List<Element> result = new LinkedList<>();
        Matcher matcher = ELEMENT.matcher(expression);
        while (matcher.find()) {
            result.add(new Element(matcher.group()));
        }
        return result;
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

}
