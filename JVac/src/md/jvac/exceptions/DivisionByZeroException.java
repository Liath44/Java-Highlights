package md.jvac.exceptions;

import md.jvac.rationalnumber.RationalNumber;

import static java.text.MessageFormat.format;

public class DivisionByZeroException extends RuntimeException {
	private final static String MESSAGE_FORMAT = "Performed division by 0 during Rational Numbers division.\nDividend: {0}\nDivisor: {1}";
	private final String dividend;
	private final String divisor;

	public DivisionByZeroException(RationalNumber dividend, RationalNumber divisor) {
		this.dividend = dividend.toString();
		this.divisor = divisor.toString();
	}

	@Override
	public String getMessage() {
		return format(MESSAGE_FORMAT, dividend, divisor);
	}
}
