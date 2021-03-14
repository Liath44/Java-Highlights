package binarysearch;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static binarysearch.BinarySearcher.binarySearch;
import static binarysearch.BinarySearcher.getNumberNotPresentReturnValue;

public class BinarySearcherTest {
	private static final int NUMBER_NOT_PRESENT_RETURN_VALUE = getNumberNotPresentReturnValue();

	//assert false powiadamia, że test się wyłożył
	//Zawadzki ma jakieś problemy z komentarzami w środku kodu
	@Test(expected = IllegalArgumentException.class)
	public void ifArrayIsNullBinarySearchThenThrowsIllegalArgumentException() {
		//given
		final double[] sortedNumbers = null;
		final double numberToFind = 0.0;
		//when
		int resultId = binarySearch(sortedNumbers, numberToFind);
		//then
		assert false;
	}

	@Test
	public void ifNumberToFindIsLowerThanFirstNumberThenNegativeIDIsReturned() {
		//given
		final double[] sortedNumbers = {2, 3, 4, 5};
		final double numberToFind = 1;
		//when
		final int resultId = binarySearch(sortedNumbers, numberToFind);
		//then
		assertEquals(resultId, NUMBER_NOT_PRESENT_RETURN_VALUE);
	}

	@Test
	public void ifArrayContainsNoValuesThenNegativeIDIsReturned() {
		//given
		final double[] sortedNumbers = {};
		final double numberToFind = 3;
		//when
		final int resultId = binarySearch(sortedNumbers, numberToFind);
		//then
		assertEquals(resultId, NUMBER_NOT_PRESENT_RETURN_VALUE);
	}
}
