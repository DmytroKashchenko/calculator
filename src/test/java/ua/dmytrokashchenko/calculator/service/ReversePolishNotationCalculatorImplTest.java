package ua.dmytrokashchenko.calculator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.dmytrokashchenko.calculator.domain.Element;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReversePolishNotationCalculatorImplTest {

    @Mock
    private ReversePolishNotationParser reversePolishNotationParser;

    @InjectMocks
    private ReversePolishNotationCalculatorImpl calculator;

    @Test
    public void calculate() {
        String expression = "2 + (3 * (5 ^ (2 ^ 2)) / 3)";
        List<Element> elements = Arrays.asList(
                new Element("2"), new Element("3"), new Element("5"),
                new Element("2"), new Element("2"), new Element("^"),
                new Element("^"), new Element("*"), new Element("3"),
                new Element("/"), new Element("+")
        );
        List<Element> reversePolishNotationExpression = new LinkedList<>(elements);

        when(reversePolishNotationParser.parse(expression)).thenReturn(reversePolishNotationExpression);

        Double expected = 627.0;
        Double actual = calculator.calculate(expression);

        assertEquals(expected, actual);
    }
}