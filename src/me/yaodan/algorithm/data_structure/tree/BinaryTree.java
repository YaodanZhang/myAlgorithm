package me.yaodan.algorithm.data_structure.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

import me.yaodan.algorithm.data_structure.tree.node.BinaryNode;


/**
 * 二叉树
 * 
 * @author yaodan.zhang
 * 
 */
public class BinaryTree {

	/**
	 * 二叉树的根节点，外界要得到需使用{@link #getRoot()}方法。
	 */
	protected BinaryNode root;

	public BinaryTree(BinaryNode root) {
		this.root = root;
	}

	/**
	 * @return 树的根节点
	 */
	public BinaryNode getRoot() {
		return root;
	}

	/**
	 * 先序遍历并打印树的节点（递归实现）。
	 * 
	 * @param node
	 *            要遍历的树的根节点
	 */
	public void preorderTraversal(BinaryNode node) {
		if (null != node) {
			System.out.print(node.getKey() + ", ");
			preorderTraversal(node.getLeftChild());
			preorderTraversal(node.getRightChild());
		}
	}

	/**
	 * 中序遍历并打印树的节点（递归实现）。
	 * 
	 * @param node
	 *            要遍历的树的根节点
	 */
	public void midOrderTraversal(BinaryNode node) {
		if (null != node) {
			midOrderTraversal(node.getLeftChild());
			System.out.print(node.getKey() + ", ");
			midOrderTraversal(node.getRightChild());
		}
	}

	/**
	 * 后序遍历并打印树的节点（递归实现）。
	 * 
	 * @param node
	 *            要遍历的树的根节点
	 */
	public void afterOrderTraversal(BinaryNode node) {
		if (null != node) {
			afterOrderTraversal(node.getLeftChild());
			afterOrderTraversal(node.getRightChild());
			System.out.print(node.getKey() + ", ");
		}
	}

	/**
	 * 层次遍历并打印树的节点（非递归实现）。
	 * 
	 * @param node
	 *            要遍历的树的根节点
	 */
	public void levelTraversal(BinaryNode node) {
		Queue<BinaryNode> queue = new ArrayDeque<BinaryNode>();
		while (null != node) {
			System.out.print(node.getKey() + ", ");
			addQueue(queue, node.getLeftChild());
			addQueue(queue, node.getRightChild());
			node = queue.poll();
		}
	}

	/**
	 * 先序遍历并打印树的节点（非递归实现）。
	 * 
	 * @param node
	 *            要遍历的树的根节点
	 */
	public void preorderTraversalNotRecursive(BinaryNode node) {
		_preorderNoRecMethod1(node);
		System.out.println();
		_preorderNoRecMethod2(node);
	}

	/**
	 * 先序遍历并打印树的节点（非递归实现）算法1。
	 * 
	 * @param node
	 *            要遍历的树的根节点
	 */
	private void _preorderNoRecMethod1(BinaryNode node) {
		Deque<BinaryNode> stack = new ArrayDeque<BinaryNode>();
		while (null != node) {
			System.out.print(node.getKey() + ", ");
			pushStack(stack, node.getRightChild());
			pushStack(stack, node.getLeftChild());
			node = popStack(stack);
		}
	}

	/**
	 * 先序遍历并打印树的节点（非递归实现）算法2。
	 * 
	 * @param node
	 *            要遍历的树的根节点
	 */
	private void _preorderNoRecMethod2(BinaryNode node) {
		Deque<BinaryNode> stack = new ArrayDeque<BinaryNode>();
		while (null != node || !stack.isEmpty()) {
			if (null == node) {
				node = stack.remove();
				node = node.getRightChild();
			} else {
				System.out.print(node.getKey() + ", ");
				stack.addFirst(node);
				node = node.getLeftChild();
			}
		}
	}

	/**
	 * 中序遍历并打印树的节点（非递归实现）。
	 * 
	 * @param node
	 *            要遍历的树的根节点
	 */
	public void midOrderTraversalNotRecursive(BinaryNode node) {
		_midOrderNoRecMethod1(node);
		System.out.println();
		_midOrderNoRecMethod2(node);
	}

