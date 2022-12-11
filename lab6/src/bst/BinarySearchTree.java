package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comparator;
  
  
  public static void main(String[] args) {
	  BSTVisualizer v = new BSTVisualizer("Tree", 800, 800);
	  BinarySearchTree<Integer> tree = new BinarySearchTree<>();
	  tree.add(2);
	  tree.add(5);
	  tree.add(3);
	  tree.add(6);
	  tree.add(7);
	  tree.add(8);
	  tree.add(1);
	  tree.printTree();
	  v.drawTree(tree);
	  tree.rebuild();
	  //v.drawTree(tree);
  }
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		size = 0;
		root = null;
		comparator = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		size = 0;
		root = null;
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		return add(root, x);
	}
	
	private boolean add(BinaryNode<E> e, E x) {
		if(root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}else if(x == e.element) {
			return false;
		}else if(comparator.compare(x, e.element) > 0) {
			if(e.right == null) {
				e.right = new BinaryNode<E>(x);
				size++;
				return true;
			}else {
				return add(e.right, x);
			}
		}else {
			if(e.left == null) {
				e.left = new BinaryNode<E>(x);
				size++;
				return true;
			}else {
				return add(e.left, x);
			}
		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> e) {
		if(e == null) {
			return 0;
		}else{
			return 1 + Math.max(height(e.left), height(e.right));
		}
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		size = 0;
		root  = null;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}
	
	private void printTree(BinaryNode<E> e) {
		if(e != null) {
			printTree(e.left);
			System.out.println(e.element);
			printTree(e.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> list = new ArrayList<>();
		toArray(root, list);
		root = buildTree(list, 0, list.size() - 1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if(n != null) {
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if(first > last) {
			return null;
		}
		int midpoint = (first + last) / 2;
		E x = sorted.get(midpoint);
		BinaryNode<E> newRoot = new BinaryNode<>(x);
		newRoot.left = buildTree(sorted, first, midpoint - 1);
		newRoot.right = buildTree(sorted, midpoint + 1, last);
		return newRoot;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
