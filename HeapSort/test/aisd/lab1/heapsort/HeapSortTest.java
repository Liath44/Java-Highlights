package aisd.lab1.heapsort;

import aisd.lab1.heapsprt.HeapSort;
import aisd.lab1.heapsprt.SortingAlgorithm;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests for HeapSort class
 *
 * @author Maciej Dragun
 * @see HeapSort
 */
public class HeapSortTest {
	private final static Double ARRAY_EQUALS_DELTA = 0.0;

	@Test(expected = IllegalArgumentException.class)
	public void ifProvidedNullToSortThenExceptionIsThrown() {
		//given
		double[] nullArray = null;
		SortingAlgorithm sorter = new HeapSort();
		//when
		sorter.sort(nullArray);
		//then
		assert false;
	}

	@Test
	public void ifSortedArrayIsEmptyThenNoIssuesOccur() {
		//given
		double[] emptyArray = {};
		SortingAlgorithm sorter = new HeapSort();
		//when
		double[] sortedVector = sorter.sort(emptyArray);
		//then
		assertArrayEquals(emptyArray, sortedVector, ARRAY_EQUALS_DELTA);
	}

	@Test
	public void ifSortedArrayContainsOnlyOneElementThenNoIssuesOccur() {
		//given
		double[] arrayToSort = {1.0};
		SortingAlgorithm sorter = new HeapSort();
		//when
		double[] sortedVector = sorter.sort(arrayToSort);
		//then
		assertArrayEquals(arrayToSort, sortedVector, ARRAY_EQUALS_DELTA);
	}

	@Test
	public void ifArrayIsSortedThenItBecomesSorted() {
		//given
		double[] arrayToSort = {5.0, 1.0, 3.0, 7.0, 2.0, 6.0, 4.0};
		double[] properlySortedArray = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0};
		SortingAlgorithm sorter = new HeapSort();
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
		SortingAlgorithm sorter = new HeapSort();
		//when
		double[] sortedArray = sorter.sort(arrayToSort);
		//then
		assertArrayEquals(sortedArray, properlySortedArray, ARRAY_EQUALS_DELTA);
	}
}
