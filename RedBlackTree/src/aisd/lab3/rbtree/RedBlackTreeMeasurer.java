package aisd.lab3.rbtree;

import static java.lang.System.nanoTime;
import static java.lang.System.out;

public class RedBlackTreeMeasurer {
	private final static int DATA_SIZE = 100000 * 16;

	public static void main(String[] args) {
		RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
		int elementIndex = 0;
		for (; elementIndex < DATA_SIZE - 1; elementIndex++) {
			redBlackTree.put(elementIndex, elementIndex);
		}
		Long start = nanoTime();
		redBlackTree.put(elementIndex, elementIndex);
		Long end = nanoTime();
		out.println(end - start);
	}
}
