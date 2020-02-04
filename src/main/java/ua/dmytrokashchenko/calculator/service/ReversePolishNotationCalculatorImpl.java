package ua.dmytrokashchenko.calculator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.dmytrokashchenko.calculator.domain.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

public class ReversePolishNotationCalculatorImpl implements Calculator {
    private static final Map<Element, BinaryOperator<Double>> OPERATOR_TO_FUNCTION = new HashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(ReversePolishNotationCalculatorImpl.class);

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

        boolean isDifficult = elements.size() > 3;
        if (isDifficult) {
            LOGGER.debug("The expression for calculation: " + expression);
            LOGGER.debug("Reverse Polish Notation: " + elements);
        }

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

        double result = Double.parseDouble(elements.get(0).getValue());

        if (isDifficult) {
            LOGGER.debug("Result: " + result);
        }

        return result;
    }

    private double calculateElements(Element firstOperand, Element secondOperand, Element operator) {
        Double first = Double.parseDouble(firstOperand.getValue());
        Double second = Double.parseDouble(secondOperand.getValue());
        return OPERATOR_TO_FUNCTION.get(operator).apply(first, second);
    }
}
