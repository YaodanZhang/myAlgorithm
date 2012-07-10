package me.yaodan.job.algorithm.data_structure.graph;

/**
 * 深度优先搜索树中每条边的分类。
 * 
 * @author yaodan.zhang
 * 
 */
public enum EdgeType {

	/**
	 * 树边：连接顶点到他的一个直接后裔的边
	 */
	TREE_EDGE,

	/**
	 * 反向边：连接顶点到他的某一个祖先的边，特殊的，有向图中的自环也是反向边
	 */
	BACK_EDGE,

	/**
	 * 前向边：连接顶点到他的某个后裔（不是直接后裔）的边
	 */
	FORWARD_EDGE,

	/**
	 * 交叉边：1、连接同一棵树两个顶点之间的边，这两个顶点之间没有“祖先-孩子”的关系；2、连接不同树顶点之间的边
	 */
	CROSS_EDGE;
}
