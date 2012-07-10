package me.yaodan.job.algorithm.sort.impl;

import me.yaodan.job.algorithm.sort.Sort;

/**
 * 冒泡排序类，实现冒泡排序算法。<br/>
 * 冒泡排序过程：每一轮遍历，从数组第一个位置开始，依次将数字与右边位置的数字比较，如果比较大就交换，从而将最大的数以“冒泡”的方式放到数组末尾。
 * 
 * @author yaodan.zhang
 * @see Sort
 * 
 */
public class BubbleSort implements Sort {

	@Override
	public void sort(int[] array) {
		for (int i = array.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	@Override
	public String getSortName() {
		return "Bubble Sort";
	}

	@Override
	public String toString() {
		return getSortName();
	}
}
