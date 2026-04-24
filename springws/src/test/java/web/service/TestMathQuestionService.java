package web.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMathQuestionService {

	@Test
	public void testQ1AdditionValidIntegers() {
		assertEquals(Double.valueOf(3.0), MathQuestionService.q1Addition("1", "2"));
	}

	@Test
	public void testQ1AdditionValidDecimals() {
		assertEquals(Double.valueOf(4.0), MathQuestionService.q1Addition("1.5", "2.5"));
	}

	@Test
	public void testQ1AdditionEmptyNumber1() {
		assertNull(MathQuestionService.q1Addition("", "2"));
	}

	@Test
	public void testQ1AdditionEmptyNumber2() {
		assertNull(MathQuestionService.q1Addition("2", ""));
	}

	@Test
	public void testQ1AdditionNullInputs() {
		assertNull(MathQuestionService.q1Addition(null, null));
	}

	@Test
	public void testQ1AdditionNonNumericInput() {
		assertNull(MathQuestionService.q1Addition("abc", "2"));
	}

	@Test
	public void testQ2SubtractionValidIntegers() {
		assertEquals(Double.valueOf(2.0), MathQuestionService.q2Subtraction("5", "3"));
	}

	@Test
	public void testQ2SubtractionNegativeAnswer() {
		assertEquals(Double.valueOf(-2.0), MathQuestionService.q2Subtraction("3", "5"));
	}

	@Test
	public void testQ2SubtractionInvalidInput() {
		assertNull(MathQuestionService.q2Subtraction("x", "5"));
	}

	@Test
	public void testQ3MultiplicationValidIntegers() {
		assertEquals(Double.valueOf(12.0), MathQuestionService.q3Multiplication("3", "4"));
	}

	@Test
	public void testQ3MultiplicationWithZero() {
		assertEquals(Double.valueOf(0.0), MathQuestionService.q3Multiplication("0", "7"));
	}

	@Test
	public void testQ3MultiplicationInvalidInput() {
		assertNull(MathQuestionService.q3Multiplication("7", "abc"));
	}

	@Test
	public void testIsCorrectAnswerTrue() {
		assertTrue(MathQuestionService.isCorrectAnswer("8", 8.0));
	}

	@Test
	public void testIsCorrectAnswerFalse() {
		assertFalse(MathQuestionService.isCorrectAnswer("9", 8.0));
	}

	@Test
	public void testIsCorrectAnswerInvalidUserInput() {
		assertFalse(MathQuestionService.isCorrectAnswer("abc", 8.0));
	}
}