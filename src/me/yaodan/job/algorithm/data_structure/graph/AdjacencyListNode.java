package me.yaodan.job.algorithm.data_structure.graph;

public class AdjacencyListNode {

	/**
	 * 该边对应权重
	 */
	private int weight;

	/**
	 * 该顶点对应下标
	 */
	private int nodeNumber;

	/**
	 * 邻接表中下一个节点
	 */
	private AdjacencyListNode next;

	/**
	 * 创建一个邻接表节点。
	 * 
	 * @param nodeNumber
	 *            该顶点对应下标
	 * @param weight
	 *            该边对应全值
	 * @param next
	 *            邻接表中的下一个节点
	 */
	public AdjacencyListNode(int nodeNumber, int weight, AdjacencyListNode next) {
		this.nodeNumber = nodeNumber;
		this.weight = weight;
		this.next = next;
	}

	/**
	 * 创建一个邻接表节点。
	 * 
	 * @param nodeNumber
	 *            该顶点对应下标
	 */
	public AdjacencyListNode(int nodeNumber) {
		this(nodeNumber, 1, null);
	}

	/**
	 * 创建一个邻接表节点。
	 * 
	 * @param nodeNumber
	 *            该顶点对应下标
	 * @param next
	 *            邻接表中的下一个节点
	 */
	public AdjacencyListNode(int nodeNumber, AdjacencyListNode next) {
		this(nodeNumber, 1, next);
	}

	/**
	 * 创建一个邻接表节点。
	 * 
	 * @param nodeNumber
	 *            该顶点对应下标
	 * @param weight
	 *            该边对应全值
	 */
	public AdjacencyListNode(int nodeNumber, int weight) {
		this(nodeNumber, weight, null);
	}

	// getters & setters
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getNodeNumber() {
		return nodeNumber;
	}

	public void setNodeNumber(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}

	public AdjacencyListNode getNext() {
		return next;
	}

	public void setNext(AdjacencyListNode next) {
		this.next = next;
	}
	
	
}
