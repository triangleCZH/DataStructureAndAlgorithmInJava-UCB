import java.util.ArrayList;

/** A Generic heap class. Unlike Java's priority queue, this heap doesn't just
  * store Comparable objects. Instead, it can store any type of object
  * (represented by type T) and an associated priority value.
  * @author CS 61BL Staff */
public class ArrayHeap<T> {

	/* DO NOT CHANGE THESE METHODS. */

	/* An ArrayList that stores the nodes in this binary heap. */
	private ArrayList<Node> contents;

	/* A constructor that initializes an empty ArrayHeap. */
	public ArrayHeap() {
		contents = new ArrayList<>();
		contents.add(null);
	}

	/* Returns the node at index INDEX. */
	private Node getNode(int index) {
		if (index >= contents.size()) {
			return null;
		} else {
			return contents.get(index);
		}
	}

	private void setNode(int index, Node n) {
		// In the case that the ArrayList is not big enough
		// add null elements until it is the right size
		while (index + 1 >= contents.size()) {
			contents.add(null);
		}
		contents.set(index, n);
	}

	/* Swap the nodes at the two indices. */
	private void swap(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		this.contents.set(index1, node2);
		this.contents.set(index2, node1);
	}

	/* Prints out the heap sideways. Use for debugging. */
	@Override
	public String toString() {
		return toStringHelper(1, "");
	}

	/* Recursive helper method for toString. */
	private String toStringHelper(int index, String soFar) {
		if (getNode(index) == null) {
			return "";
		} else {
			String toReturn = "";
			int rightChild = getRightOf(index);
			toReturn += toStringHelper(rightChild, "        " + soFar);
			if (getNode(rightChild) != null) {
				toReturn += soFar + "    /";
			}
			toReturn += "\n" + soFar + getNode(index) + "\n";
			int leftChild = getLeftOf(index);
			if (getNode(leftChild) != null) {
				toReturn += soFar + "    \\";
			}
			toReturn += toStringHelper(leftChild, "        " + soFar);
			return toReturn;
		}
	}

	/* A Node class that stores items and their associated priorities. */
	public class Node {
		private T item;
		private double priority;

		private Node(T item, double priority) {
			this.item = item;
			this.priority = priority;
		}

		public T item(){
			return this.item;
		}

		public double priority() {
			return this.priority;
		}

		@Override
		public String toString() {
			return this.item.toString() + ", " + this.priority;
		}
	}



	/* FILL IN THE METHODS BELOW. */

	/* Returns the index of the node to the left of the node at i. */
	private int getLeftOf(int i) {
		//YOUR CODE HERE
		if (contents.size() > 2 * i) {
			return 2 * i;
		} else return 0;
	}

	/*private void sortPriority(Node n) {
		while ((getNode(getRightOf(contents.indexOf(n))) != null && getNode(getRightOf(contents.indexOf(n))).priority < n.priority)
				|| (getNode(getLeftOf(contents.indexOf(n))) != null && getNode(getLeftOf(contents.indexOf(n))).priority < n.priority)) {
			bubbleDown(contents.indexOf(n));
		}
		while ((getNode(getParentOf(contents.indexOf(n))) != null && getNode(getParentOf(contents.indexOf(n))).priority > n.priority)) {
			bubbleUp(contents.indexOf(n));
		}
	}*/


	/* Returns the index of the node to the right of the node at i. */
	private int getRightOf(int i) {
		//YOUR CODE HERE
		if (contents.size()> 2 * i + 1) { // make a standard for the invalid input requests
			return 2 * i + 1;
		} else return 0;
	}

	/* Returns the index of the node that is the parent of the node at i. */
	private int getParentOf(int i) {
		//YOUR CODE HERE
		return i / 2;
	}

	/* Adds the given node as a left child of the node at the given index. */
	private void setLeft(int index, Node n) {
		//YOUR CODE HERE // try not to exceed the bound!
		int leftIndex = getLeftOf(index);
		if (leftIndex == 0) {
			return;
		}
		contents.set(leftIndex, n);

	}

	/* Adds the given node as the right child of the node at the given index. */
	private void setRight(int index, Node n) {
		//YOUR CODE HERE
		int rightIndex = getRightOf(index);
		if (rightIndex == 0) {
			return;
		}
		contents.set(rightIndex, n);
	}

