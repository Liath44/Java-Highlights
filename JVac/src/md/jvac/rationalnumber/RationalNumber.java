package md.jvac.rationalnumber;

import md.jvac.exceptions.DivisionByZeroException;
import md.jvac.exceptions.ImproperDenominatorException;

import static java.text.MessageFormat.format;

public class RationalNumber {
	private final static String NUMBERS_STRING_FORMAT = "{0}.{1}";
	private final int numerator;
	private final int denominator;

	public RationalNumber(int integer) {
		this.numerator = integer;
		this.denominator = 1;
	}

	public RationalNumber(int numerator, int denominator) {
		if (denominator == 0) {
			throw new ImproperDenominatorException(numerator, denominator);
		}
		int negativeController = 1;
		if (numerator * denominator < 0) {
			negativeController = -1;
			if (numerator < 0) {
				numerator *= -1;
			} else {
				denominator *= -1;
			}
		}
		int divisor = getGCF(numerator, denominator);
		numerator /= divisor;
		denominator /= divisor;
		this.numerator = numerator * negativeController;
		this.denominator = denominator;
	}

	public RationalNumber subtract(RationalNumber subtrahend) {
		RationalNumber summand = new RationalNumber(-1 * subtrahend.getNumerator(), subtrahend.getDenominator());
		return add(summand);
	}

	public RationalNumber add(RationalNumber summand) {
		int outputDenominator = getLCM(denominator, summand.getDenominator());
		int outputNumerator = numerator * (outputDenominator / denominator);
		outputNumerator += summand.getNumerator() * (outputDenominator / summand.getDenominator());
		if (outputNumerator == 0) {
			return getZero();
		}
		return new RationalNumber(outputNumerator, outputDenominator);
	}

	private static int getLCM(int a, int b) {
		return (a * b) / getGCF(a, b);
	}

	private static int getGCF(int a, int b) {
		if (a == 1 || b == 1) {
			return 1;
		}
		int pivot;
		while (b != 0) {
			pivot = b;
			b = a % b;
			a = pivot;
		}
		return a;
	}

	public RationalNumber divide(RationalNumber divisor) {
		if (divisor.isZero()) {
			throw new DivisionByZeroException(this, divisor);
		}
		RationalNumber multiplier = divisor.getReversed();
		return multiply(multiplier);
	}

	public RationalNumber multiply(RationalNumber multiplier) {
		int outputNumerator = numerator * multiplier.getNumerator();
		int outputDenominator = denominator * multiplier.getDenominator();
		if (outputNumerator == 0) {
			return getZero();
		}
		return new RationalNumber(outputNumerator, outputDenominator);
	}

	@Override
	public String toString() {
		int integerPart = numerator / denominator;
		int fractionalPart = (numerator % denominator) * (100 / denominator);
		if (fractionalPart == 0) {
			return Integer.toString(integerPart);
		} else {
			String integerPartS = Integer.toString(integerPart);
			String fractionalPartS = Integer.toString(fractionalPart);
			if (fractionalPartS.length() == 1) {
				fractionalPartS = "0" + fractionalPartS;
			}
			return format(NUMBERS_STRING_FORMAT, integerPartS, fractionalPartS);
		}
	}

	public boolean equals(RationalNumber target) {
		return numerator * target.getDenominator() == denominator * target.getNumerator();
	}

	private static RationalNumber getZero() {
		return new RationalNumber(0, 1);
	}

	public boolean isSmaller(RationalNumber target) {
		return numerator * target.getDenominator() < denominator * target.getNumerator();
	}

	public boolean isNegative() {
		return numerator < 0;
	}

	public boolean isPositive() {
		return numerator > 0;
	}

	public boolean isZero() {
		return numerator == 0;
	}

	public RationalNumber getReversed() {
		return new RationalNumber(denominator, numerator);
	}

	private int getNumerator() {
		return numerator;
	}

	private int getDenominator() {
		return denominator;
	}
}
