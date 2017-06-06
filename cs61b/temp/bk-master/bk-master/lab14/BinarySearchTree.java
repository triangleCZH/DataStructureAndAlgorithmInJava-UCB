import sun.reflect.generics.tree.Tree;

/** A class implementing a BST.
  * @author CS 61BL Staff.
  */
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	/* Constructs an empty BST. */
	public BinarySearchTree() {
		//YOUR CODE HERE
		root = null;
	}

	/* Constructs a BST with root MYROOT. */
	public BinarySearchTree(TreeNode root) {
		//YOUR CODE HERE
		this.root = root;
	}
	
	/* Returns true if and only if KEY is in the BST. */
	public boolean contains(T key) {
		//YOUR CODE HERE
			return root != null && contains(root,key);
	}

	public boolean contains(TreeNode a, T key) {
		if (a == null) {
			return false;
		}
		if (a.item.compareTo(key) > 0) {
			return contains(a.left,key);
		}
		if (a.item.compareTo(key) < 0) {
			return contains(a.right,key);
		}
		if (a.item.compareTo(key) == 0) {
			return true;
		}
		return false;//fixme: why I need this return here?
	}
	
	/* Adds a node for KEY iff it isn't in the BST already. */
	public void add(T key) {
		if (root == null) {
			root = new TreeNode(key,null,null);
		} else add(root,key);
		//YOUR CODE HERE
	}

	public void add(TreeNode a, T key) {
		if (a == null) {
			a = new TreeNode(key,null,null);
		}
		if (a.item.compareTo(key) == 0)
			return;
		if (a.item.compareTo(key) > 0) {
			if (a.left != null)
			add(a.left,key);
			else a.left = new TreeNode(key,null,null);
		}
		if (a.item.compareTo(key) < 0) {
			if (a.right != null)
			add(a.right,key);
			else a.right = new TreeNode(key,null,null);
		}
	}
	/* Deletes the node with KEY. */
	public T delete(T key) {
		TreeNode parent = null;
		TreeNode curr = root;
		TreeNode delNode = null;
		TreeNode replacement = null;
		boolean rightSide = false;
		
		while (curr != null && !curr.item.equals(key)) {
			if (((Comparable<T>) curr.item).compareTo(key) > 0) {
				parent = curr;
				curr = curr.left;
				rightSide = false; // if the first time is right, then later on will all be left
			} else {
				parent = curr;
				curr = curr.right;
				rightSide = true;
			}
		}
		delNode = curr;
		if (curr == null) { // meaning there is not such a point
			return null;
		}
		
		if (delNode.right == null) {
			if (root == delNode) { // simple move
				root = root.left;
			} else {
				if (rightSide) { // key is smaller than key
					parent.right = delNode.left;
				} else {
					parent.left = delNode.left;
				}
			}
		} else {
			curr = delNode.right;
			replacement = curr.left;
			if (replacement == null) {
				replacement = curr;
			} else {
				while (replacement.left != null) {
					curr = replacement;
					replacement = replacement.left;
				}
				curr.left = replacement.right;
				replacement.right = delNode.right;
			}
			replacement.left = delNode.left;
			if (root == delNode) {
				root = replacement;
			} else {
				if (rightSide) {
					parent.right = replacement;
				} else {
					parent.left = replacement;
				}
			}
		}
		return delNode.item;
	}
	
}