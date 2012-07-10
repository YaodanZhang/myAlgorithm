package me.yaodan.job.algorithm.data_structure.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import me.yaodan.job.algorithm.data_structure.DisjointSet;


/**
 * 图的邻接表表示方法。
 * 
 * @author yaodan.zhang
 * 
 */
public class AdjacencyList {

	/**
	 * 是否是有向图
	 */
	private boolean directed;
	private AdjacencyListNode[] nodeList;
	private int nodeCount;
	/**
	 * 图中的所有边，对于无向图，同一条边只存一次。
	 */
	private Set<Edge> allEdges;

	// --------------------------------------这部分域用于深度优先搜索算法--------------------------------------//
	private boolean haveDFSed;
	/**
	 * 计算访问时间
	 */
	private int timeDFS;
	/**
	 * 保存每条边的类型
	 */
	private Map<Edge, EdgeType> edgeTypeMapDFS;
	/**
	 * 第一次到达时间
	 */
	private int[] visitTimeDFS;
	/**
	 * 访问完成时间
	 */
	private int[] finishTimeDFS;
	/**
	 * 深度优先搜索树中的父节点
	 */
	private int[] parentsDFS;
	// --------------------------------------这部分域用于深度优先搜索算法--------------------------------------//

	private static int DEFAULT_WEIGHT = 1;

	/**
	 * 创建一个图的邻接表。
	 * 
	 * @param nodeCount
	 *            图中顶点的数量。
	 * @param directed
	 *            是否是有向图，true为有向图，否则不是。
	 */
	public AdjacencyList(int nodeCount, boolean directed) {
		this.nodeCount = nodeCount;
		this.directed = directed;
		this.nodeList = new AdjacencyListNode[nodeCount];
		allEdges = new HashSet<Edge>();
		haveDFSed = false;
	}

	/**
	 * 向图中增加一条边，这条边的权值为默认值。
	 * 
	 * @param begin
	 *            边的起点
	 * @param end
	 *            边的终点
	 */
	public AdjacencyList addEdge(int begin, int end) {
		return addEdge(begin, end, DEFAULT_WEIGHT);
	}

	/**
	 * 向图中增加一条边。
	 * 
	 * @param begin
	 *            边的起点
	 * @param end
	 *            边的终点
	 * @param weight
	 *            边的权值
	 */
	public AdjacencyList addEdge(int begin, int end, int weight) {
		Edge edge = new Edge(begin, end, weight, directed);
		boolean contained = allEdges.add(edge);
		if (contained) {
			// 表明还未添加过这条边，要加上
			_addEdgeToNodeList(begin, end, weight);
			if (!directed) {
				// 无向图要多加一条边
				_addEdgeToNodeList(end, begin, weight);
			}
		}
		return this;
	}

	/**
	 * 向{@linkplain #nodeList}中添加一条边
	 * 
	 * @param begin
	 *            起点
	 * @param end
	 *            终点
	 * @param weight
	 *            权值
	 */
	private void _addEdgeToNodeList(int begin, int end, int weight) {
		// 图发生改变，上次做的DFS不再继续有效
		haveDFSed = false;

		AdjacencyListNode endNode = new AdjacencyListNode(end, weight);
		AdjacencyListNode beginNode = nodeList[begin];
		if (null != beginNode) {
			while (null != beginNode.getNext()) {
				beginNode = beginNode.getNext();
			}
			beginNode.setNext(endNode);
		} else {
			nodeList[begin] = endNode;
		}
	}

	/**
	 * 返回该图转置图。
	 */
	public AdjacencyList getTranspose() {
		AdjacencyList transpose = new AdjacencyList(nodeCount, directed);
		for (Edge edge : allEdges) {
			transpose.addEdge(edge.getEnd(), edge.getBegin(), edge.getWeight());
		}
		return transpose;
	}

	public List<Edge> sortEdgeByWeight() {
		List<Edge> edges = new ArrayList<Edge>(allEdges);
		Collections.sort(edges);
		return edges;
	}

	/**
	 * 返回图中总共的顶点数。
	 */
	public int getSize() {
		return nodeCount;
	}

	/**
	 * 是否是有向图，如果是，返回true，否则返回false。
	 */
	public boolean isDirected() {
		return directed;
	}

	/**
	 * 返回图中所有边的一个{@linkplain List}类型的副本。
	 */
	public Set<Edge> getAllEdges() {
		return new HashSet<Edge>(allEdges);
	}

