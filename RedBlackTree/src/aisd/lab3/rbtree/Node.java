package aisd.lab3.rbtree;

import static aisd.lab3.rbtree.Color.RED;

public class Node<K extends Comparable<K>, V> {
	private K key;
	private V value;
	private Node<K, V> left, right;
	private Color color;

	public Node(K key, V value) {
		setPair(key, value);
		this.color = RED;
	}

	public Node(K key, V value, Node<K, V> left, Node<K, V> right, Color color) {
		setPair(key, value);
		setTreeRelatedParameters(left, right, color);
	}

	public Node<K, V> createClone() {
		return new Node<>(key, value, left, right, color);
	}

	public void setPair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public void setTreeRelatedParameters(Node<K, V> left, Node<K, V> right, Color color) {
		this.left = left;
		this.right = right;
		this.color = color;
	}

	public boolean isRed() {
		return RED.equals(color);
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public Node<K, V> getLeft() {
		return left;
	}

	public void setLeft(Node<K, V> leftNode) {
		left = leftNode;
	}

	public Node<K, V> getRight() {
		return right;
	}

	public void setRight(Node<K, V> rightNode) {
		right = rightNode;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
