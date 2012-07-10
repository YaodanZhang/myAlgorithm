package me.yaodan.job.algorithm.data_structure.graph;

/**
 * 图的边，包括起点、终点和边的权值等信息。
 * 
 * @author yaodan.zhang
 * 
 */
public final class Edge implements Comparable<Edge> {

	private int begin;
	private int end;
	private int weight;
	private boolean directed;

	// 用于缓存hash
	private int hash;

	/**
	 * 根据边的起点、终点、权值、是否是有向边，构造图的一条边的对象。
	 * 
	 * @param directed
	 *            ture为有向边，否则为无向边
	 */
	public Edge(int begin, int end, int weight, boolean directed) {
		this.begin = begin;
		this.end = end;
		this.weight = weight;
		this.directed = directed;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o instanceof Edge) {
			Edge edge = (Edge) o;
			if (directed) {
				return this.begin == edge.begin && this.end == edge.end
						&& this.weight == edge.weight
						&& this.directed == edge.directed;
			} else {
				return this.weight == edge.weight
						&& this.directed == edge.directed
						&& ((this.begin == edge.begin && this.end == edge.end) || (this.begin == edge.end && this.end == edge.begin));
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int h = hash;
		if (h == 0) {
			h = (directed ? 1 : 0);
			if(directed) {
				h = 31 * h + this.begin;
				h = 31 * h + this.end;
			} else {
				if(this.begin < this.end) {
					h = 31 * h + this.begin;
					h = 31 * h + this.end;
				} else {
					h = 31 * h + this.end;
					h = 31 * h + this.begin;
				}
			}
			h = 31 * h + this.weight;
			hash = h;
		}
		return h;
	}

	@Override
	public int compareTo(Edge o) {
		return this.weight - o.weight;
	}

	// getters
	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}

	public int getWeight() {
		return weight;
	}

	public boolean isDirected() {
		return directed;
	}

}
