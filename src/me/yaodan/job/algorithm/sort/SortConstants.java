package me.yaodan.job.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 排序算法包中用到的常量
 * 
 * @author yaodan.zhang
 * 
 */
public final class SortConstants {
	private SortConstants() {
	}

	/**
	 * int型数组中元素的最大值。注意，不能被设置成Integer.MAX_VALUE，最大值的integer要用在程序中。
	 */
	public static final int ARRAY_ELEMENT_INT_MAX = 100000;

	/**
	 * 数组长度
	 */
	public static final int ARRAY_LENGTH = 2000;

	/**
	 * int型数组，长度为{@linkplain #ARRAY_LENGTH}
	 */
	public static final int[] COMPARISON_INT_ARRAY = new int[ARRAY_LENGTH];

	/**
	 * 排序好的数组（根据数组{@linkplain #COMPARISON_INT_ARRAY}），用于测试排序算法是否正确
	 */
	public static final int[] STANDARD_SORTED_INT_ARRAY;

	static {
		Random random = new Random();
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			COMPARISON_INT_ARRAY[i] = random.nextInt(ARRAY_ELEMENT_INT_MAX);
		}
		STANDARD_SORTED_INT_ARRAY = Arrays.copyOf(COMPARISON_INT_ARRAY,
				ARRAY_LENGTH);
		Arrays.sort(STANDARD_SORTED_INT_ARRAY);
	}
}