	// //////////////////////////////////////////////////////////
	// -------------以上是创建图等图的基本方法----------------------//
	// --------------------------------------------------------//
	// -------------以下是图的相关算法的实现方法--------------------//
	// //////////////////////////////////////////////////////////

	/**
	 * 图的单源广度优先搜索算法，即从指定顶点出发，按广度优先，搜索所有顶点。
	 * 
	 * @param source
	 *            源顶点
	 * @return 所有顶点到源顶点的距离（int型数组）。如果无法达到，则返回{@linkplain Integer#MAX_VALUE}
	 *         ，用来代表正无穷。
	 */
	public int[] breadthFirstSearch(int source) {
		// 每个顶点到源顶点的距离
		int[] distances = new int[nodeCount];
		// 每个节点在广度优先搜索树中的父节点
		int[] parents = new int[nodeCount];
		// 每个顶点的颜色
		GraphNodeColor[] colors = new GraphNodeColor[nodeCount];
		// 遍历过程中保存顶点的队列
		Queue<Integer> vertexQueue = new ArrayDeque<Integer>();

		// 首先将每个顶点设置为白色, 将每个顶点的距离设置为正无穷，将每个节点的父节点设置为-1
		for (int i = 0; i < nodeCount; i++) {
			colors[i] = GraphNodeColor.WHITE;
			distances[i] = Integer.MAX_VALUE;
			parents[i] = -1;
		}

		colors[source] = GraphNodeColor.GRAY;
		distances[source] = 0;
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			int vertex = vertexQueue.poll();
			colors[vertex] = GraphNodeColor.BLACK;
			AdjacencyListNode child = nodeList[vertex];
			while (null != child) {
				int childNumber = child.getNodeNumber();
				if (GraphNodeColor.WHITE == colors[childNumber]) {
					colors[childNumber] = GraphNodeColor.GRAY;
					parents[childNumber] = vertex;
					distances[childNumber] = distances[vertex] + 1;
					vertexQueue.add(childNumber);
				}
				child = child.getNext();
			}
		}

