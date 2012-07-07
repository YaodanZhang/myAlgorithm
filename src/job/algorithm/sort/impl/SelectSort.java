package job.algorithm.sort.impl;

import job.algorithm.sort.Sort;

/**
 * 选择排序类，实现选择排序算法。<br/>
 * 选择排序的实现过程：每一轮遍历数组，选择出当前最小的值放在数组头部，并从下一个位置开始再今次进行一次遍历。
 * 
 * @author yaodan.zhang
 * @see Sort
 * 
 */
public class SelectSort implements Sort {

	@Override
	public void sort(int[] array) {
		for (int i = 0, arrayLength = array.length; i < arrayLength - 1; i++) {
			for (int j = i + 1; j < arrayLength; j++) {
				if (array[j] < array[i]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
	}

	@Override
	public String getSortName() {
		return "Select Sort";
	}

	@Override
	public String toString() {
		return getSortName();
	}

}
