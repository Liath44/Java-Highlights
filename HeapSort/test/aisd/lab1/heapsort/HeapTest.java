package aisd.lab1.heapsort;

import aisd.lab1.heapsprt.Heap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for Heap class
 *
 * @author Maciej Dragun
 * @see Heap
 */
public class HeapTest {
	@Test(expected = IllegalArgumentException.class)
	public void ifNullIsPutThenIllegalArgumentExceptionIsThrown() {
		//given
		Heap<Double> heap = new Heap<>();
		//when
		heap.put(null);
		//then
		assert false;
	}

	@Test
	public void ifItemIsPutOnEmptyHeapThenItIsPutOnTopOfIt() {
		//given
		Integer sampleItem = 4;
		Heap<Integer> heap = new Heap<>();
		//when
		heap.put(sampleItem);
		//then
		assertEquals(sampleItem, heap.get(0));
	}

	@Test
	public void ifTheGreatestItemIsPutThenItIsPutOnTopOfHeap() {
		//given
		Integer greatestItem = 10;
		Heap<Integer> heap = new Heap<>();
		for (int i = 3; i < 7; i++) {
			heap.put(i);
		}
		//when
		heap.put(greatestItem);
		//then
		assertEquals(greatestItem, heap.get(0));
	}

	@Test
	public void ifItemsArePutThenHeapConditionsAreSatisfied() {
		//given
		Integer[] randomIntegers = {3, 14, 22, 19, 0, -4, 27};
		Heap<Integer> heap = new Heap<>();
		//when
		for (Integer item : randomIntegers) {
			heap.put(item);
			//then
			assertTrue(heap.verifyHeap());
		}
	}

	@Test
	public void ifHeapAcceptsStringsThenItStillWorks() {
		//given
		String[] randomStrings = {"aaa", "szczupak", "sample", ":^]", "last"};
		Heap<String> heap = new Heap<>();
		//when
		for (String item : randomStrings) {
			heap.put(item);
			//then
			assertTrue(heap.verifyHeap());
		}
	}

	@Test
	public void ifHeapContainsRepeatingItemsThenItFulfillsItsConditions() {
		//given
		Integer[] randomIntegers = {3, 14, 7, 7, 7, 7, 44, -2};
		Heap<Integer> heap = new Heap<>();
		//when
		for (Integer item : randomIntegers) {
			heap.put(item);
			//then
			assertTrue(heap.verifyHeap());
		}
	}

	@Test
	public void ifAttemptedToPopFromEmptyHeapThenNullIsReturned() {
		//given
		Heap<Integer> heap = new Heap<>();
		//when
		Integer poppedItem = heap.pop();
		//then
		assertNull(poppedItem);
	}

	@Test
	public void ifOnlyOneItemIsOnHeapThenItIsReturnedWhenPopped() {
		//given
		Integer sampleItem = 5;
		Heap<Integer> heap = new Heap<>();
		heap.put(sampleItem);
		//when
		Integer poppedItem = heap.pop();
		//then
		assertEquals(poppedItem, sampleItem);
	}

	@Test
	public void whenItemIsPoppedThenItMustBeTheGreatest() {
		//given
		Integer[] randomItems = {1, 4, 2, 7, 3, 3, 2, 0};
		Integer greatestItem = 8;
		Heap<Integer> heap = new Heap<>();
		for (Integer item : randomItems) {
			heap.put(item);
		}
		heap.put(greatestItem);
		//when
		Integer poppedItem = heap.pop();
		//then
		assertEquals(poppedItem, greatestItem);
	}

	@Test
	public void whenItemsArePoppedFromHeapThenItsConditionIsStillFulfilled() {
		//given
		Integer[] randomIntegers = {3, 14, 22, 19, 0, -4, 27};
		Heap<Integer> heap = new Heap<>();
		for (Integer item : randomIntegers) {
			heap.put(item);
		}
		//when
		while (heap.pop() != null) {
			//then
			assertTrue(heap.verifyHeap());
		}
	}

	@Test
	public void whenItemsOnHeapAreStringsThenPoppingThemStillFulfillsHeapsCondition() {
		//given
		String[] randomStrings = {"first", "szczupak", "sample", ":^]", "aaa"};
		Heap<String> heap = new Heap<>();
		for (String item : randomStrings) {
			heap.put(item);
		}
		//when
		while (heap.pop() != null) {
			//then
			assertTrue(heap.verifyHeap());
		}
	}

	@Test
	public void ifHeapContainsRepeatingItemsThenPoppingThemStillFulfillsHeapsCondition() {
		//given
		Integer[] randomIntegers = {3, 14, 7, 7, 7, 7, 7, 4, 77};
		Heap<Integer> heap = new Heap<>();
		for (Integer item : randomIntegers) {
			heap.put(item);
		}
		//when
		while (heap.pop() != null) {
			//then
			assertTrue(heap.verifyHeap());
		}
	}
}
