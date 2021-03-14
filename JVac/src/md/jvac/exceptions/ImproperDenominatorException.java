package md.jvac.exceptions;

import static java.text.MessageFormat.format;

public class ImproperDenominatorException extends RuntimeException {
	private final static String MESSAGE_FORMAT = "Attempted creation of rational number with denominator equal to 0\nNumerator: {0, number, integer}\nDenominator: {1, number, integer}";
	private final int numerator;
	private final int denominator;

	public ImproperDenominatorException(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	@Override
	public String getMessage() {
		return format(MESSAGE_FORMAT, numerator, denominator);
	}
}
