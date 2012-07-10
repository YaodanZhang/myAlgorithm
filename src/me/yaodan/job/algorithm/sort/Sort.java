package me.yaodan.job.algorithm.sort;

/**
 * 排序接口，所有排序算法类均要实现这个接口。具体的排序算法实现类通过{@linkplain #sort(int[])}方法实现排序，通过
 * {@linkplain #getSortName()}方法获取排序算法的名称。
 * 
 * @author yaodan.zhang
 * 
 */
public interface Sort {

	/**
	 * int型数组的排序算法实现方法，将int型数组array中的元素排序（按从小到大的顺序）。<br/>
	 * <br/>
	 * 注意，对于数组<tt>array</tt>并未进行保护性拷贝，排序前后<tt>array</tt>数组会发生改变。
	 * 
	 * @param array
	 *            要排序的int型数组
	 */
	public void sort(int[] array);

	/**
	 * 获取排序类型的名称，如“归并排序”，“插入排序”等。
	 * 
	 * @return 排序类型
	 */
	public String getSortName();
}
