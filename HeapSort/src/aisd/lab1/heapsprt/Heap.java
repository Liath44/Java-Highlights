package aisd.lab1.heapsprt;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which represents heap in which parent elements are always greater than their sons
 *
 * @author Maciej Dragun
 *
 * TODO: Index rodzica = (childIndex - 1) / 2
 * TODO: Index lewego dziecka = 2 * parentIndex + 1
 * TODO: Index prawego dziecka = 2 * parentIndex + 2
 *
 * TODO:    a.compareTo(b)
 * TODO:    == 0 gdy a == b
 * TODO:    > 0 gdy a > b
 * TODO:    < 0 gdy a < b
 */
public class Heap<T extends Comparable<T>> implements HeapInterface<T> {
	private final static String NULL_ITEM_PUT_MESSAGE = "Cannot put null on heap";
	private final List<T> items;

	public Heap() {
		items = new ArrayList<>();
	}

	@Override
	public void put(T item) {
		if (item == null) {
			throw new IllegalArgumentException(NULL_ITEM_PUT_MESSAGE);
		}
		items.add(item);
		heapUp();
	}

	private void heapUp() {
		int childIndex = items.size() - 1;
		int parentIndex;
		while (childIndex != 0) {
			parentIndex = (childIndex - 1) / 2;
			if (isFirstProvidedItemGreaterThanSecond(childIndex, parentIndex)) {
				swapItems(childIndex, parentIndex);
			} else {
				return;
			}
			childIndex = parentIndex;
		}
	}

	private boolean isFirstProvidedItemGreaterThanSecond(int index1, int index2) {
		return items.get(index1).compareTo(items.get(index2)) > 0;
	}

	private void swapItems(int index1, int index2) {
		T pivot = items.get(index1);
		items.set(index1, items.get(index2));
		items.set(index2, pivot);
	}

	@Override
	public T pop() {
		if (items.isEmpty()) {
			return null;
		}
		int lastItemIndex = items.size() - 1;
		swapItems(lastItemIndex, 0);
		T poppedItem = items.remove(lastItemIndex);
		heapDown();
		return poppedItem;
	}

	private void heapDown() {
		int numberOfItems = items.size();
		if (items.size() <= 1) {
			return;
		}
		int parentIndex = 0;
		int leftChildIndex = 1;
		int rightChildIndex = 2;
		while (leftChildIndex < numberOfItems) {
			if (rightChildIndex < numberOfItems) {
				int greaterChildIndex = getIndexOfGreaterItem(leftChildIndex, rightChildIndex);
				if (isFirstProvidedItemGreaterThanSecond(greaterChildIndex, parentIndex)) {
					swapItems(greaterChildIndex, parentIndex);
					parentIndex = greaterChildIndex;
					leftChildIndex = 2 * parentIndex + 1;
					rightChildIndex = 2 * parentIndex + 2;
				} else {
					return;
				}
			} else {
				if (isFirstProvidedItemGreaterThanSecond(leftChildIndex, parentIndex)) {
					swapItems(leftChildIndex, parentIndex);
				}
				return;
			}
		}
	}

	private int getIndexOfGreaterItem(int index1, int index2) {
		if (isFirstProvidedItemGreaterThanSecond(index1, index2)) {
			return index1;
		}
		return index2;
	}

	/**
	 * Verifies heap's condition (every parent is greater than its sons)
	 * This method is for testing purposes only
	 *
	 * @return true if condition is met, false otherwise
	 */
	public boolean verifyHeap() {
		for (int i = 1; i < items.size(); i++) {
			if (isFirstProvidedItemGreaterThanSecond(i, (i - 1) / 2)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method is for testing purposes only
	 *
	 * @param index returned item's index
	 * @return item from heap with provided index
	 */
	public T get(int index) {
		return items.get(index);
	}
}
