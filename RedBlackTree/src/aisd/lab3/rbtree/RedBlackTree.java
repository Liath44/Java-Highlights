package aisd.lab3.rbtree;

import static aisd.lab3.rbtree.Color.BLACK;
import static aisd.lab3.rbtree.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {
	private final static String NULL_INPUT_MESSAGE = "Input params (key, value) cannot be null.";
	private Node<K, V> root;

	public RedBlackTree() {
		root = null;
	}

	public RedBlackTree(Node<K, V> root) {
		this.root = root;
	}

	public void put(K key, V value) {
		validateParams(key, value);
		if (root == null) {
			root = new Node<>(key, value);
		} else {
			put(root, key, value);
		}
		root.setColor(BLACK);
	}

	private void validateParams(K key, V value) {
		if (key == null || value == null) {
			throw new IllegalArgumentException(NULL_INPUT_MESSAGE);
		}
	}

	private void put(Node<K, V> node, K key, V value) {
		if (isKeyBiggerThanNode(key, node)) {
			putOnTheRight(node, key, value);
		} else if (isKeySmallerThanNode(key, node)) {
			putOnTheLeft(node, key, value);
		} else {
			node.setValue(value);
		}
		reorganizeTree(node);
	}

	public static <L extends Comparable<L>, R> boolean isKeyBiggerThanNode(L key, Node<L, R> node) {
		return key.compareTo(node.getKey()) > 0;
	}

	private void putOnTheRight(Node<K, V> node, K key, V value) {
		Node<K, V> rightChild = node.getRight();
		if (rightChild == null) {
			node.setRight(new Node<>(key, value));
		} else {
			put(rightChild, key, value);
		}
	}

	public static <L extends Comparable<L>, R> boolean isKeySmallerThanNode(L key, Node<L, R> node) {
		return key.compareTo(node.getKey()) < 0;
	}

	private void putOnTheLeft(Node<K, V> node, K key, V value) {
		Node<K, V> leftChild = node.getLeft();
		if (leftChild == null) {
			node.setLeft(new Node<>(key, value));
		} else {
			put(leftChild, key, value);
		}
	}

	private void reorganizeTree(Node<K, V> node) {
		rotateLeftIfNeeded(node);
		rotateRightIfNeeded(node);
		changeColorsIfNeeded(node);
	}

	private void rotateLeftIfNeeded(Node<K, V> node) {
		if (isBlack(node.getLeft()) && isRed(node.getRight())) {
			rotateLeft(node);
		}
	}

	private void rotateLeft(Node<K, V> node) {
		Node<K, V> pushedLeftNode = createPushedLeftNode(node, node.getRight().getLeft());
		writeSonToNode(node, node.getRight());
		node.setLeft(pushedLeftNode);
		colourNodesAfterRotation(node, pushedLeftNode);
	}

	private Node<K, V> createPushedLeftNode(Node<K, V> nodeToClone, Node<K, V> newRightSon) {
		Node<K, V> pushedLeftNode = nodeToClone.createClone();
		pushedLeftNode.setRight(newRightSon);
		return pushedLeftNode;
	}

	private void writeSonToNode(Node<K, V> targetNode, Node<K, V> son) {
		targetNode.setPair(son.getKey(), son.getValue());
		targetNode.setTreeRelatedParameters(son.getLeft(), son.getRight(), son.getColor());
	}

	private void colourNodesAfterRotation(Node<K, V> newFather, Node<K, V> pushedNode) {
		newFather.setColor(pushedNode.getColor());
		pushedNode.setColor(RED);
	}

	private void rotateRightIfNeeded(Node<K, V> node) {
		if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
			rotateRight(node);
		}
	}

	private void rotateRight(Node<K, V> node) {
		Node<K, V> pushedRightNode = createPushedRightNode(node, node.getLeft().getRight());
		writeSonToNode(node, node.getLeft());
		node.setRight(pushedRightNode);
		colourNodesAfterRotation(node, pushedRightNode);
	}

	private Node<K, V> createPushedRightNode(Node<K, V> nodeToClone, Node<K, V> newLeftSon) {
		Node<K, V> pushedRightNode = nodeToClone.createClone();
		pushedRightNode.setLeft(newLeftSon);
		return pushedRightNode;
	}

	private void changeColorsIfNeeded(Node<K, V> node) {
		if (isRed(node.getLeft()) && isRed(node.getRight())) {
			changeColors(node);
		}
	}

	private void changeColors(Node<K, V> node) {
		node.setColor(RED);
		node.getLeft().setColor(BLACK);
		node.getRight().setColor(BLACK);
	}

	private boolean isBlack(Node<K, V> node) {
		return !isRed(node);
	}

	public static boolean isRed(Node<?, ?> node) {
		return node != null && node.isRed();
	}

	public Node<K, V> getRoot() {
		return root;
	}
}
