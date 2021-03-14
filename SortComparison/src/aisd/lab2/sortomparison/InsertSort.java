package aisd.lab2.sortomparison;

public class InsertSort implements SortingAlgorithm{
	private final static String TO_BE_SORTED_ARRAY_IS_NULL_MESSAGE = "To be sorted array cannot be null";

	@Override
	public double[] sort(double[] unsortedArray) {
		if(unsortedArray == null) {
			throw new IllegalArgumentException(TO_BE_SORTED_ARRAY_IS_NULL_MESSAGE);
		}
		double[] sortedArray = unsortedArray.clone();
		int n = sortedArray.length;
		for(int i = 1; i < n; i++) {
			int pos = i - 1;
			double val = sortedArray[i];
			while(pos >= 0 && sortedArray[pos] > val) {
				sortedArray[pos + 1] = sortedArray[pos];
				pos--;
			}
			sortedArray[pos + 1] = val;
		}
		return sortedArray;
	}
}
