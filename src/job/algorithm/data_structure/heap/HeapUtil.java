package job.algorithm.data_structure.heap;

/**
 * 堆的相关算法中用到的通用方法
 * 
 * @author yaodan.zhang
 * 
 */
public class HeapUtil {

	/**
	 * 判断序号为<tt>index</tt>的节点是否有左孩子。
	 * 
	 * @param index
	 *            待判断的节点。
	 * @param heapLength
	 *            当前堆中元素的个数。
	 * @return 如果有，返回<tt>true</tt>，否则返回<tt>false</tt>。
	 */
	public static boolean hasLeftChild(int index, int heapLength) {
		return index > -1 && (2 * index + 1) < heapLength;
	}

	/**
	 * 判断序号为<tt>index</tt>的节点是否有右孩子。
	 * 
	 * @param index
	 *            待判断的节点。
	 * @param heapLength
	 *            当前堆中元素的个数。
	 * @return 如果有，返回<tt>true</tt>，否则返回<tt>false</tt>。
	 */
	public static boolean hasRightChild(int index, int heapLength) {
		return index > -1 && (2 * (index + 1)) < heapLength;
	}

	/**
	 * 判断序号为<tt>index</tt>的节点是否有父节点。
	 * 
	 * @param index
	 *            待判断的节点。
	 * @param heapLength
	 *            当前堆中元素的个数。
	 * @return 如果有，返回<tt>true</tt>，否则返回<tt>false</tt>。
	 */
	public static boolean hasParent(int index, int heapLength) {
		return index > 0 && index < heapLength;
	}

	/**
	 * 获取左孩子节点的索引。<br/>
	 * <br/>
	 * 注意：本方法不保证获得的索引是否越界，需要用户自行调用{@link #hasLeftChild(int, int)}进行判断。
	 * 
	 * @param index
	 *            当前节点的索引
	 * @return 左孩子的索引
	 */
	public static int getLetfChildIndex(int index) {
		return 2 * index + 1;
	}

	/**
	 * 获取右孩子节点的索引。<br/>
	 * <br/>
	 * 注意：本方法不保证获得的索引是否越界，需要用户自行调用{@link #hasRightChild(int, int)}进行判断。
	 * 
	 * @param index
	 *            当前节点的索引
	 * @return 右孩子的索引
	 */
	public static int getRightChildIndex(int index) {
		return 2 * (index + 1);
	}

	/**
	 * 获取父亲节点的索引。<br/>
	 * <br/>
	 * 注意：本方法不保证获得的索引是否越界，需要用户自行调用{@link #hasParent(int, int)}进行判断。
	 * 
	 * @param index
	 *            当前节点的索引
	 * @return 父亲的索引
	 */
	public static int getParentIndex(int index) {
		return (index - 1) / 2;
	}
}
