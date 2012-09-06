package me.yaodan.algorithm.questions;

import java.util.Random;

/**
 * 《编程之美》题目1.8：电梯调度问题
 * 
 * @author yaodan.zhang
 * 
 */
public class ElevatorDispatching {

	private static int topFloor = 100;

	private static int personCount = 400;

	private static final int[] personFloor;

	static {
		personFloor = initArray();
	}

	public static void main(String[] args) {
		long begin, end;

		System.out.println("每个乘客的层数：");
		for (int floor : personFloor) {
			System.out.print(floor + ", ");
		}
		System.out.println("\n--------------------------------");

		// 每层去试算法
		begin = System.currentTimeMillis();
		int checkEveryResult = checkEvery(personFloor, topFloor);
		end = System.currentTimeMillis();
		System.out.println(checkEveryResult);
		System.out.println("每层去试费时：" + (end - begin) + "ms");

	}

	private static int checkEvery(int[] personFloor, int topFloor) {
		int total = personFloor.length * topFloor;
		int resultFloor = 0;
		for (int i = 1; i <= topFloor; i++) {
			int sum = 0;
			for (int floor : personFloor) {
				sum += (floor - i) >= 0 ? (floor - i) : (i - floor);
			}
			if (total >= sum) {
				total = sum;
				resultFloor = i;
			}
		}
		return resultFloor;
	}

	/**
	 * 初始化数组
	 * 
	 * @return
	 */
	private static int[] initArray() {
		Random random = new Random();
		int[] array = new int[personCount];

		for (int i = 0; i < personCount; i++) {
			array[i] = 1 + random.nextInt(topFloor);
		}

		return array;
	}
}
