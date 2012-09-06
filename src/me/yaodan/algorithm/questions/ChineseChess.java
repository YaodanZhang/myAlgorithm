package me.yaodan.algorithm.questions;

/**
 * 编程之美题目：1.2，中国象棋将帅问题
 * 
 * @author yaodan.zhang
 * 
 */
public class ChineseChess {

	public static void main(String[] args) {
		chess(new int[] { 0, 2 }, new int[] { 2, 0 });
	}

	/**
	 * 计算并打印将帅的所有合法位置（打印坐标，并假设他们处于同一个3乘3的格子中）。
	 * 
	 * @param a
	 *            “将”位置数组。为int数组，该数组包含两个元素，第一个元素为x坐标（0到2），后一个为y坐标（0到2）。
	 * @param b
	 *            “帅”位置坐标。为int数组，该数组包含两个元素，第一个元素为x坐标（0到2），后一个为y坐标（0到2）。
	 */
	private static void chess(int[] a, int[] b) {
		if (a[0] < 0 || a[0] > 2 || a[1] < 0 || a[1] > 2 || b[0] < 0
				|| b[0] > 2 || b[1] < 0 || b[1] > 2) {
			throw new IllegalArgumentException(
					"the arguments of method 'chess' should between 0 and 2, and a[1] != b[1]");
		}

		System.out.println("初始位置：将：(" + a[0] + ", " + a[1] + ")，帅：(" + b[0]
				+ ", " + b[1] + ")\n合法位置：");

		long begin = System.nanoTime();
		// 先确定合法的X坐标
		for (int ax = 0; ax < 3; ax++) {
			for (int bx = 0; bx < 3; bx++) {
				if ((ax < bx && a[0] < b[0]) || (ax > bx && a[0] > b[0])) {
					for (int ay = 0; ay < 3; ay++) {
						for (int by = 0; by < 3; by++) {
							System.out.printf("将：(%d, %d)，帅：(%d, %d)\n", ax,
									ay, bx, by);
						}
					}
				}
			}
		}
		long end = System.nanoTime();
		System.out.println("cost: " + (end - begin) + "ns");
	}
}
