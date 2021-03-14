package aisd.lab1.heapsprt;

public class QuickSortRecursive implements SortingAlgorithm {
	private final static String TO_BE_SORTED_ARRAY_IS_NULL_MESSAGE = "To be sorted array cannot be null";

	@Override
	public double[] sort(double[] unsortedVector) {
		if (unsortedVector == null) {
			throw new IllegalArgumentException(TO_BE_SORTED_ARRAY_IS_NULL_MESSAGE);
		}
		double[] sortedNewVector = unsortedVector.clone();
		quicksort(sortedNewVector);
		return sortedNewVector;
	}

	private void quicksort(double[] data) {
		int left = 0;
		int right = data.length - 1;
		quicksort(data, left, right);
	}

	private void quicksort(double[] data, int left, int right) {
		if (left < right) {
			int pivot = splitData(data, left, right);
			quicksort(data, left, pivot - 1);
			quicksort(data, pivot + 1, right);
		}
	}

	private int splitData(double[] data, int start, int end) {
		int left = start + 1;
		int right = end;
		while (right > left) {
			while (right > left && data[left] < data[start]) {
				left++;
			}
			while (right > left && data[right] >= data[start]) {
				right--;
			}
			swap(data, left, right);
		}
		if (data[left] >= data[start]) {
			left--;
		}
		swap(data, start, left);
		return left;
	}

	private void swap(double[] data, int firstId, int secondId) {
		if (firstId != secondId) {
			double firstValue = data[firstId];
			data[firstId] = data[secondId];
			data[secondId] = firstValue;
		}
	}

}
