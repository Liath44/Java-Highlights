package binarysearch;

public class BinarySearcher {
	private final static String NULL_ARRAY_MESSAGE = "Sorted numbers cannot be null.";
	private final static int NUMBER_NOT_PRESENT_RETURN_VALUE = -1;

	private BinarySearcher() {
	}

	//nie wymagane jest sprawdzanie posortowania tablicy
	//niestabilność algorytmu - dlaczego - pytanie na następnych zajęciach
	//typy generyczne
	public static int binarySearch(final double[] sortedNumbers, final double toFind) {
		if(sortedNumbers == null) {
			throw new IllegalArgumentException(NULL_ARRAY_MESSAGE);
		}
		int n = sortedNumbers.length;
		int left = 0;
		int right = n - 1;
		int mid;
		while(left <= right) {
			mid = (left + right) >>> 1;   //TODO: (l + r) / 2    ||    low + ((high - low) / 2)
			if(sortedNumbers[mid] == toFind) {
				return mid;
			} else if(sortedNumbers[mid] > toFind) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return NUMBER_NOT_PRESENT_RETURN_VALUE;
	}

	public static <T extends Comparable<T>> int genericBinarySearch(final T[] sortedArray, final T toFind) {
		if(sortedArray == null) {
			throw new IllegalArgumentException(NULL_ARRAY_MESSAGE);
		}
		int leftIndex = 0;
		int rightIndex = sortedArray.length - 1;
		int middleIndex;
		while(leftIndex <= rightIndex) {
			middleIndex = (leftIndex + rightIndex) >>> 1;
			final int comparisonResult = sortedArray[middleIndex].compareTo(toFind);
			if(comparisonResult == 0) {
				return middleIndex;
			} else if(comparisonResult > 0) {
				rightIndex = middleIndex - 1;
			} else {
				leftIndex = middleIndex + 1;
			}
		}
		return NUMBER_NOT_PRESENT_RETURN_VALUE;
	}

	public static int getNumberNotPresentReturnValue() {
		return NUMBER_NOT_PRESENT_RETURN_VALUE;
	}
}
