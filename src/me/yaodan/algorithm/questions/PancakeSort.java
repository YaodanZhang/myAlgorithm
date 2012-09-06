package me.yaodan.algorithm.questions;

import java.util.Arrays;

import me.yaodan.algorithm.sort.SortConstants;

/**
 * 《编程之美》题目1.3：一堆烙饼的排序<br/>
 * <br/>
 * 一次抓住最上面几块，上下颠倒，反复几次后排好序。
 * 
 * @author yaodan.zhang
 * 
 */
public class PancakeSort {

	private static int REVERSE_COUNT;

	public static void main(String[] args) {
		REVERSE_COUNT = 0;
		int[] array = SortConstants.COMPARISON_INT_ARRAY;

		System.out.println("初始数组：");
		printArray(array);

		sort(array);

		System.out.println("排序后数组：");
		printArray(array);

		System.out
				.println("排序正确？"
						+ Arrays.equals(array,
								SortConstants.STANDARD_SORTED_INT_ARRAY));

		System.out.println("翻转次数：" + REVERSE_COUNT);
	}

	private static void sort(int[] array) {
		for (int i = 0; i < SortConstants.ARRAY_LENGTH - 1; i++) {
			int position = find(array, 0, i, array[i + 1]);
			reverse(array, 0, position - 1);
			reverse(array, 0, i);
			reverse(array, 0, i + 1);
			reverse(array, 0, position);
		}
	}

	/**
	 * 将数组<tt>array</tt>从<tt>begin</tt>（包括）到<tt>end</tt>（包括）之间的元素翻转。
	 */
	private static void reverse(int[] array, int begin, int end) {
		while (begin < end) {
			int temp = array[begin];
			array[begin++] = array[end];
			array[end--] = temp;
		}
		REVERSE_COUNT++;
	}

	/**
	 * 查找关键值为<tt>key</tt>的元素在数组<tt>array</tt>从<tt>begin</tt>（包括）到<tt>end</tt>
	 * （包括）之间应该在什么位置。<br/>
	 * 数组<tt>array</tt>从<tt>begin</tt>（包括）到<tt>end</tt>（包括）之间已经按从小到大排好序。
	 */
	private static int find(int[] array, int begin, int end, int key) {
		for (; begin <= end; begin++) {
			if (key <= array[begin]) {
				break;
			}
		}
		return begin;
	}

	/**
	 * 打印数组
	 */
	private static void printArray(int[] array) {
		System.out
				.println("-------------------------------------------------------");
		for (int i : array) {
			System.out.print(i + ", ");
		}
		System.out
				.println("\n-------------------------------------------------------\n");
	}
}
