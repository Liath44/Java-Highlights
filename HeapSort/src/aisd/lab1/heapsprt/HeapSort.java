package aisd.lab1.heapsprt;

/**
 * Class which implements heap sort algorithm (using Heap class)
 *
 * @author Maciej Dragun
 * @see Heap
 */
public class HeapSort implements SortingAlgorithm {
	private final static String NULL_ARRAY_MESSAGE = "To-be-sorted array cannot be null";

	@Override
	public double[] sort(double[] unsortedArray) {
		if(unsortedArray == null) {
			throw new IllegalArgumentException(NULL_ARRAY_MESSAGE);
		}
		Heap<Double> maxHeap = new Heap<>();
		for(Double item : unsortedArray) {
			maxHeap.put(item);
		}
		double[] outcomeArray = new double[unsortedArray.length];
		for(int i = outcomeArray.length - 1; i >= 0; i--) {
			outcomeArray[i] = maxHeap.pop();
		}
		return outcomeArray;
	}
}
