package me.yaodan.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import me.yaodan.algorithm.sort.Sort;
import me.yaodan.algorithm.sort.SortConstants;
import me.yaodan.algorithm.sort.impl.BubbleSort;
import me.yaodan.algorithm.sort.impl.BubbleSortWithFlag;
import me.yaodan.algorithm.sort.impl.CountingSort;
import me.yaodan.algorithm.sort.impl.HeapSort;
import me.yaodan.algorithm.sort.impl.InsertSort;
import me.yaodan.algorithm.sort.impl.MergeSort;
import me.yaodan.algorithm.sort.impl.QuickSort;
import me.yaodan.algorithm.sort.impl.RadixSort;
import me.yaodan.algorithm.sort.impl.SelectSort;
import me.yaodan.algorithm.sort.impl.ShellSort;

import org.junit.Test;

/**
 * 排序算法的单元测试类
 * 
 * @author yaodan.zhang
 * 
 */
public class TestSort {

	@Test
	public void testSort() {
		// 所有的排序算法
		List<Sort> sortAlgorithms = createSorts();
		Date sortBegin, sortEnd;

		for (Sort sort : sortAlgorithms) {
			int[] arrayInt = Arrays.copyOf(SortConstants.COMPARISON_INT_ARRAY,
					SortConstants.ARRAY_LENGTH);

			// 执行排序
			sortBegin = new Date();
			sort.sort(arrayInt);
			sortEnd = new Date();

			TestCase.assertTrue(Arrays.equals(arrayInt,
					SortConstants.STANDARD_SORTED_INT_ARRAY));

			// 打印排序后的数组元素及排序所用时间
			System.out.println("Time cost: "
					+ (sortEnd.getTime() - sortBegin.getTime())
					+ "ms. Sort name: " + sort.getSortName());
		}
	}

	private List<Sort> createSorts() {
		// 所有的排序算法
		List<Sort> sortAlgorithms = new ArrayList<Sort>();

		Sort bubbleSort = new BubbleSort();
		Sort bubbleSortWithFlag = new BubbleSortWithFlag();
		Sort heapSort = new HeapSort();
		Sort insertSort = new InsertSort();
		Sort mergeSort = new MergeSort();
		Sort quickSort = new QuickSort();
		Sort selectSort = new SelectSort();
		Sort shellSort = new ShellSort();

		// 以下是非比较排序
		Sort countingSort = new CountingSort();
		Sort radixSort = new RadixSort();

		sortAlgorithms.add(bubbleSort);
		sortAlgorithms.add(bubbleSortWithFlag);
		sortAlgorithms.add(heapSort);
		sortAlgorithms.add(insertSort);
		sortAlgorithms.add(mergeSort);
		sortAlgorithms.add(quickSort);
		sortAlgorithms.add(selectSort);
		sortAlgorithms.add(shellSort);
		sortAlgorithms.add(countingSort);
		sortAlgorithms.add(radixSort);

		return sortAlgorithms;
	}

}
