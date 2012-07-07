package job.algorithm.data_structure.tree;

import job.algorithm.data_structure.tree.node.BinaryNode;

/**
 * 二叉查找树，这种树的中序遍历输出是按字典顺序的。
 * 
 * @author yaodan.zhang
 * 
 */
public class SearchTree extends BinaryTree {

	public SearchTree(BinaryNode root) {
		super(root);
	}

	/**
	 * 查找关键字为<tt>key</tt>的节点。
	 * 
	 * @param key
	 * @return 如果找到，返回该节点，否则返回<tt>null</tt>。
	 */
	public BinaryNode search(int key) {
		BinaryNode node = root;
		while (null != node) {
			if (key == node.getKey()) {
				break;
			} else if (key < node.getKey()) {
				node = node.getLeftChild();
			} else {
				node = node.getRightChild();
			}
		}
		return node;
	}

	/**
	 * 查找后继元素，即中序遍历中，元素<tt>node</tt>的下一个元素。
	 * 
	 * @param node
	 *            待查找后继的元素
	 * @return <tt>node</tt>的后继，如果<tt>node</tt>为最后一个元素，则返回<tt>null</tt>
	 */
	public BinaryNode getSuccessor(BinaryNode node) {
		if (null != node.getRightChild()) {
			// 如果右子树不为空，怎后继为右子树的最小节点
			return getMin(node.getRightChild());
		} else {
			// 如果右子树为空
			if (node == getMax(root)) {
				return null;
			} else {
				BinaryNode parent = node.getParent();
				while (parent != null && parent.getRightChild() == node) {
					node = parent;
					parent = node.getParent();
				}
				return parent;
			}
		}
	}

	/**
	 * 查找后继元素，即中序遍历中，元素<tt>node</tt>的下一个元素。
	 * 
	 * @param key
	 *            待查找后继的元素的<tt>key</tt>
	 * @return <tt>node</tt>的后继，如果<tt>node</tt>为最后一个元素或没有关键词为<tt>key</tt>的元素，则返回
	 *         <tt>null</tt>
	 */
	public BinaryNode getSuccessor(int key) {
		BinaryNode node = search(key);
		if (null == node) {
			return null;
		} else {
			return getSuccessor(node);
		}
	}

	/**
	 * 查找前趋元素，即中序遍历中，元素<tt>node</tt>的上一个元素。
	 * 
	 * @param node
	 *            待查找前趋的元素
	 * @return <tt>node<tt>的前趋，如果<tt>node</tt>为第一个元素，则返回<tt>null</tt>
	 */
	public BinaryNode getPredecessor(BinaryNode node) {
		if (null != node.getLeftChild()) {
			// 如果左子树不为空，则前趋为左子树的最大节点
			return getMax(node.getLeftChild());
		} else {
			// 如果左子树为空
			if (node == getMin(root)) {
				return null;
			} else {
				BinaryNode parent = node.getParent();
				while (null != parent && node == parent.getLeftChild()) {
					node = parent;
					parent = node.getParent();
				}
				return parent;
			}
		}
	}

	/**
	 * 查找前趋元素，即中序遍历中，元素<tt>node</tt>的上一个元素。
	 * 
	 * @param key
	 *            待查找前趋的元素的<tt>key</tt>
	 * @return <tt>node</tt>的前趋，如果<tt>node</tt>为第一个元素或没有关键词为<tt>key</tt>的元素，则返回
	 *         <tt>null</tt>
	 */
	public BinaryNode getPredecessor(int key) {
		BinaryNode node = search(key);
		if (null == node) {
			return null;
		} else {
			return getPredecessor(node);
		}
	}

	/**
	 * 获得关键字最小的元素，如果树为空，返回<tt>null</tt>。
	 * 
	 * @param node
	 *            要查找最小元素的子树的根节点
	 * @return
	 */
	public BinaryNode getMin(BinaryNode node) {
		if (null != node) {
			while (null != node.getLeftChild()) {
				node = node.getLeftChild();
			}
		}
		return node;
	}

	/**
	 * 获得关键字最大的元素，如果树为空，返回<tt>null</tt>。
	 * 
	 * @param node
	 *            要查找最大元素的子树的根节点
	 * 
	 * @return
	 */
	public BinaryNode getMax(BinaryNode node) {
		if (null != node) {
			while (null != node.getRightChild()) {
				node = node.getRightChild();
			}
		}
		return node;
	}

	/**
	 * 向二叉查找树中插入一个元素。
	 * 
	 * @param node
	 *            待插入的元素
	 */
	public void insert(BinaryNode newNode) {
		if (null == newNode) {
			root = newNode;
		} else {
			BinaryNode node = root;
			BinaryNode childNode = root;
			while (childNode != null) {
				node = childNode;
				if (newNode.getKey() <= node.getKey()) {
					childNode = node.getLeftChild();
				} else {
					childNode = node.getRightChild();
				}
			}
			if (newNode.getKey() <= node.getKey()) {
				node.setLeftChild(newNode);
			} else {
				node.setRightChild(newNode);
			}
			newNode.setParent(node);
		}
	}

	/**
	 * 向二叉查找树中插入一个关键词为<tt>key</tt>的空元素，需要调用{@link #insert(BinaryNode)}实现。
	 * 
	 * @param key
	 *            待插入的元素的关键词
	 */
	public void insert(int key) {
		BinaryNode node = new BinaryNode();
		node.setKey(key);
		insert(node);
	}

	/**
	 * 将关键词为<tt>key</tt>的元素从树中删掉。
	 * 
	 * @param key
	 *            待删除元素的关键词
	 */
	public void delete(int key) {
		BinaryNode keyNode = search(key);
		if (null != keyNode) {
			BinaryNode nodeToDel, childNode;

			// 确定要删除的节点，该节点要么是关键词为key的节点（他最多有一个孩子），要么是关键词为key的节点的后继（他有两个孩子）
			if (null == keyNode.getLeftChild()
					|| null == keyNode.getRightChild()) {
				nodeToDel = keyNode;
			} else {
				nodeToDel = getSuccessor(keyNode);
			}

			if (null != nodeToDel.getLeftChild()) {
				childNode = nodeToDel.getLeftChild();
			} else {
				childNode = nodeToDel.getRightChild();
			}

			if (null != childNode) {
				childNode.setParent(nodeToDel.getParent());
			}

			if (null == nodeToDel.getParent()) {
				root = childNode;
			} else if (nodeToDel == nodeToDel.getParent().getLeftChild()) {
				nodeToDel.getParent().setLeftChild(childNode);
			} else {
				nodeToDel.getParent().setRightChild(childNode);
			}

			if (keyNode != nodeToDel) {
				nodeToDel.copyTo(keyNode);
			}

			nodeToDel = null;
		} else {
			System.out.println("删除终止，没有找到key为" + key + "的元素。");
		}
	}
}
