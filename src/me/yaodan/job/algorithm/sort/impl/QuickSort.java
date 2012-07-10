package me.yaodan.job.algorithm.sort.impl;

import me.yaodan.job.algorithm.sort.Sort;

/**
 * 快速排序类，实现快速排序算法。<br/>
 * 快速排序算法的实现过程：选取数组的最后一个元素做哨兵，将数组分为两个子数组，其中，前一个数组的元素都比哨兵小，后一个数组的元素都比哨兵大，
 * 然后再递归将两个子数组进行分组，最终实现快速排序。
 * 
 * @author yaodan.zhang
 * @see Sort
 * 
 */
public class QuickSort implements Sort {

	@Override
	public void sort(int[] array) {
		quickSort(0, array.length - 1, array);
	}

	@Override
	public String getSortName() {
		return "Quick Sort";
	}

	/**
	 * 将数组分为两个子数组，其中，前一个数组中的元素都比后一个数组小。
	 * 
	 * @param begin
	 *            数组的开始索引（包括）
	 * @param end
	 *            数组的结束索引（包括）
	 * @param array
	 *            待划分的数组
	 * @return 前后两个数组的中间索引
	 */
	private int partition(int begin, int end, int[] array) {
		int i = begin - 1;
		int key = array[end];
		for (int j = begin; j < end; j++) {
			if (array[j] <= key) {
				i++;
				int temp = array[j];
				array[j] = array[i];
				array[i] = temp;
			}
		}
		array[end] = array[i + 1];
		array[i + 1] = key;
		return i + 1;
	}

	/**
	 * 递归实现快排
	 * 
	 * @param begin
	 *            数组的开始索引（包括）
	 * @param end
	 *            数组的结束索引（包括）
	 * @param array
	 *            待排序的数组
	 */
	private void quickSort(int begin, int end, int[] array) {
		if (begin < end) {
			int middle = partition(begin, end, array);
			quickSort(begin, middle - 1, array);
			quickSort(middle + 1, end, array);
		}
	}

	@Override
	public String toString() {
		return getSortName();
	}
}
