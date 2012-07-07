package job.algorithm.data_structure;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 查并集类，用于不相交集合的数据结构。<br/>
 * <br/>
 * 保持一组不相交的动态集合 <tt>S={S1, S2, ... , Sn}</tt>，每个集合通过一个代表来识别，特别的，集合中的元素不能为null。<br/>
 * <br/>
 * 查并集可以用链表实现，也可以用树实现，本算法使用数组模拟一个逻辑意义上的树来实现。
 * 
 * @author yaodan.zhang
 * 
 */
public class DisjointSet<T> {

	/**
	 * 集合中的元素
	 */
	private Object[] elements;

	/**
	 * 集合中元素所在集合的代表元素在{@linkplain #elements}中的索引。<br/>
	 * <br/>
	 * 比如：<tt>fathers[4] = 8</tt>的意思是<tt>elements[4]所在集合的代表元素是elements[8]</tt>。
	 */
	private int[] parents;

	/**
	 * 集合中元素的秩，元素x的秩指的是以x为根的树的高度（从x到其某一个后代叶子节点的最长路径上边的数目）的一个上界。
	 */
	private int[] ranks;

	/**
	 * 几个中实际元素的个数，注意，不等于数组{@linkplain #elements}的长度。
	 */
	private int size;

	/**
	 * 使用指定的初始容量构造一个查并集。<br/>
	 * <br/>
	 * 注意，容量不是包含的元素的个数。
	 * 
	 * @param initialCapacity
	 *            查并集的初始容量
	 * @exception IllegalArgumentException
	 *                如果初始容量小于0
	 */
	public DisjointSet(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal Capacity: "
					+ initialCapacity);
		}
		this.elements = new Object[initialCapacity];
		this.parents = new int[initialCapacity];
		this.ranks = new int[initialCapacity];
		size = 0;
	}

	/**
	 * 构造一个初始容量为10的查并集。<br/>
	 * <br/>
	 * 注意，容量不是包含的元素的个数。
	 */
	public DisjointSet() {
		this(10);
	}

	/**
	 * 创建一个新的集合，该集合有且仅有一个元素<tt>t</tt>。
	 * 
	 * @param t
	 *            要求元素t没有在集合中出现过。
	 * @return 返回该集合的代表元素，如果t没有在集合中出现过，则返回t，否则返回集合中元素t的代表元素。
	 */
	@SuppressWarnings("unchecked")
	public T makeSet(T t) {
		int index = _indexOfWithoutException(t);
		if (-1 == index) {
			ensureCapacity(size + 1);
			elements[size] = t;
			parents[size] = size;
			ranks[size] = 0;
			size++;
			return t;
		} else {
			return (T) elements[findSet(index)];
		}
	}

	/**
	 * 查找元素t所在集合的代表元素。
	 * 
	 * @param t
	 *            待查找的元素
	 * 
	 * @throws NoSuchElementException
	 *             元素t不在集合中
	 */
	@SuppressWarnings("unchecked")
	public T findSet(T t) {
		int index = indexOf(t);
		return (T) elements[findSet(index)];
	}

	/**
	 * 将包含t1和t2两个元素的两个集合合并成一个新的集合，并返回这个集合的代表元素。
	 * 
	 * @param t1
	 * @param t2
	 * @return 合并后的集合的代表元素。
	 * 
	 * @throws NoSuchElementException
	 *             元素t1或t2不在集合中
	 */
	@SuppressWarnings("unchecked")
	public T union(T t1, T... ts) {
		int index1 = indexOf(t1);
		int parentIndex = -1;
		for (T t : ts) {
			parentIndex = unionForParents(findSet(indexOf(t)), findSet(index1));
		}
		return (T) elements[parentIndex];
	}

	/**
	 * 返回集合中总的元素个数。
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 将代表为parentIndex1和parentIndex2两个元素的两个集合合并成一个新的集合，并返回这个集合的代表元素。
	 * 
	 * @param parentIndex1
	 * @param parentIndex2
	 * @return 合并后集合的代表元素的索引。
	 */
	private int unionForParents(int parentIndex1, int parentIndex2) {
		if (parentIndex1 == parentIndex2) {
			return parentIndex1;
		} else {
			int parent = parentIndex1;
			if (ranks[parentIndex1] > ranks[parentIndex2]) {
				parents[parentIndex2] = parentIndex1;
			} else {
				parents[parentIndex1] = parentIndex2;
				parent = parentIndex2;
				if (ranks[parentIndex1] == ranks[parentIndex2]) {
					ranks[parentIndex2]++;
				}
			}
			return parent;
		}
	}

	/**
	 * 查找元素t所在集合的代表元素的索引。
	 * 
	 * @param index
	 *            待查找的元素的索引
	 * @return 元素t所在集合的代表元素的索引。
	 */
	private int findSet(int index) {
		if (index != parents[index]) {
			parents[index] = findSet(parents[index]);
		}
		return parents[index];
	}

	/**
	 * 返回元素t出现的位置。
	 * 
	 * @throws NoSuchElementException
	 *             元素t不在集合中
	 */
	private int indexOf(T t) {
		int index = _indexOfWithoutException(t);
		if (-1 == index) {
			throw new NoSuchElementException("There is no such element: " + t);
		} else {
			return index;
		}
	}

	/**
	 * 返回元素t出现的位置，如果元素不存在，返回-1。
	 * 
	 */
	private int _indexOfWithoutException(T t) {
		for (int i = 0; i < size; i++) {
			if (t.equals(elements[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 如果需要，增加查并集实例的容量，以保证能包含最小容量参数制定值数量的集合元素。
	 * 
	 * @param minCapacity
	 *            查并集需要的最小容量
	 */
	private void ensureCapacity(int minCapacity) {
		int oldCapacity = elements.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}
			elements = Arrays.copyOf(elements, newCapacity);
			parents = Arrays.copyOf(parents, newCapacity);
			ranks = Arrays.copyOf(ranks, newCapacity);
		}
	}
}
