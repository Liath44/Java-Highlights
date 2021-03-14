package aisd.lab2.sortomparison;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import static java.text.MessageFormat.format;

public class SortingMeasurer {
	private final static int ARRAY_SIZE = 10840;
	private final static int NUMBER_OF_MEASUREMENTS = 10;
	private final static long SEED = 44L;
	private final static Random RANDOM_NUMBER_GENERATOR = new Random(SEED);
	private final static String QUICKSORT_LABEL = "QS";
	private final static String INSORT_LABEL = "IS";
	private final static String SORTED_ARRAY_LABEL = "Sorted";
	private final static String DESCENDING_ARRAY_LABEL = "Descending";
	private final static String RANDOM_ARRAY_LABEL = "Random";
	private final static String OUTPUT_FILE_NAME_FORMAT = "{0} {1, number, integer} {2}";
	private final static String MEASURED_VALUE_FORMAT = "{0, number, integer}){1, number, long}\n";
	private final static SortingAlgorithm QUICK_SORTER = new QuickSortIterative();
	private final static SortingAlgorithm INSERTION_SORTER = new InsertSort();

	public static void main(String[] args) throws IOException {
		measureSorting(generateSortedArray(), SORTED_ARRAY_LABEL);
		measureSorting(generateDescendingArray(), DESCENDING_ARRAY_LABEL);
		measureSorting(generateRandomArray(), RANDOM_ARRAY_LABEL);
	}

	private static double[] generateSortedArray() {
		double[] subjectArray = new double[ARRAY_SIZE];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			subjectArray[i] = i;
		}
		return subjectArray;
	}

	private static double[] generateDescendingArray() {
		double[] subjectArray = new double[ARRAY_SIZE];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			subjectArray[ARRAY_SIZE - i - 1] = i;
		}
		return subjectArray;
	}

	private static double[] generateRandomArray() {
		double[] subjectArray = new double[ARRAY_SIZE];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			subjectArray[i] = RANDOM_NUMBER_GENERATOR.nextDouble();
		}
		return subjectArray;
	}

	private static void measureSorting(double[] subjectArray, String arrayType) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(format(OUTPUT_FILE_NAME_FORMAT, QUICKSORT_LABEL, ARRAY_SIZE, arrayType)))) {
			measureSorting(subjectArray, writer, QUICK_SORTER);
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(format(OUTPUT_FILE_NAME_FORMAT, INSORT_LABEL, ARRAY_SIZE, arrayType)))) {
			measureSorting(subjectArray, writer, INSERTION_SORTER);
		}
	}

	private static void measureSorting(double[] subjectArray, BufferedWriter writer, SortingAlgorithm sorter) throws IOException {
		for (int i = 1; i <= NUMBER_OF_MEASUREMENTS; i++) {
			long sortingStart = System.nanoTime();
			sorter.sort(subjectArray);
			long sortingEnd = System.nanoTime();
			writeResult(writer, i, sortingEnd - sortingStart);
		}
	}

	private static void writeResult(BufferedWriter writer, int measurementIndex, long measuredTime) throws IOException {
		writer.write(format(MEASURED_VALUE_FORMAT, measurementIndex, measuredTime));
	}
}
