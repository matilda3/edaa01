package testTree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bst.BinarySearchTree;
import bst.Book;

class testBST {
	private BinarySearchTree<Integer> tree;
	private BinarySearchTree<Book> tree2;

	@BeforeEach
	void setUp() throws Exception {
		tree = new BinarySearchTree<>();
		tree2 = new BinarySearchTree<>((e1, e2) -> e1.getNum() - e2.getNum());		
	}

	@AfterEach
	void tearDown() throws Exception {
		tree.clear();
		tree2.clear();
	}

	
	@Test
	void testMethods() {
		tree.add(5);
		tree.add(2);
		tree.add(3);
		assertTrue(tree.add(1));
		tree.add(9);
		assertFalse(tree.add(9));
		tree.printTree();
		assertEquals(3, tree.height());
		assertEquals(5, tree.size());
		tree.clear();
		assertEquals(0, tree.height());
		assertEquals(0, tree.size());
	}
	
	@Test
	void testEmpty() {
		assertEquals(0, tree.height());
		assertEquals(0, tree.size());
		assertEquals(0, tree2.height());
		assertEquals(0, tree2.size());
	}
	
	@Test
	void testClear() {
		tree.add(0);
		tree.add(1);
		tree.clear();
		assertEquals(0, tree.size());
	}
	
	@Test
	void testComp() {
		Book a = new Book("a", "author", 1);
		Book b = new Book("b", "author", 2);
		Book c = new Book("c", "author", 11);
		Book d = new Book("d", "author", 5);
		
		tree2.add(b);
		tree2.add(a);
		tree2.add(d);
		tree2.add(c);
		
		assertEquals(4, tree2.size());
		assertEquals(3, tree2.height());
		tree2.printTree();
	}
	

}
