package me.yaodan.algorithm.sort.impl;

import me.yaodan.algorithm.sort.Sort;

/**
 * 插入排序类，实现插入排序算法。<br/>
 * 插入排序算法的实现过程：第<tt>k</tt>次遍历排好前<tt>(k+1)</tt>个元素，然后下一次遍历，将第<tt>(k+2)</tt>
 * 个元素依次同它前面的元素比较，如果比较小就交换，直到不用交换，再进行下一次遍历。
 * 
 * @author yaodan.zhang
 * @see Sort
 * 
 */
public class InsertSort implements Sort {

	@Override
	public void sort(int[] array) {
		for (int i = 1, arrayLength = array.length; i < arrayLength; i++) {
			int j = i;
			int key = array[j];
			while (j > 0 && key < array[j - 1]) {
				array[j] = array[j - 1];
				j--;
			}
			array[j] = key;
		}

	}

	@Override
	public String getSortName() {
		return "Insert Sort";
	}

	@Override
	public String toString() {
		return getSortName();
	}

}
