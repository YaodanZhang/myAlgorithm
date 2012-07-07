package job.algorithm.sort.impl;

import job.algorithm.data_structure.heap.ArrayIntHeap;
import job.algorithm.sort.Sort;

/**
 * 堆排序类，实现堆排序算法。<br/>
 * 堆排序过程：对整个数组建一个最大堆，然后开始排序：<br/>
 * 将堆顶元素与堆中的最后一个叶子节点交换，然后对前<tt>(n-1)<tt>个元素重新建堆，直到堆中元素只剩一个，完成排序。
 * 
 * @author yaodan.zhang
 * @see Sort
 *
 */
public class HeapSort implements Sort {

	@Override
	public void sort(int[] array) {
		ArrayIntHeap heap = new ArrayIntHeap(true, array);
		heap.sort();
	}

	@Override
	public String getSortName() {
		return "Heap Sort";
	}

	@Override
	public String toString() {
		return getSortName();
	}

}
