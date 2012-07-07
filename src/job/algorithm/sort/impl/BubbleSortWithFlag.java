package job.algorithm.sort.impl;

import job.algorithm.sort.Sort;

/**
 * 冒泡排序类，实现冒泡排序算法。<br/>
 * 冒泡排序过程：每一轮遍历，从数组第一个位置开始，依次将数字与右边位置的数字比较，如果比较大就交换，从而将最大的数以“冒泡”的方式放到数组末尾。<br/>
 * 本算法与一般的冒泡排序算法的区别在于，普通的冒泡排序，一定会执行<tt>(n-1)</tt>次遍历，而本算法，对于在未到<tt>(n-1)</tt>
 * 次遍历就已经排好队的数组， 将不进行后来的遍历。<br/>
 * 相对于一般的冒泡排序算法，本算法适用于基本有序和n比较大的情况。
 * 
 * @author yaodan.zhang
 * @see Sort
 * 
 */
public class BubbleSortWithFlag implements Sort {

	@Override
	public void sort(int[] array) {
		for (int i = array.length - 1; i > 0; i--) {
			boolean flag = false;
			for (int j = 0; j < i; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					flag = true;
				}
			}
			if (!flag) {
				break;
			}
		}
	}

	@Override
	public String getSortName() {
		return "Bubble Sort With Flag";
	}

	@Override
	public String toString() {
		return getSortName();
	}

}
