package me.yaodan.algorithm.questions;

import me.yaodan.algorithm.sort.SortConstants;

/**
 * 《编程之美》题1.4：买书折扣问题。
 * 
 * @author yaodan.zhang
 * 
 */
public class BookToBuy {

	private static float[] DISCOUNTS = { 1, 0.95f, 0.9f, 0.8f, 0.75f };

	private static float COST_PER_BOOK = 8;

	public static void main(String[] args) {
		// 书的每一卷的数量数组
		int[] bookCount = SortConstants.COMPARISON_INT_ARRAY;

		System.out.println("买书数量：");
		for (int i : bookCount) {
			System.out.print(i + ", ");
		}
		System.out.println("\n---------------------------------------");

		float cost = countByEqualCost(bookCount);
		System.out.println("总共花费：" + cost);
	}

	private static float countByEqualCost(int[] bookCount) {
		float cost = 0;
		sortBigFirst(bookCount);

		// 一起购买的书的卷数
		int bookTogether = 5;
		while (bookCount[0] > 0) {
			for (; bookTogether > 0; bookTogether--) {
				if (bookCount[bookTogether - 1] > 0) {
					break;
				}
			}

			cost += bookTogether * COST_PER_BOOK * DISCOUNTS[bookTogether - 1];

			for (int i = 0; i < bookTogether; bookCount[i++]--)
				;
		}
		return cost;
	}

	private static void sortBigFirst(int[] bookCount) {
		for (int i = 1; i < bookCount.length; i++) {
			int j = i, key = bookCount[i];
			while (j > 0 && bookCount[j - 1] < key) {
				bookCount[j] = bookCount[j - 1];
				j--;
			}
			bookCount[j] = key;
		}
	}
}
