package aisd.lab2.sortcomparison;

import aisd.lab2.sortomparison.InsertSort;
import aisd.lab2.sortomparison.SortingAlgorithm;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class InsertSortTest {
	private final static Double ARRAY_EQUALS_DELTA = 0.0;

	@Test(expected = IllegalArgumentException.class)
	public void ifProvidedNullToSortThenExceptionIsThrown() {
		//given
		double[] nullArray = null;
		SortingAlgorithm sorter = new InsertSort();
		//when
		sorter.sort(nullArray);
		//then
		assert false;
	}

	@Test
	public void ifArrayToSortIsEmptyThenNoIssuesOccur() {
		//given
		double[] emptyArray = {};
		SortingAlgorithm sorter = new InsertSort();
		//when
		double[] sortedVector = sorter.sort(emptyArray);
		//then
		assertArrayEquals(emptyArray, sortedVector, ARRAY_EQUALS_DELTA);
	}

	@Test
	public void ifArrayToSortContainsOnlyOneElementThenNoIssuesOccur() {
		//given
		double[] arrayToSort = {1.0};
		SortingAlgorithm sorter = new InsertSort();
		//when
		double[] sortedVector = sorter.sort(arrayToSort);
		//then
		assertArrayEquals(arrayToSort, sortedVector, ARRAY_EQUALS_DELTA);
	}

	@Test
	public void ifArrayToSortContainsTwoElementsThenNoIssuesOccur() {
		//given
		double[] arrayToSort = {2.0, 1.0};
		double[] properlySortedArray = {1.0, 2.0};
		SortingAlgorithm sorter = new InsertSort();
		//when
		double[] sortedArray = sorter.sort(arrayToSort);
		//then
		assertArrayEquals(sortedArray, properlySortedArray, ARRAY_EQUALS_DELTA);
	}

	@Test
	public void ifSortingIsPerformedThenArrayBecomesSorted() {
		//given
		double[] arrayToSort = {5.0, 1.0, 3.0, 7.0, 2.0, 6.0, 4.0};
		double[] properlySortedArray = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0};
		SortingAlgorithm sorter = new InsertSort();
		//when
		double[] sortedArray = sorter.sort(arrayToSort);
		//then
		assertArrayEquals(sortedArray, properlySortedArray, ARRAY_EQUALS_DELTA);
	}

	@Test
	public void ifArrayContainsRepeatingElementsThenItIsStillSortedProperly() {
		//given
		double[] arrayToSort = {9.0, 3.0, 1.0, 2.0, 9.0, 9.0, 9.0, 5.0};
		double[] properlySortedArray = {1.0, 2.0, 3.0, 5.0, 9.0, 9.0, 9.0, 9.0};
		SortingAlgorithm sorter = new InsertSort();
		//when
		double[] sortedArray = sorter.sort(arrayToSort);
		//then
		assertArrayEquals(sortedArray, properlySortedArray, ARRAY_EQUALS_DELTA);
	}

	@Test
	public void ifArrayIsSortedThenSortingItStillSortsProperly() {
		//given
		double[] arrayToSort = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
		double[] properlySortedArray = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
		SortingAlgorithm sorter = new InsertSort();
		//when
		double[] sortedArray = sorter.sort(arrayToSort);
		//then
		assertArrayEquals(sortedArray, properlySortedArray, ARRAY_EQUALS_DELTA);
	}

	@Test
	public void ifValuesInArrayAreDescendingThenSortingItStillSortsProperly() {
		//given
		double[] arrayToSort = {10.0, 9.0, 8.0, 7.0, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0};
		double[] properlySortedArray = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};
		SortingAlgorithm sorter = new InsertSort();
		//when
		double[] sortedArray = sorter.sort(arrayToSort);
		//then
		assertArrayEquals(sortedArray, properlySortedArray, ARRAY_EQUALS_DELTA);
	}
}