		return distances;
	}

	/**
	 * 图的深度优先搜索算法
	 */
	public void depthFirstSearch() {
		GraphNodeColor[] colors = new GraphNodeColor[nodeCount];

		parentsDFS = new int[nodeCount];
		visitTimeDFS = new int[nodeCount];
		finishTimeDFS = new int[nodeCount];
		haveDFSed = true;
		timeDFS = 1;
		edgeTypeMapDFS = new HashMap<Edge, EdgeType>();

		// 初始化颜色、父节点、发现时间、完成时间
		for (int i = 0; i < nodeCount; i++) {
			colors[i] = GraphNodeColor.WHITE;
			parentsDFS[i] = -1;
		}

		for (int i = 0; i < nodeCount; i++) {
			if (GraphNodeColor.WHITE == colors[i]) {
				DFSVisit(i, colors);
			}
		}
	}

	/**
	 * 返回图中的每条边在深度优先搜索树中的类型（{@linkplain Map}类型的副本）
	 * 
	 * @throws IllegalStateException
	 *             在执行深度优先算法{@linkplain #depthFirstSearch()}之前调用此方法时抛出
	 */
	public Map<Edge, EdgeType> getEdgeTypeMapDFS() {
		if (!haveDFSed) {
			throw new IllegalStateException("Should DFS first!");
		}
		return new HashMap<Edge, EdgeType>(edgeTypeMapDFS);
	}

	/**
	 * 返回深度优先搜索算法中每个顶点的到达时间（int数组类型的副本）
	 * 
	 * @throws IllegalStateException
	 *             在执行深度优先算法{@linkplain #depthFirstSearch()}之前调用此方法时抛出
	 */
	public int[] getVisitTimeDFS() {
		if (!haveDFSed) {
			throw new IllegalStateException("Should DFS first!");
		}
		return Arrays.copyOf(visitTimeDFS, visitTimeDFS.length);
	}

	/**
	 * 返回深度优先搜索算法中每个顶点的完成时间（int数组类型的副本）
	 * 
	 * @throws IllegalStateException
	 *             在执行深度优先算法{@linkplain #depthFirstSearch()}之前调用此方法时抛出
	 */
	public int[] getFinishTimeDFS() {
		if (!haveDFSed) {
			throw new IllegalStateException("Should DFS first!");
		}
		return Arrays.copyOf(finishTimeDFS, finishTimeDFS.length);
	}

	/**
	 * 返回深度优先搜索树中每个顶点的父节点（int数组类型的副本）
	 * 
	 * @throws IllegalStateException
	 *             在执行深度优先算法{@linkplain #depthFirstSearch()}之前调用此方法时抛出
	 */
	public int[] getParentsDFS() {
		if (!haveDFSed) {
			throw new IllegalStateException("Should DFS first!");
		}
		return Arrays.copyOf(parentsDFS, parentsDFS.length);
	}

	/**
	 * 图的单源深度优先搜索方法
	 */
	private void DFSVisit(int source, GraphNodeColor[] colors) {
		colors[source] = GraphNodeColor.GRAY;
		visitTimeDFS[source] = timeDFS++;

		AdjacencyListNode next = nodeList[source];
		while (null != next) {
			Edge edge = new Edge(source, next.getNodeNumber(),
					next.getWeight(), directed);
			edgeTypeMapDFS.put(edge,
					getEdgeType(edge, colors[next.getNodeNumber()]));
			if (GraphNodeColor.WHITE == colors[next.getNodeNumber()]) {
				parentsDFS[next.getNodeNumber()] = source;
				DFSVisit(next.getNodeNumber(), colors);
			}
			next = next.getNext();
		}

		colors[source] = GraphNodeColor.BLACK;
		finishTimeDFS[source] = timeDFS++;
	}

	/**
	 * 根据到达顶点的颜色和访问时间可以确定边的类型：<br/>
	 * <br/>
	 * 1、白色：树边；<br/>
	 * 2、灰色：反向边；<br/>
	 * 3、黑色：正向边或者交叉边。如果起点的到达时间小于终点，则是正向边，如果起点的到达时间大于终点，则是交叉边。
	 */
	private EdgeType getEdgeType(Edge edge, GraphNodeColor color) {
		switch (color) {
		case WHITE:
			return EdgeType.TREE_EDGE;
		case GRAY:
			return EdgeType.BACK_EDGE;
		default:
			if (visitTimeDFS[edge.getBegin()] < visitTimeDFS[edge.getEnd()]) {
				return EdgeType.FORWARD_EDGE;
			} else {
				return EdgeType.CROSS_EDGE;
			}
		}
	}

	/**
	 * 对一个有向无回路图进行拓扑排序。
	 * 
	 * @return 排序后的顶点顺序数组
	 */
	public int[] topoLogicalSort() {
		if (!haveDFSed) {
			this.depthFirstSearch();
		}
		return sortNodeByFinishTime();
	}

	/**
	 * 根据完成时间从大到小的顺序，使用计数排序，对顶点进行排序，即完成时间最大的顶点排在最前面。
	 * 
	 * @return 排序后的顶点顺序数组
	 */
	private int[] sortNodeByFinishTime() {
		int[] sortedNodes = new int[nodeCount];
		int[] counting = new int[2 * nodeCount + 1];

		for (int f : finishTimeDFS) {
			counting[f]++;
		}

		for (int i = 0, length = counting.length; i < length - 1; i++) {
			counting[i + 1] += counting[i];
		}

		for (int i = nodeCount - 1; i >= 0; i--) {
			sortedNodes[counting[finishTimeDFS[i]] - 1] = i;
			counting[finishTimeDFS[i]]--;
		}

		return reverseArray(sortedNodes);
	}

	/**
	 * 一个无向连通图的Kruskal最小生成树算法。<br/>
	 * <br/>
	 * 无向连通图：图的边的一个无回路的子集，它连通了所有的顶点，且其权值之和为最小。
	 * 
	 * @return 最小生成树的边。
	 */
	public List<Edge> kruskalMST() {
		List<Edge> edges = new ArrayList<Edge>();
		DisjointSet<Integer> vertexDS = new DisjointSet<Integer>(this.nodeCount);
		List<Edge> sortedEdges = sortEdgeByWeight();

		// 初始化图中每个顶点
		for (int i = 0; i < this.nodeCount; i++) {
			vertexDS.makeSet(i);
		}

		for (Edge e : sortedEdges) {
			if (vertexDS.findSet(e.getBegin()) != vertexDS.findSet(e.getEnd())) {
				edges.add(e);
				vertexDS.union(e.getBegin(), e.getEnd());
			}
		}
		return edges;
	}

	/**
	 * 将数组反转
	 * 
	 * @param array
	 *            需要注意，调用后会发生改变
	 * @return 反转后的数组
	 */
	private static int[] reverseArray(int[] array) {
		for (int i = 0, j = array.length - 1; i < j; i++, j--) {
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		return array;
	}
}
