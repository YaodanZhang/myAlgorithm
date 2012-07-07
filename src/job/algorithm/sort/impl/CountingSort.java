package job.algorithm.sort.impl;

import java.util.Arrays;

import job.algorithm.sort.SortConstants;
import job.algorithm.sort.Sort;

/**
 * 计数排序类，实现计数排序算法。<br/>
 * <br/>
 * 计数排序的原理：<br/>
 * 前提：数组A中的元素均小于<tt>k</tt>；<br/>
 * 排序过程：新建长度为<tt>(k + 1)</tt>的数组<tt>C</tt>作为中间数组，统计<tt>A</tt>中每个元素的出现次数，新建长度为
 * <tt>n</tt>的数组<tt>B</tt>用来存储排序结果，遍历一遍<tt>A</tt>，将其中每个元素出现的次数统计到<tt>C</tt>中
 * ，再遍历一遍<tt>C</tt>，统计计数和 （即对每一个<tt>i = 0, 1, 2, ..., k</tt>，有多少个元素是小于或等于
 * <tt>i</tt> 的），最后遍历一遍 <tt>A</tt>（从末尾开始到头，保证排序的稳定性），对每个<tt>A[j]</tt>，
 * <tt>C[A[j]]</tt>的值即为 <tt>A[j]</tt>在最后的排序结果中的位置，因为共有<tt>C[A[j]]</tt>个元素小于或等于
 * <tt>A[j]</tt>。
 * 
 * @author yaodan.zhang
 * @see Sort
 * 
 */
public class CountingSort implements Sort {

	@Override
	public void sort(int[] array) {
		int arrayLength = array.length;
		int countingArrayLength = SortConstants.ARRAY_ELEMENT_INT_MAX + 1;
		int[] mirroArray = Arrays.copyOf(array, arrayLength);
		int[] countResult = new int[countingArrayLength];

		// 遍历原数组，统计其中元素出现的次数。
		for (int i = 0; i < arrayLength; i++) {
			countResult[mirroArray[i]] += 1;
		}

		// 遍历统计数组，统计计数和
		for (int i = 1; i < countingArrayLength; i++) {
			countResult[i] += countResult[i - 1];
		}

		for (int i = arrayLength - 1; i >= 0; i--) {
			array[countResult[mirroArray[i]] - 1] = mirroArray[i];
			countResult[mirroArray[i]] -= 1;
		}
	}

	@Override
	public String getSortName() {
		return "Counting Sort";
	}

	@Override
	public String toString() {
		return getSortName();
	}

}
