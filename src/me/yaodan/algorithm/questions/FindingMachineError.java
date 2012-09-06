package me.yaodan.algorithm.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 《编程之美》题目1.5：快速找出故障机器。
 * 
 * @author yaodan.zhang
 * 
 */
public class FindingMachineError {

	/**
	 * 数据ID的最大值
	 */
	private static final int maxId = 1000000000;

	/**
	 * 数据条数
	 */
	private static final int dataCount = 100000;

	/**
	 * 出现错误的机器数量
	 */
	private static int errorCount = 456;

	public static void main(String[] args) {
		long begin, end;

		// 初始化数组
		begin = System.currentTimeMillis();
		int[] array = initArray();
		end = System.currentTimeMillis();
		System.out.println("初始化时间：" + (end - begin) + "ms");

		begin = end;

		// 执行查找：算法一
		int tail = method1PutTwoTogether(array);
		end = System.currentTimeMillis();
		System.out.println("费时：" + (end - begin) + "ms");
		System.out.println("故障机器数量：" + (array.length - tail));
		for (int i = tail; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out
				.println("\n--------------------------------------------------------------");

		// 初始化数组
		begin = System.currentTimeMillis();
		array = initArray();
		end = System.currentTimeMillis();
		System.out.println("初始化时间：" + (end - begin) + "ms");

		begin = end;

		// 执行查找：算法二
		List<Integer> errorList = method2Sort(array);
		end = System.currentTimeMillis();
		System.out.println("费时：" + (end - begin) + "ms");
		System.out.println("故障机器数量：" + errorList.size());
		for (Integer error : errorList) {
			System.out.print(error + ", ");
		}
	}

	/**
	 * 算法一：将相同的两个放到一起，只有一个的放到最后
	 * 
	 * @param array
	 * @return
	 */
	private static int method1PutTwoTogether(int[] array) {
		int tail = array.length;
		for (int i = 0; i < tail;) {
			int j = i + 1;
			for (; j < array.length; j++) {
				if (array[i] == array[j]) {
					int temp = array[i + 1];
					array[i + 1] = array[j];
					array[j] = temp;
					break;
				}
			}
			if (j >= array.length) {
				int temp = array[i];
				array[i] = array[tail - 1];
				array[--tail] = temp;
			} else {
				i += 2;
			}
		}
		return tail;
	}

	/**
	 * 算法二：进行排序
	 * 
	 * @param array
	 * @return
	 */
	private static List<Integer> method2Sort(int[] array) {
		List<Integer> errorList = new ArrayList<Integer>();
		quickSort(array, 0, array.length - 1);
		for (int i = 0; i < array.length;) {
			if (i < array.length - 1 && array[i] == array[i + 1]) {
				i += 2;
			} else {
				errorList.add(array[i++]);
			}
		}
		return errorList;
	}

	private static void quickSort(int[] array, int begin, int end) {
		if (begin < end) {
			int middle = partition(array, begin, end);
			quickSort(array, begin, middle - 1);
			quickSort(array, middle + 1, end);
		}
	}

	private static int partition(int[] array, int begin, int end) {
		int key = array[end];
		int i = begin, j = begin;
		while (j < end) {
			if (array[j] < key) {
				int temp = array[j];
				array[j] = array[i];
				array[i] = temp;
				i++;
			}
			j++;
		}
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;

		return i;
	}

	/**
	 * 初始化数据ID数组
	 * 
	 * @return
	 */
	private static int[] initArray() {
		Random random = new Random();
		int[] array = new int[dataCount * 2 - errorCount];
		int radix = maxId / dataCount;

		for (int i = 0; i < dataCount; i++) {
			array[i] = radix * i + random.nextInt(radix);
			// array[i] = i;
		}
		for (int i = 0; i < dataCount - errorCount; i++) {
			array[i + dataCount] = array[i];
		}

		// 打乱次序
		for (int i = 0; i < dataCount * 2; i++) {
			int m = random.nextInt(dataCount * 2 - errorCount);
			int n = random.nextInt(dataCount * 2 - errorCount);
			int temp = array[m];
			array[m] = array[n];
			array[n] = temp;
		}
		return array;
	}
}
