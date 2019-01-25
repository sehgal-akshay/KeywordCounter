
public class treeNode {

	treeNode left, right, child, parent;
	int degree = 0;
	boolean childCut = false;
	private String data;
	int count;

	// constructor
	public treeNode(String data, int count) {
		// initializing the variables
		this.left = this;
		this.right = this;
		this.parent = null;
		this.degree = 0;
		this.data = data;
		this.count = count;
	}

	// return the data field i.e. the keyword name
	public String getData() {
		return data;
	}

}
