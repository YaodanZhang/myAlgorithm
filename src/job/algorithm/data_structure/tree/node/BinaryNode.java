package job.algorithm.data_structure.tree.node;

/**
 * 普通二叉树的节点，有关键词/父节点/左孩子/右孩子几个域。
 * 
 * @author yaodan.zhang
 * 
 */
public class BinaryNode {

	private int key;
	private BinaryNode parent;
	private BinaryNode leftChild;
	private BinaryNode rightChild;

	public boolean hasLeftChild() {
		return null != leftChild;
	}

	public boolean hasRightChild() {
		return null != rightChild;
	}

	/**
	 * 将节点的内容复制到节点node。
	 * 
	 * @param node
	 */
	public void copyTo(BinaryNode node) {
		node.setKey(key);
	}

	// getters & setters
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public BinaryNode getParent() {
		return parent;
	}

	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}

	public BinaryNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinaryNode leftChild) {
		if (null != leftChild) {
			leftChild.setParent(this);
		}
		this.leftChild = leftChild;
	}

	public BinaryNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinaryNode rightChild) {
		if (null != rightChild) {
			rightChild.setParent(this);
		}
		this.rightChild = rightChild;
	}

}
