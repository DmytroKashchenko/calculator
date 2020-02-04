package ua.dmytrokashchenko.calculator.service;

import org.junit.Test;
import ua.dmytrokashchenko.calculator.domain.Element;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReversePolishNotationParserImplTest {

    @Test
    public void parseShouldReturnReversePolishNotationForExpression() {
        String expression = "2 + (3 * (5 ^ (2 ^ 2)) / 3)";
        List<Element> expected = Arrays.asList(
                new Element("2"), new Element("3"), new Element("5"),
                new Element("2"), new Element("2"), new Element("^"),
                new Element("^"), new Element("*"), new Element("3"),
                new Element("/"), new Element("+")
        );

        ReversePolishNotationParser reversePolishNotationParser = new ReversePolishNotationParserImpl();
        List<Element> actual = reversePolishNotationParser.parse(expression);

        assertEquals(expected, actual);
    }
}