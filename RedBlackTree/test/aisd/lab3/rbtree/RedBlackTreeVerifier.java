package aisd.lab3.rbtree;

import static aisd.lab3.rbtree.RedBlackTree.isKeyBiggerThanNode;
import static aisd.lab3.rbtree.RedBlackTree.isKeySmallerThanNode;
import static aisd.lab3.rbtree.RedBlackTree.isRed;

public class RedBlackTreeVerifier {
	private final static String UNEQUAL_BLACK_LENGTHS_MESSAGE = "Condition 5) not met. Paths to leaf nodes from nodes have unequal number of black nodes.";

	private RedBlackTreeVerifier() {
	}

	public static <K extends Comparable<K>, V> boolean isProperRedBlackTree(RedBlackTree<K, V> redBlackTree) {
		Node<K, V> root = redBlackTree.getRoot();
		if (isRed(root) || !isColouredProperlyAndSorted(root)) {
			return false;
		}
		try {
			verifyPathsHaveEqualBlackLength(root);
		} catch (IllegalStateException exception) {
			return false;
		}
		return true;
	}

	private static <K extends Comparable<K>, V> boolean isColouredProperlyAndSorted(Node<K, V> father) {
		if (father == null) {
			return true;
		}
		Node<K, V> leftSon = father.getLeft();
		Node<K, V> rightSon = father.getRight();
		if (father.isRed()) {
			if (isRed(leftSon)) {
				return false;
			} else if (isRed(rightSon)) {
				return false;
			}
		}
		if (leftSon != null && !isKeySmallerThanNode(leftSon.getKey(), father) ||
				rightSon != null && !isKeyBiggerThanNode(rightSon.getKey(), father)) {
			return false;
		}
		return isColouredProperlyAndSorted(leftSon) && isColouredProperlyAndSorted(rightSon);
	}

	private static <K extends Comparable<K>, V> int verifyPathsHaveEqualBlackLength(Node<K, V> node) throws IllegalStateException {
		if (node == null) {
			return 1;
		}
		int leftPathBlackLength = verifyPathsHaveEqualBlackLength(node.getLeft());
		int rightPathBlackLength = verifyPathsHaveEqualBlackLength(node.getRight());
		if (leftPathBlackLength != rightPathBlackLength) {
			throw new IllegalStateException(UNEQUAL_BLACK_LENGTHS_MESSAGE);
		}
		if (!node.isRed()) {
			leftPathBlackLength++;
		}
		return leftPathBlackLength;
	}
}
