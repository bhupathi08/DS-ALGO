package binarysearchtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Tree {

	int data;
	Tree left;
	Tree right;

	public Tree() {

	}

	public Tree(int data) {
		this.data = data;
	}

}

public class BinarySearchTreeImpl {

	Tree tree;

	public BinarySearchTreeImpl() {
		tree = null;
	}

	public void insert(int key) {
		tree = insertData(tree, key);
		System.out.println("Data inserted: " + key);
	}

	private Tree insertData(Tree root, int key) {
		if (root == null) {
			root = new Tree(key);
		} else if (key < root.data) {
			root.left = insertData(root.left, key);
		} else if (key > root.data) {
			root.right = insertData(root.right, key);
		}
		return root;
	}

	public void delete(int key) {
		tree = deleteData(tree, key);
		System.out.println("Data deleted: " + key);
	}

	private Tree deleteData(Tree root, int key) {
		if (root == null) {
			return root;
		} else if (key < root.data) {
			root.left = deleteData(root.left, key);
		} else if (key > root.data) {
			root.right = deleteData(root.right, key);
		} else {
			if(root.left == null && root.right == null) {
				root = null;
			} else if (root.left != null && root.right == null) {
				root = root.left;
			} else if (root.right != null && root.left == null) {
				root = root.right;
			} else {
				List<Integer> inorder = inorder(root);
				int predecessor = inorder.get(inorder.indexOf(key)-1);
				int successor = inorder.get(inorder.indexOf(key)+1);
				if (findHeight(root.left) >= findHeight(root.right)) {
					root.data = predecessor;
					root.left = deleteData(root.left, predecessor);
				} else if (findHeight(root.left) < findHeight(root.right)) {
					root.data = successor;
					root.right = deleteData(root.right, successor);
				}
			}
		}
		return root;
	}

	public void search(int key) {
		Tree result = searchData(tree, key);
		if (result != null) {
			System.out.println("Data found: " + key);
		} else {
			System.out.println("Data not found");
		}
	}

	private Tree searchData(Tree root, int key) {
		if (root == null) {
			return root;
		} else {
			if (root.data == key) {
				return root;
			} else if (key < root.data) {
				root = searchData(root.left, key);
			} else if (key > root.data) {
				root = searchData(root.right, key);
			}
		}
		return root;
	}

	public void height() {
		System.out.println("Height of the tree is: " + findHeight(tree));
	}

	public int findHeight(Tree root) {
		int count = 0;
		if (root == null) {
			return count;
		}
		count = Math.max(findHeight(root.left), findHeight(root.right)) + 1;
		return count;
	}

	public List<Integer> inorder(Tree root) {
		List<Integer> data = new ArrayList<Integer>();
		if (root != null) {
			data.addAll(inorder(root.left));
			data.add(root.data);
			data.addAll(inorder(root.right));
		}
		return data;
	}

	public int[] inorderTraversal() {
		List<Integer> keys = new ArrayList<Integer>();
		keys.addAll(inorder(tree));
		return keys.stream().mapToInt(i -> i).toArray();
	}

	public List<Integer> preorder(Tree root) {
		List<Integer> data = new ArrayList<Integer>();
		if (root != null) {
			data.add(root.data);
			data.addAll(preorder(root.left));
			data.addAll(preorder(root.right));
		}
		return data;
	}

	public int[] preorderTraversal() {
		List<Integer> keys = new ArrayList<Integer>();
		keys.addAll(preorder(tree));
		return keys.stream().mapToInt(i -> i).toArray();
	}

	public List<Integer> postorder(Tree root) {
		List<Integer> data = new ArrayList<Integer>();
		if (root != null) {
			data.addAll(postorder(root.left));
			data.addAll(postorder(root.right));
			data.add(root.data);
		}
		return data;
	}

	public int[] postorderTraversal() {
		List<Integer> keys = new ArrayList<Integer>();
		keys.addAll(postorder(tree));
		return keys.stream().mapToInt(i -> i).toArray();
	}

	public int findInorderPredesessor(Tree root) {
		return root.data;
	}

	public int findInorderSuccessor(Tree root) {
		return root.data;
	}

	public static void main(String[] args) {
		BinarySearchTreeImpl binarySearchTreeImpl = new BinarySearchTreeImpl();
		binarySearchTreeImpl.insert(50);
		binarySearchTreeImpl.insert(30);
		binarySearchTreeImpl.insert(20);
		binarySearchTreeImpl.insert(40);
		binarySearchTreeImpl.insert(70);
		binarySearchTreeImpl.insert(60);
		binarySearchTreeImpl.insert(80);
		binarySearchTreeImpl.search(20);
		binarySearchTreeImpl.height();
		System.out.println("In-order Traversal: " + Arrays.toString(binarySearchTreeImpl.inorderTraversal()));
		binarySearchTreeImpl.delete(20);
		System.out.println("In-order Traversal: " + Arrays.toString(binarySearchTreeImpl.inorderTraversal()));
		binarySearchTreeImpl.delete(30);
		System.out.println("In-order Traversal: " + Arrays.toString(binarySearchTreeImpl.inorderTraversal()));
		binarySearchTreeImpl.delete(50);
		System.out.println("In-order Traversal: " + Arrays.toString(binarySearchTreeImpl.inorderTraversal()));
	}
}
