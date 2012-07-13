package me.yaodan.algorithm.sort.impl;

import me.yaodan.algorithm.sort.Sort;

/**
 * 归并排序类，实现归并排序算法。<br/>
 * 归并排序的实现过程：运用递归，将数组分割成左右两个子数组，直到每个子数组只剩一个元素，然后两两按序合并。
 * 
 * @author yaodan.zhang
 * @see Sort
 * 
 */
public class MergeSort implements Sort {

	@Override
	public void sort(int[] array) {
		mergeSort(0, array.length - 1, array);
	}

	@Override
	public String getSortName() {
		return "Merge Sort";
	}

	/**
	 * 执行归并排序的递归方法，执行递归分割成子数组来排序，分割完后调用{@link #merge(int, int, int, int[])}执行合并
	 * 
	 * @param begin
	 *            数组的开始索引（包括）
	 * @param end
	 *            数组的结束索引（包括）
	 * @param array
	 *            要排序的数组
	 */
	private void mergeSort(int begin, int end, int[] array) {
		if (begin < end) {
			int middle = (begin + end) / 2;
			mergeSort(begin, middle, array);
			mergeSort(middle + 1, end, array);
			merge(begin, middle, end, array);
		}

	}

	/**
	 * 归并排序的合并方法，将排序好的两个子数组合并成一个排序好的数组。
	 * 
	 * @param leftBegin
	 *            前一个子数组的开始索引（包括）
	 * @param leftEnd
	 *            前一个子数组的结束索引（包括）
	 * @param rightEnd
	 *            后一个子数组的结束索引（包括）
	 * @param array
	 *            待合并的数组
	 */
	private void merge(int leftBegin, int leftEnd, int rightEnd, int[] array) {

		// 定义两个中间变量数组来缓存待合并的两个数组
		int leftLength = leftEnd - leftBegin + 1;
		int rightLength = rightEnd - leftEnd;
		int allLength = rightEnd - leftBegin + 1;

		int[] tempLeft = new int[leftLength + 1];
		int[] tempRight = new int[rightLength + 1];

		/*
		 * 给两个缓存数组赋值，缓存数组比待合并数组长度 + 1，将缓存数组最后一位赋值为Integer.MAX_VALUE，目的是为了简化代码，
		 * 在执行合并的时候不用每次都判断缓存数组是否越界。
		 */
		for (int i = 0; i < leftLength; i++) {
			tempLeft[i] = array[leftBegin + i];
		}
		tempLeft[leftLength] = Integer.MAX_VALUE;
		for (int i = 0; i < rightLength; i++) {
			tempRight[i] = array[leftEnd + 1 + i];
		}
		tempRight[rightLength] = Integer.MAX_VALUE;

		// 执行合并，讲缓存数组中的值按顺序放到array中
		int i = 0, j = 0, k = leftBegin;
		while (k < leftBegin + allLength) {
			if (tempLeft[i] <= tempRight[j]) {
				array[k] = tempLeft[i];
				i++;
			} else {
				array[k] = tempRight[j];
				j++;
			}
			k++;
		}
	}

	@Override
	public String toString() {
		return getSortName();
	}

}
