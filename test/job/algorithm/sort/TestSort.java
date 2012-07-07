package job.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import job.algorithm.sort.impl.BubbleSort;
import job.algorithm.sort.impl.BubbleSortWithFlag;
import job.algorithm.sort.impl.CountingSort;
import job.algorithm.sort.impl.HeapSort;
import job.algorithm.sort.impl.InsertSort;
import job.algorithm.sort.impl.MergeSort;
import job.algorithm.sort.impl.QuickSort;
import job.algorithm.sort.impl.RadixSort;
import job.algorithm.sort.impl.SelectSort;
import job.algorithm.sort.impl.ShellSort;
import junit.framework.TestCase;

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
