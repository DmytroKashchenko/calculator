package ua.dmytrokashchenko.calculator.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Element {
    private static final Pattern NUMBER = Pattern.compile("[0-9]+(\\.[0-9]+)?");

    private static final Pattern OPERATION_ZERO_PRIORITY = Pattern.compile("[(]");

    private static final Pattern OPERATION_FIRST_PRIORITY = Pattern.compile("[)]");

    private static final Pattern OPERATION_SECOND_PRIORITY = Pattern.compile("[+\\-]");

    private static final Pattern OPERATION_THIRD_PRIORITY = Pattern.compile("[*/]");

    private static final Pattern OPERATION_FOURTH_PRIORITY = Pattern.compile("[\\^]");

    private String value;
    private int priority;
    private boolean isOperand;

    public Element(String value) {
        this.value = value;
        if (NUMBER.matcher(value).find()) {
            priority = -1;
            isOperand = false;
        } else if (OPERATION_ZERO_PRIORITY.matcher(value).find()) {
            priority = 0;
            isOperand = true;
        } else if (OPERATION_FIRST_PRIORITY.matcher(value).find()) {
            priority = 1;
            isOperand = true;
        } else if (OPERATION_SECOND_PRIORITY.matcher(value).find()) {
            priority = 2;
            isOperand = true;
        } else if (OPERATION_THIRD_PRIORITY.matcher(value).find()) {
            priority = 3;
            isOperand = true;
        } else if (OPERATION_FOURTH_PRIORITY.matcher(value).find()) {
            priority = 4;
            isOperand = true;
        } else {
            throw new IllegalArgumentException();
        }

    }

    public String getValue() {
        return value;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isOperand() {
        return isOperand;
    }

    public void negate() {
        if (this.isOperand) {
            throw new UnsupportedOperationException();
        }
        this.value = "-" + this.value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Objects.equals(value, element.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
