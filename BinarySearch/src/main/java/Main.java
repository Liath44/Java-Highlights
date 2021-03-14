import binarysearch.BinarySearcher;

public class Main {
	public static void main(String[] args) {
		Double[] sortedNumbers = {1.0, 2.0, 3.0, 4.1, 5.2, 6.2, 7.0};
		double toFind = 7.0;
		System.out.println(BinarySearcher.genericBinarySearch(sortedNumbers, toFind));
	}
}