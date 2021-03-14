package aisd.lab3.rbtree;

import org.junit.Test;

import static aisd.lab3.rbtree.RedBlackTreeVerifier.isProperRedBlackTree;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static aisd.lab3.rbtree.Color.BLACK;
import static aisd.lab3.rbtree.Color.RED;

public class RedBlackTreeVerifierTest {
	@Test
	public void ifNullIsVerifiedThenItIsProper() {
		//given
		RedBlackTree<Integer, Integer> treeWithNullRoot = new RedBlackTree<>();
		//when
		boolean verificationResult = isProperRedBlackTree(treeWithNullRoot);
		//then
		assertTrue(verificationResult);
	}

	@Test
	public void ifTreeConsistsOfOneBlackNodeThenItIsProper() {
		//given
		Node<Integer, Integer> root = new Node<>(1, 1);
		root.setColor(BLACK);
		RedBlackTree<Integer, Integer> tree = new RedBlackTree<>(root);
		//when
		boolean verificationResult = isProperRedBlackTree(tree);
		//then
		assertTrue(verificationResult);
	}

	@Test
	public void ifTreeConsistsOfOneRedNodeThenItIsImproper() {
		//given
		Node<Integer, Integer> redRoot = new Node<>(1, 1);
		redRoot.setColor(RED);
		RedBlackTree<Integer, Integer> tree = new RedBlackTree<>(redRoot);
		//when
		boolean verificationResult = isProperRedBlackTree(tree);
		//then
		assertFalse(verificationResult);
	}

	@Test
	public void ifTreeConsistsOfFourProperNodesThenItIsProper() {
		//given
		RedBlackTree<Integer, Integer> tree = new RedBlackTree<>(createRootOfFourNodedTree());
		//when
		boolean verificationResult = isProperRedBlackTree(tree);
		//then
		assertTrue(verificationResult);
	}

	private Node<Integer, Integer> createRootOfFourNodedTree() {
		Node<Integer, Integer> node4 = new Node<>(4, 4, null, null, RED);
		Node<Integer, Integer> node3 = new Node<>(3, 3, null, node4, BLACK);
		Node<Integer, Integer> node1 = new Node<>(1, 1, null, null, BLACK);
		return new Node<>(2, 2, node1, node3, BLACK);
	}

	@Test
	public void ifNodeInTreeHasWronglyColouredSonsThenItIsImproper() {
		//given
		RedBlackTree<Integer, Integer> improperTree = new RedBlackTree<>(createRootOfFourNodedTree());
		improperTree.getRoot().getRight().setColor(RED);
		//when
		boolean verificationResult = isProperRedBlackTree(improperTree);
		//then
		assertFalse(verificationResult);
	}

	@Test
	public void ifTreeHasFiveImproperlyPlacedNodesThenItIsImproper() {
		//given
		Node<Integer, Integer> rootOfImproperLeftSon = createRootOfFourNodedTree();
		Node<Integer, Integer> improperRoot = new Node<>(7, 7, rootOfImproperLeftSon, null, BLACK);
		RedBlackTree<Integer, Integer> improperTree = new RedBlackTree<>(improperRoot);
		//when
		boolean verificationResult = isProperRedBlackTree(improperTree);
		//then
		assertFalse(verificationResult);
	}

	@Test
	public void ifFourNodedTreeIsNotSortedThenItIsImproper() {
		//given
		Node<Integer, Integer> improperRoot = createRootOfFourNodedTree();
		improperRoot.setKey(44);
		RedBlackTree<Integer, Integer> improperTree = new RedBlackTree<>(improperRoot);
		//when
		boolean verificationResult = isProperRedBlackTree(improperTree);
		//then
		assertFalse(verificationResult);
	}

	@Test
	public void ifTreeConsistsOfTenProperNodesThenItIsProper() {
		//given
		Node<Integer, Integer> root = createRootOfTenNodedTree();
		RedBlackTree<Integer, Integer> tree = new RedBlackTree<>(root);
		//when
		boolean verificationResult = isProperRedBlackTree(tree);
		//then
		assertTrue(verificationResult);
	}

	private Node<Integer, Integer> createRootOfTenNodedTree() {
		Node<Integer, Integer> node9 = new Node<>(9, 9, null, null, RED);
		Node<Integer, Integer> node7 = new Node<>(7, 7, null, null, RED);
		Node<Integer, Integer> node8 = new Node<>(8, 8, node7, node9, BLACK);
		Node<Integer, Integer> node5 = new Node<>(5, 5, null, null, BLACK);
		Node<Integer, Integer> node6 = new Node<>(6, 6, node5, node8, RED);
		Node<Integer, Integer> node0 = new Node<>(0, 0, null, null, RED);
		Node<Integer, Integer> node1 = new Node<>(1, 1, node0, null, BLACK);
		Node<Integer, Integer> node3 = new Node<>(3, 3, null, null, BLACK);
		Node<Integer, Integer> node2 = new Node<>(2, 2, node1, node3, RED);
		return new Node<>(4, 4, node2, node6, BLACK);
	}

	@Test
	public void ifTenNodedTreeContainsUnequalBlackPathsThenItIsImproper() {
		//given
		Node<Integer, Integer> improperRoot = createRootOfTenNodedTree();
		improperRoot.getRight().getRight().setColor(RED);
		RedBlackTree<Integer, Integer> improperTree = new RedBlackTree<>(improperRoot);
		//when
		boolean verificationResult = isProperRedBlackTree(improperTree);
		//then
		assertFalse(verificationResult);
	}

	@Test
	public void ifTenNodedTreeIsNotSortedThenItIsImproper() {
		//given
		Node<Integer, Integer> improperRoot = createRootOfTenNodedTree();
		improperRoot.getRight().getRight().setKey(44);
		RedBlackTree<Integer, Integer> improperTree = new RedBlackTree<>(improperRoot);
		//when
		boolean verificationResult = isProperRedBlackTree(improperTree);
		//then
		assertFalse(verificationResult);
	}

	@Test
	public void ifTreeContainsStringsThenItCanBeVerified() {
		//given
		Node<String, String> stringRoot = createStringBasedTree();
		RedBlackTree<String, String> stringTree = new RedBlackTree<>(stringRoot);
		//then
		boolean verificationResult = isProperRedBlackTree(stringTree);
		assertTrue(verificationResult);
	}

	private Node<String, String> createStringBasedTree() {
		Node<String, String> nodeF = new Node<>("f", "f", null, null, RED);
		Node<String, String> nodeG = new Node<>("g", "g", nodeF, null, BLACK);
		Node<String, String> nodeJ = new Node<>("j", "j", null, null, RED);
		Node<String, String> nodeI = new Node<>("i", "i", null, nodeJ, BLACK);
		return new Node<>("h", "h", nodeG, nodeI, BLACK);
	}
}