	/**
	 * 中序遍历并打印树的节点（非递归实现）算法1。
	 * 
	 * @param node
	 *            要遍历的树的根节点
	 */
	private void _midOrderNoRecMethod1(BinaryNode node) {
		Deque<BinaryNode> stack = new ArrayDeque<BinaryNode>();
		pushStack(stack, node);
		boolean pushed = true;
		while (null != node) {
			if (pushed && node.hasLeftChild()) {
				pushStack(stack, node.getLeftChild());
			} else {
				node = popStack(stack);
				System.out.print(node.getKey() + ", ");
				if (node.hasRightChild()) {
					pushed = true;
					stack.addFirst(node.getRightChild());
				} else {
					pushed = false;
				}
			}
			node = getFirstFromStack(stack);
		}
	}

	/**
	 * 中序遍历并打印树的节点（非递归实现）算法2。
	 * 
	 * @param node
	 *            要遍历的树的根节点
	 */
	private void _midOrderNoRecMethod2(BinaryNode node) {
		Deque<BinaryNode> stack = new ArrayDeque<BinaryNode>();
		while (null != node || !stack.isEmpty()) {
			if (null == node) {
				node = stack.remove();
				System.out.print(node.getKey() + ", ");
				node = node.getRightChild();
			} else {
				stack.addFirst(node);
				node = node.getLeftChild();
			}
		}
	}

	/**
	 * 后序遍历并打印树的节点（非递归实现）。
	 * 
	 * @param node
	 *            要遍历的树的根节点
	 */
	public void afterOrderTraversalNotRecursive(BinaryNode node) {
		_afterOrderNoRecMethod1(node);
	}

	/**
	 * 后序遍历并打印树的节点（非递归实现）算法1。
	 * 
	 * @param node
	 *            要遍历的树的根节点
	 */
	private void _afterOrderNoRecMethod1(BinaryNode node) {
		Deque<BinaryNode> stack = new ArrayDeque<BinaryNode>();
		Deque<BinaryNode> visited = new ArrayDeque<BinaryNode>();
		while (null != node || !stack.isEmpty()) {
			if (null == node) {
				node = stack.getFirst();
				if (getFirstFromStack(visited) == node) {
					stack.remove();
					System.out.print(node.getKey() + ", ");
					popStack(visited);
					node = null;
				} else {
					pushStack(visited, node);
					node = node.getRightChild();
				}
			} else {
				stack.addFirst(node);
				node = node.getLeftChild();
			}
		}
	}

	/**
	 * 将<tt>node</tt>添加到队列<tt>queue</tt>中，如果<tt>node</tt>为<tt>null</tt>，则不添加。
	 * 
	 * @param queue
	 *            队列
	 * @param node
	 *            待加入队列的元素
	 */
	private static void addQueue(Queue<BinaryNode> queue, BinaryNode node) {
		if (null != node) {
			queue.add(node);
		}
	}

	/**
	 * 将<tt>node</tt>添加到栈<tt>stack</tt>中，如果<tt>node</tt>为<tt>null</tt>，则不添加。
	 * 
	 * @param stack
	 *            栈
	 * @param node
	 *            待加入队列的元素
	 */
	private static void pushStack(Deque<BinaryNode> stack, BinaryNode node) {
		if (null != node) {
			stack.addFirst(node);
		}
	}

	/**
	 * 从栈<tt>stack</tt>中<tt>pop</tt>出一个元素，如果<tt>stack</tt>为空，则返回<tt>null</tt>。
	 * 
	 * @param stack
	 *            栈
	 */
	private static BinaryNode popStack(Deque<BinaryNode> stack) {
		if (stack.isEmpty()) {
			return null;
		} else {
			return stack.remove();
		}
	}

	/**
	 * 获取栈<tt>stack</tt>中的第一个元素，如果<tt>stack</tt>为空，则返回<tt>null</tt>。
	 * 
	 * @param stack
	 *            栈
	 */
	private static BinaryNode getFirstFromStack(Deque<BinaryNode> stack) {
		if (stack.isEmpty()) {
			return null;
		} else {
			return stack.getFirst();
		}
	}
}