	/** Returns the index of the node with smaller priority. Precondition: not
	  * both nodes are null. */
	private int min(int index1, int index2) {
		//YOUR CODE HERE
        if (getNode(index1) == null) {
			return index2;
		} else if (getNode(index2) == null) {
			return index1;
		} else {
			if (getNode(index1).priority > getNode(index2).priority) {
				return index2;
			} else {
				return index1;
			}
		}
	}

	/* Returns the Node with the smallest priority value, but does not remove it
	 * from the heap. */
	public Node peek() {
		//YOUR CODE HERE
		return getNode(1);
	}

	/* Bubbles up the node currently at the given index. */
	private void bubbleUp(int index) {
		//YOUR CODE HERE
		while (index > 0 && getParentOf(index) != 0) {
			if (min(index, getParentOf(index)) == index) {
				swap(index,getParentOf(index));
				bubbleDown(index); // TODO: 7/29/16  don't understand this
				index = getParentOf(index);
			} else {
				break;
			}
		}
	}

	/* Bubbles down the node currently at the given index. */
	private void bubbleDown(int index) {
		//YOUR CODE HERE
		while (index < contents.size() && (getLeftOf(index) !=0 || getRightOf(index) != 0)) {
			int chosen = min((getLeftOf(index)), getRightOf(index)); //since it can handle null pointers!!
			if (min(chosen, index) == chosen) {                      //that is why we don't need that long at All!
				swap(index, chosen);
				bubbleUp(index);
				index = chosen;
			} else {
				break;
			}
		}
		/*if (getNode(index) == null) {
			return;
		}
		if (getNode(getLeftOf(index)) != null && getNode(getRightOf(index)) != null) {
			if (getNode(index).priority > getNode(getLeftOf(index)).priority
					&& (getNode(index).priority > getNode(getRightOf(index)).priority)) {
				swap(index, min(getLeftOf(index), getRightOf(index)));
			} else if (getNode(index).priority > getNode(getLeftOf(index)).priority) {
				swap(index, getLeftOf(index));
			} else if (getNode(index).priority > getNode(getRightOf(index)).priority) {
				swap(index, getLeftOf(index));
			}
		} else {
			if (getNode(getLeftOf(index)) != null) {
				if (getNode(index).priority > getNode(getLeftOf(index)).priority) {
                swap(index, getLeftOf(index));
				}
			}
			if (getNode(getRightOf(index)) != null) {
				if (getNode(index).priority > getNode(getRightOf(index)).priority) {
					swap(index, getLeftOf(index));
				}
			}
		}*/

	}

	/* Inserts an item with the given priority value. Same as enqueue, or offer. */
	public void insert(T item, double priority) {
		//YOUR CODE HERE
        Node temp = new Node(item, priority);
        contents.add(temp);
        bubbleUp(contents.size() - 1); // since this time bubble itself can handle
		//a loop so no need for an extra sort method
	}

	/* Returns the Node with the smallest priority value, and removes it from
	 * the heap. Same as dequeue, or poll. */
	public Node removeMin() {
		//YOUR CODE HERE
        if (peek() == null) {
            return null;
        } else if (contents.size() == 2) {
            return contents.remove(1);
        } else {
            Node store = getNode(1);
            swap(1, contents.size() - 1);
            contents.remove(contents.size() - 1);
            /*while (getNode(contents.size() - 1) == null) {
                contents.remove(contents.size() - 1);
            }*/
            bubbleDown(1);
            return store;
        }
	}

	/* Changes the node in this heap with the given item to have the given
	 * priority. You can assume the heap will not have two nodes with the same
	 * item. Check for item equality with .equals(), not == */
	public void changePriority(T item, double priority) {
        boolean found = false;
        int i;
        for (i = 1; i < contents.size() && !found; i++) {
            if (getNode(i) != null) {
                if (getNode(i).item.equals(item)) {
                found = true;
					setNode(i, new Node(getNode(i).item, priority));
					Node temp = getNode(i);
					//sortPriority(temp);
					}
            }
        }
		if (!found) {
			return;
		}
		if (getParentOf(i) != 0 && min(getParentOf(i), i) == i) {
			bubbleUp(i); // after one if condition, just do the bubble up which automatically make the whole process
		} else if (i * 2 <contents.size() && !(getLeftOf(i) == 0 && getRightOf(i) == 0)) {
			bubbleDown(i); // there is judgement in the bubble method so we don't need to compare
			// it to its child here
		}



	}

}
