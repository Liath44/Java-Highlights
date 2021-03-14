package aisd.lab3.rbtree;

import org.junit.Test;

import static aisd.lab3.rbtree.RedBlackTreeVerifier.isProperRedBlackTree;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RedBlackTreeTest {
	private final static int NUMBER_OF_NODES_IN_SOME_TESTS = 14;

	@Test(expected = IllegalArgumentException.class)
	public void ifNodeWithNullKeyIsPutThenIllegalArgumentExceptionIsThrown() {
		//given
		RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
		//when
		redBlackTree.put(null, 5);
		//then
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void ifNodeWithNullValueIsPutThenIllegalArgumentExceptionIsThrown() {
		//given
		RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
		//when
		redBlackTree.put(5, null);
		//then
		fail();
	}

	@Test
	public void ifTreeHasNoElementsThenItIsProper() {
		//given
		RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
		//then
		assertTrue(isProperRedBlackTree(redBlackTree));
	}

	@Test
	public void ifSingleValueIsPutOnTreeThenItIsProper() {
		//given
		RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
		//when
		redBlackTree.put(1, 1);
		//then
		assertTrue(isProperRedBlackTree(redBlackTree));
	}

	@Test
	public void ifMultipleValuesArePutAscendingThenTreeIsProper() {
		//given
		RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
		//when
		for (int i = 0; i < NUMBER_OF_NODES_IN_SOME_TESTS; i++) {
			redBlackTree.put(i, i);
		}
		//then
		assertTrue(isProperRedBlackTree(redBlackTree));
	}

	@Test
	public void ifMultipleValuesArePutDescendingThenTreeIsProper() {
		//given
		RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
		//when
		for (int i = NUMBER_OF_NODES_IN_SOME_TESTS; i > 0; i--) {
			redBlackTree.put(i, i);
		}
		//then
		assertTrue(isProperRedBlackTree(redBlackTree));
	}

	@Test
	public void ifNodeWithAlreadyExistingKeyIsAddedThenItsValueIsOverriden() {
		//given
		Integer key = 1;
		Integer oldValue = 1;
		Integer newValue = 2;
		RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
		redBlackTree.put(key, oldValue);
		//when
		redBlackTree.put(key, newValue);
		//then
		assertEquals(newValue, redBlackTree.getRoot().getValue());
	}

	@Test
	public void ifNodeWithAlreadyExistingKeyIsAddedThenNoNewNodesAreCreated() {
		//given
		Integer key = 1;
		Integer oldValue = 1;
		Integer newValue = 2;
		RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
		redBlackTree.put(key, oldValue);
		//when
		redBlackTree.put(key, newValue);
		//then
		assertNull(redBlackTree.getRoot().getLeft());
		assertNull(redBlackTree.getRoot().getRight());
	}

	@Test
	public void ifRedBlackTreeStoresStringsThenItStillBehavesProperly() {
		//given
		RedBlackTree<String, String> redBlackTree = new RedBlackTree<>();
		//when
		redBlackTree.put("sample", "sample");
		redBlackTree.put("szczupak", "szczupak");
		redBlackTree.put("king of lake", "king of lake");
		redBlackTree.put("JJBA part 3 is mediocre at best", "JJBA part 3 is mediocre at best");
		redBlackTree.put("<3", "<3");
		//then
		assertTrue(isProperRedBlackTree(redBlackTree));
	}

	@Test
	public void ifValuesArePutAtRandomThenTreeIsStillProper() {
		//given
		RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
		//when
		putRandomValuesToTree(redBlackTree);
		//then
		assertTrue(isProperRedBlackTree(redBlackTree));
	}

	private void putRandomValuesToTree(RedBlackTree<Integer, Integer> redBlackTree) {
		redBlackTree.put(6, 6);
		redBlackTree.put(27, 27);
		redBlackTree.put(9, 9);
		redBlackTree.put(11, 11);
		redBlackTree.put(30, 30);
		redBlackTree.put(26, 26);
		redBlackTree.put(16, 16);
		redBlackTree.put(27, 27);
		redBlackTree.put(24, 24);
		redBlackTree.put(1, 1);
		redBlackTree.put(6, 6);
		redBlackTree.put(10, 10);
		redBlackTree.put(29, 29);
		redBlackTree.put(4, 4);
		redBlackTree.put(17, 17);
		redBlackTree.put(22, 22);
	}
}
