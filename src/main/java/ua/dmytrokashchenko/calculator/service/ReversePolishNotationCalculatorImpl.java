package ua.dmytrokashchenko.calculator.service;

import ua.dmytrokashchenko.calculator.domain.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

public class ReversePolishNotationCalculatorImpl implements Calculator {
    private static final Map<Element, BinaryOperator<Double>> OPERATOR_TO_FUNCTION = new HashMap<>();
    private final ReversePolishNotationParser parser;

    static {
        OPERATOR_TO_FUNCTION.put(new Element("+"), Double::sum);
        OPERATOR_TO_FUNCTION.put(new Element("-"), (operand1, operand2) -> operand1 - operand2);
        OPERATOR_TO_FUNCTION.put(new Element("*"), (operand1, operand2) -> operand1 * operand2);
        OPERATOR_TO_FUNCTION.put(new Element("/"), (operand1, operand2) -> operand1 / operand2);
        OPERATOR_TO_FUNCTION.put(new Element("^"), Math::pow);
    }

    public ReversePolishNotationCalculatorImpl(ReversePolishNotationParser parser) {
        this.parser = parser;
    }

    @Override
    public double calculate(String expression) {
        List<Element> elements = parser.parse(expression);
        while (elements.size() > 2) {
            for (int i = 2; i < elements.size(); i++) {
                if (elements.get(i).isOperand()) {
                    double value = calculateElements(elements.get(i - 2), elements.get(i - 1), elements.get(i));
                    elements.set(i - 2, new Element(Double.toString(value)));
                    elements.remove(i - 1);
                    elements.remove(i - 1);
                    break;
                }
            }
        }
        return Double.parseDouble(elements.get(0).getValue());
    }

    private double calculateElements(Element firstOperand, Element secondOperand, Element operator) {
        Double first = Double.parseDouble(firstOperand.getValue());
        Double second = Double.parseDouble(secondOperand.getValue());
        return OPERATOR_TO_FUNCTION.get(operator).apply(first, second);
    }
}
