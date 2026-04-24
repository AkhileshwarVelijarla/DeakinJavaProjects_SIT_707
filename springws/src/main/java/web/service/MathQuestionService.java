package web.service;

public class MathQuestionService {

	public static Double q1Addition(String number1, String number2) {
		Double n1 = parseNumber(number1);
		Double n2 = parseNumber(number2);

		if (n1 == null || n2 == null) {
			return null;
		}

		return n1 + n2;
	}

	public static Double q2Subtraction(String number1, String number2) {
		Double n1 = parseNumber(number1);
		Double n2 = parseNumber(number2);

		if (n1 == null || n2 == null) {
			return null;
		}

		return n1 - n2;
	}

	public static Double q3Multiplication(String number1, String number2) {
		Double n1 = parseNumber(number1);
		Double n2 = parseNumber(number2);

		if (n1 == null || n2 == null) {
			return null;
		}

		return n1 * n2;
	}

	public static boolean isCorrectAnswer(String userAnswer, Double expectedAnswer) {
		if (expectedAnswer == null) {
			return false;
		}

		Double userValue = parseNumber(userAnswer);
		if (userValue == null) {
			return false;
		}

		return Double.compare(userValue, expectedAnswer) == 0;
	}

	private static Double parseNumber(String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			return Double.valueOf(value.trim());
		} catch (NumberFormatException e) {
			return null;
		}
	}
}