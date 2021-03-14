package md.test;

import md.jvac.exceptions.DivisionByZeroException;
import md.jvac.exceptions.ImproperDenominatorException;
import md.jvac.rationalnumber.RationalNumber;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RationalNumberTest {
	@Test
	public void ifSingleIntegerIsPassedToConstructorThenDenominatorEquals1() {
		//given
		RationalNumber integer = new RationalNumber(5);
		//when
		RationalNumber fraction = new RationalNumber(5, 1);
		//then
		assertTrue(integer.equals(fraction));
	}

	@Test
	public void ifIntegersPassedToConstructorHaveNon0GCFThenFractionIsReduced() {
		//given
		RationalNumber nonReducedFraction = new RationalNumber(7, 14);
		RationalNumber reducedFraction = new RationalNumber(1, 2);
		//then
		assertTrue(nonReducedFraction.equals(reducedFraction));
	}

	@Test(expected = ImproperDenominatorException.class)
	public void ifDenominatorEquals0ThenExceptionIsThrown() {
		//given
		RationalNumber denominatorIs0 = new RationalNumber(5, 0);
		//then
		fail();
	}

	@Test
	public void ifFractionsAreSubtractedThenOutcomeIsProper() {
		//given
		RationalNumber a = new RationalNumber(2, 5);
		RationalNumber b = new RationalNumber(1, 10);
		RationalNumber outcome = new RationalNumber(3, 10);
		//when
		RationalNumber hypotheticalOutcome = a.subtract(b);
		//then
		assertTrue(outcome.equals(hypotheticalOutcome));
	}

	@Test
	public void ifFractionsAreAddedThenOutcomeIsProper() {
		//given
		RationalNumber a = new RationalNumber(3, 7);
		RationalNumber b = new RationalNumber(5, 21);
		RationalNumber outcome = new RationalNumber(2, 3);
		//when
		RationalNumber hypotheticalOutcome = a.add(b);
		//then
		assertTrue(outcome.equals(hypotheticalOutcome));
	}

	@Test
	public void ifFractionsAreDividedThenOutcomeIsProper() {
		//given
		RationalNumber a = new RationalNumber(5, 12);
		RationalNumber b = new RationalNumber(1, 8);
		RationalNumber outcome = new RationalNumber(10, 3);
		//when
		RationalNumber hypotheticalOutcome = a.divide(b);
		//then
		assertTrue(outcome.equals(hypotheticalOutcome));
	}

	@Test(expected = DivisionByZeroException.class)
	public void ifDivisionBy0IsAttemptedThenExceptionIsThrown() {
		//given
		RationalNumber a = new RationalNumber(2, 7);
		RationalNumber zero = new RationalNumber(0, 4);
		//when
		RationalNumber outcome = a.divide(zero);
		//then
		fail();
	}

	@Test
	public void ifFractionsAreMultipliedThenOutcomeIsProper() {
		//given
		RationalNumber a = new RationalNumber(4, 9);
		RationalNumber b = new RationalNumber(12, 2);
		RationalNumber outcome = new RationalNumber(8, 3);
		//when
		RationalNumber hypotheticalOutcome = a.multiply(b);
		//then
		assertTrue(outcome.equals(hypotheticalOutcome));
	}

	@Test
	public void ifToStringIsCalledThenFormatIsProperForDecimalFractionsWithTwoPlaceOfPrecision() {
		//given
		RationalNumber rationalNumber = new RationalNumber(5, 100);
		String properString = "0.05";
		//when
		String stringedNumber = rationalNumber.toString();
		//then
		assertEquals(properString, stringedNumber);
	}

	@Test
	public void ifToStringIsCalledThenFormatIsProperForDecimalFractionsWithOnePlacesOfPrecision() {
		//given
		RationalNumber rationalNumber = new RationalNumber(150, 100);
		String properString = "1.50";
		//when
		String stringedNumber = rationalNumber.toString();
		//then
		assertEquals(properString, stringedNumber);
	}

	@Test
	public void ifSmallerNumberCallsIsSmallerThenItReturnsTrue() {
		//given
		RationalNumber smallerNumber = new RationalNumber(3, 77);
		RationalNumber biggerNumber = new RationalNumber(4, 77);
		//then
		assertTrue(smallerNumber.isSmaller(biggerNumber));
	}

	@Test(expected = ImproperDenominatorException.class)
	public void ifZeroIsReversedThenExceptionIsThrown() {
		//given
		RationalNumber zero = new RationalNumber(0, 1);
		//when
		RationalNumber reversed = zero.getReversed();
		//then
		fail();
	}
}
