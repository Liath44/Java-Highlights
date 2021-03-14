package aisd.lab1.heapsprt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSortIterative implements SortingAlgorithm {
	private final static String TO_BE_SORTED_ARRAY_IS_NULL_MESSAGE = "To be sorted array cannot be null";
	private final static long SEED = 7821L;

	@Override
	public double[] sort(double[] unsortedVector) {
		if (unsortedVector == null) {
			throw new IllegalArgumentException(TO_BE_SORTED_ARRAY_IS_NULL_MESSAGE);
		}
		double[] sortedNewVector = unsortedVector.clone();
		performQuicksort(sortedNewVector);
		return sortedNewVector;
	}

	private void performQuicksort(double[] data) {
		Random randomNumberGenerator = new Random(SEED);
		List<Integer> startsList = new ArrayList<>();
		List<Integer> endsList = new ArrayList<>();
		Integer leftIndex = 0;
		Integer rightIndex = data.length - 1;
		startsList.add(leftIndex);
		endsList.add(rightIndex);
		int stackSize = 1;
		int pivot;
		if (leftIndex < rightIndex) {
			while (stackSize > 0) {
				stackSize--;
				leftIndex = startsList.remove(stackSize);
				rightIndex = endsList.remove(stackSize);
				pivot = splitData(data, leftIndex, rightIndex, randomNumberGenerator);
				if (pivot - 1 > leftIndex) {
					startsList.add(leftIndex);
					endsList.add(pivot - 1);
					stackSize++;
				}
				if (pivot + 1 < rightIndex) {
					startsList.add(pivot + 1);
					endsList.add(rightIndex);
					stackSize++;
				}
			}
		}
	}

	private int splitData(double[] data, int startIndex, int endIndex, Random randomNumberGenerator) {
		int splittingElementIndex = findSplittingElement(data, startIndex, endIndex, randomNumberGenerator);
		swap(data, startIndex, splittingElementIndex);
		int leftIndex = startIndex + 1;
		int rightIndex = endIndex;
		while (leftIndex < rightIndex) {
			while (leftIndex < rightIndex && data[leftIndex] < data[startIndex]) {
				leftIndex++;
			}
			while (leftIndex < rightIndex && data[rightIndex] >= data[startIndex]) {
				rightIndex--;
			}
			swap(data, leftIndex, rightIndex);
		}
		if (data[leftIndex] >= data[startIndex]) {
			leftIndex--;
		}
		swap(data, startIndex, leftIndex);
		return leftIndex;
	}

	private int findSplittingElement(double[] data, int startIndex, int endIndex, Random randomNumberGenerator) {
		int randomIndex = randomNumberGenerator.nextInt((endIndex - startIndex) + 1) + startIndex;
		return findSplittingElement(data, startIndex, randomIndex, endIndex);
	}

	private int findSplittingElement(double[] data, int startIndex, int randomIndex, int endIndex) {
		if (data[startIndex] > data[randomIndex]) {
			if (data[randomIndex] > data[endIndex]) {
				return randomIndex;
			} else if (data[startIndex] > data[endIndex]) {
				return endIndex;
			} else {
				return startIndex;
			}
		} else {
			if (data[startIndex] > data[endIndex]) {
				return endIndex;
			} else if (data[randomIndex] > data[endIndex]) {
				return endIndex;
			} else {
				return randomIndex;
			}
		}
	}

	private void swap(double[] data, int firstIndex, int secondIndex) {
		if (firstIndex != secondIndex) {
			double firstValue = data[firstIndex];
			data[firstIndex] = data[secondIndex];
			data[secondIndex] = firstValue;
		}
	}
}

