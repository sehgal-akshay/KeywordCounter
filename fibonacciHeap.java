import java.util.HashMap;
import java.util.Set;

public class fibonacciHeap {

	// store the value of max node
	private treeNode maxNode;

	public void insert(treeNode node) {
		// TODO Auto-generated method stub
		if (maxNode != null) {
			
			// concatenate the root list containing x with root list H
			concatenateInRootList(node);

			// comparing the value of count of the node with the max node
			if (node.count > maxNode.count) {
				maxNode = node;
			}

		} else {
			maxNode = node;

		}
	}

	// function to put tree nodes in the root list
	private void concatenateInRootList(treeNode node) {
		// TODO Auto-generated method stub
		treeNode mx = maxNode;
		node.left = mx;
		node.right = mx.right;
		mx.right = node;

		if (node.right != null) {
			node.right.left = node;
		}
		if (node.right == null) {
			node.right = maxNode;
			maxNode.left = node;
		}

	}

	// function to remove max node from the heap
	public treeNode removeMax() {
		treeNode mx = maxNode;
		if (mx != null) {
			int numberOfChlildren = mx.degree;
			treeNode mxChild = mx.child;
			treeNode nextChild;

			while (numberOfChlildren > 0) {
				nextChild = mxChild.right;

				mxChild.left.right = mxChild.right;
				mxChild.right.left = mxChild.left;

				mxChild.left = maxNode;
				mxChild.right = maxNode.right;
				maxNode.right = mxChild;
				mxChild.right.left = mxChild;

				mxChild.parent = null;
				mxChild = nextChild;
				numberOfChlildren--;
			}

			mx.left.right = mx.right;
			mx.right.left = mx.left;

			if (mx.right == mx) {
				maxNode = null;
			} else {
				maxNode = mx.right;
				consolidateHeap();
			}
			mx.degree = 0;
			mx.parent = null;
			mx.child = null;
			mx.left = mx;
			mx.right = mx;
			mx.childCut = false;
			return mx;
		}

		return null;
	}

	public void increaseCount(treeNode node, int newCount) {

		if (node.count > newCount) {
			// only possible if negative value is present in the input file
			// throw error
		}
		node.count = newCount;
		treeNode nodeParent = node.parent;

		if (nodeParent != null && node.count > nodeParent.count) {
			cut(node, nodeParent);
			cascadingCut(nodeParent);
		}
		if (node.count > maxNode.count) {
			maxNode = node;
		}
	}
	
	//used to perform cut operation
	private void cut(treeNode node, treeNode nodeParent) {

		node.right.left = node.left;
		node.left.right = node.right;
		nodeParent.degree--;

		if (nodeParent.child == node) {
			nodeParent.child = node.right;
		}

		if (nodeParent.degree == 0) {
			nodeParent.child = null;
		}
		concatenateInRootList(node);
		node.parent = null;
		node.childCut = false;

	}
	//used to perform cascading cut
	private void cascadingCut(treeNode node) {

		treeNode nodeParent = node.parent;
		if (node.parent != null) {
			if (!node.childCut) {
				node.childCut = true;
			} else {
				cut(node, nodeParent);

				cascadingCut(nodeParent);
			}
		}

	}
	//performs consolidate and pair wise merge operation
	public void consolidateHeap() {		
		
		HashMap<Integer, treeNode> degreeTable = new HashMap<Integer, treeNode>();

		// Find the number of root nodes

		int rootCount = 0;
		treeNode root = maxNode;

		if (root != null) {
			rootCount++;
			root = root.right;

			while (root != maxNode) {
				rootCount++;
				root = root.right;
			}
		}

		while (rootCount > 0) {
			int deg = root.degree;
			treeNode nextRoot = root.right;

			for (;;) {
				
				if(degreeTable.containsKey(deg)) {
					treeNode y = degreeTable.get(deg);
					if (root.count < y.count) {
						HeapLink(root, nextRoot);
					} else {
						HeapLink(y, root);
					}
					// swapping the values
					if (root.count < y.count) {
						treeNode temp = y;
						y = root;
						root = temp;
					}
					degreeTable.remove(deg);
					deg++;
				}else {
					break;
				}
			}
			degreeTable.put(deg, root);

			// Move forward through list.
			root = nextRoot;
			rootCount--;
		}

		// Deleting the max node
		maxNode = null;
		
		Set<Integer> degreeset = degreeTable.keySet();
		
		// combine entries of the degree table
		for(Integer i:degreeset) {
			treeNode node = degreeTable.get(i);
			if (node == null) {
				continue;
			}

			// till max node is not null
			if (maxNode != null) {

				// First remove node from root list.
				node.left.right = node.right;
				node.right.left = node.left;

				// Now add to root list, again.
				node.left = maxNode;
				node.right = maxNode.right;
				maxNode.right = node;
				node.right.left = node;

				// Check if this is a new maximum
				if (node.count > maxNode.count) {
					maxNode = node;
				}
			} else {
				maxNode = node;
			}
		}

	}

	// Linking for same degree nodes. The value of x is greater than y
	public void HeapLink(treeNode y, treeNode x) {
		// Removing y from the root list
		y.left.right = y.right;
		y.right.left = y.left;

		// Make y as child of x
		y.parent = x;

		if (x.child == null) {
			x.child = y;
			y.right = y;
			y.left = y;
		} else {
			y.left = x.child;
			y.right = x.child.right;
			x.child.right = y;
			y.right.left = y;
		}

		// Incrementing the degree of x because of addition of one more child.
		x.degree++;

		// Marking child cut as false
		y.childCut = false;

	}

	// this function returns the log base 2 value for any integer n
	public static double log2(int n) {
		return (Math.log(n) / Math.log(2));
	}
}
