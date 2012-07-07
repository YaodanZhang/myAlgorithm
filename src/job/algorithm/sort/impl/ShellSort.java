package job.algorithm.sort.impl;

import job.algorithm.sort.Sort;

/**
 * 希尔排序类，实现希尔排序算法。<br/>
 * 希尔排序算法的实现过程：先设定好增量序列（一个较好的序列是<tt>(2^k - 1)</tt>，序列要保证最后一个增量是<tt>1</tt>），<br/>
 * 然后根据最大的增量d，对数组中的第<tt>{1, d, 2d, ... ,kd}</tt>
 * 这一组元素进行直接插入排序，排序好后，选择下一个增量进行插入排序，直到最后一个增量<tt>1</tt>，完成排序。<br/>
 * <br/>
 * 本算法选取的增量序列为<tt>(2^k - 1)</tt>，即<tt>{... , 2^k - 1, ... , 15, 7, 3, 1}</tt>。
 * 
 * @author yaodan.zhang
 * @see Sort
 * 
 */
public class ShellSort implements Sort {

	/**
	 * 增量选择的幂指数，即k
	 */
	private int powerIndex;

	@Override
	public void sort(int[] array) {
		int incremental = initIncremental(array);
		while (incremental > 0) {
			insertSortForShell(incremental, array);
			incremental = getIncremental();
		}
	}

	@Override
	public String getSortName() {
		return "Shell Sort";
	}

	/**
	 * 为Shell排序设计的增量序列的直接插入排序方法。
	 * 
	 * @param incremental
	 *            增量
	 * @param array
	 *            待排序数组
	 */
	private void insertSortForShell(int incremental, int[] array) {
		int i = incremental;
		int arrayLength = array.length;

		while (i < arrayLength) {
			int key = array[i];
			int j = i;
			while (j > 0 && array[j - incremental] > key) {
				array[j] = array[j - incremental];
				j = j - incremental;
			}
			array[j] = key;

			i = i + incremental;
		}
	}

	/**
	 * 根据数组长度初始化增量序列的幂指数
	 * 
	 * @param array
	 *            待排序数组
	 * @return 增量序列中最大的增量（即第一个使用的增量）
	 */
	private int initIncremental(int[] array) {
		powerIndex = (int) (Math.log(array.length / 2 + 1) / Math.log(2));
		return getIncremental();
	}

	/**
	 * 根据幂指数{@linkplain #powerIndex}计算当前的增量。
	 */
	private int getIncremental() {
		if (powerIndex > -1) {
			return (int) Math.pow(2, powerIndex--) - 1;
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return getSortName();
	}
}
