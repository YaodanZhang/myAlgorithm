package job.algorithm.sort.impl;

import java.util.Arrays;

import job.algorithm.sort.SortConstants;
import job.algorithm.sort.Sort;

/**
 * 基数排序类，实现基数排序算法。<br/>
 * 基数排序的实现过程：对于<tt>d</tt>位的正整数，先按个位的大小进行排序，再按十位，直到第<tt>d</tt>位。<br/>
 * <br/>
 * 注意：对每一位进行排序时必须使用稳定的排序，比如计数排序，直接插入排序等。<br/>
 * 注意：一定要从最低位开始而不是从最高位开始。
 * 
 * @author yaodan.zhang
 * @see Sort
 * 
 */
public class RadixSort implements Sort {

	@Override
	public void sort(int[] array) {
		int maxRadix = getMaxRadix(SortConstants.ARRAY_ELEMENT_INT_MAX);
		for (int radix = 1; radix <= maxRadix; radix++) {
			countingSortForRadix(radix, array);
		}
	}

	@Override
	public String getSortName() {
		return "Radix Sort";
	}

	private void countingSortForRadix(int radix, int[] array) {
		int countingLength = 11;
		int arrayLength = array.length;
		int[] countingResult = new int[countingLength];
		int[] mirroArray = Arrays.copyOf(array, arrayLength);
		int[] radixArray = new int[arrayLength];

		for (int i = 0; i < countingLength; i++) {
			countingResult[i] = 0;
		}

		for (int i = 0; i < arrayLength; i++) {
			radixArray[i] = getRadixValue(radix, array[i]);
		}

		for (int i = 0; i < arrayLength; i++) {
			countingResult[radixArray[i]]++;
		}

		for (int i = 1; i < countingLength; i++) {
			countingResult[i] += countingResult[i - 1];
		}

		for (int i = arrayLength - 1; i >= 0; i--) {
			array[countingResult[radixArray[i]] - 1] = mirroArray[i];
			countingResult[radixArray[i]]--;
		}
	}

	/**
	 * 计算数组中元素最大的一共有几位。
	 * 
	 * @param maxValue
	 *            数组中元素的最大值
	 * @return 数组中元素最大值的位数
	 */
	private static int getMaxRadix(int maxValue) {
		int radix = 0;
		while (maxValue > 0) {
			maxValue /= 10;
			radix++;
		}
		return radix;
	}

	private static int getRadixValue(int radix, int value) {
		for (; radix > 1; radix--) {
			value /= 10;
		}
		return value % 10;
	}

	@Override
	public String toString() {
		return getSortName();
	}
}
