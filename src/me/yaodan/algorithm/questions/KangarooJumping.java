package me.yaodan.algorithm.questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 袋鼠跳的问题：袋鼠可以一次跳1m，或一次跳3m，总共长10m的距离，求有几种跳法（只进不退）。
 * 
 * @author yaodan.zhang
 * 
 */
public class KangarooJumping {

	/**
	 * 袋鼠跳跃的总长度（默认为10）
	 */
	private static int totalDistance = 10;
	/**
	 * 跳跃一次较短的距离（默认为1）
	 */
	private static int shortStep = 1;
	/**
	 * 跳跃一次较长的距离（默认为3）
	 */
	private static int longStep = 3;
	/**
	 * 袋鼠跳跃方式集合，集合中的元素为一种跳跃方式，这种跳跃方式用{@link List}<tt><</tt>{@link String}
	 * <tt>></tt>，
	 */
	private static List<List<Integer>> jumpMethodList = new ArrayList<List<Integer>>();

	public static void main(String[] args) throws IOException {
		readDistance();
		List<Integer> tempJumpList = new ArrayList<Integer>();
		jump(0, 0, tempJumpList);
		printMethod();
	}

	private static void printMethod() {
		System.out.println("总的方法数量：" + jumpMethodList.size());
		System.out.println("每种方法的实现方式：");

		for (List<Integer> method : jumpMethodList) {
			for (Integer step : method) {
				System.out.print(step + ", ");
			}
			System.out.println();
		}
	}

	/**
	 * 递归计算袋鼠跳跃的各种方法
	 * 
	 * @param distance
	 *            递归过程中存储当前跳跃的总距离
	 * @param tempJumpList
	 *            递归过程中存储当前跳跃的过程
	 */
	private static void jump(int step, int distance, List<Integer> tempJumpList) {
		distance += step;
		// tempJumpList第一个元素为0
		tempJumpList.add(step);
		if (distance < totalDistance) {
			jump(shortStep, distance, tempJumpList);
			jump(longStep, distance, tempJumpList);
		} else if (distance == totalDistance) {
			// tempJumpList第一个元素为0，要从非0元素开始获取
			jumpMethodList.add(new ArrayList<Integer>(tempJumpList.subList(1,
					tempJumpList.size())));
		}
		tempJumpList.remove(tempJumpList.size() - 1);
	}

	/**
	 * 从控制台读取用户输入的袋鼠跳跃问题的参数。
	 * 
	 * @throws IOException
	 */
	private static void readDistance() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text;

		System.out.println("用户需要输入三个参数：袋鼠要跳的总长度（totalDistance），"
				+ "袋鼠跳一次较长的距离（longStep），袋鼠跳一次较短的距离（shortStep）。\n"
				+ "三者的关系是：totalDistance >= longStep > shortStep。");
		// 输入袋鼠要跳的总长度
		System.out.print("输入袋鼠要跳的总长度（m）：");
		text = br.readLine();
		try {
			totalDistance = Integer.valueOf(text);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("袋鼠要跳的总长度输入有误，请输入纯数字");
		}
		if (totalDistance <= 0) {
			throw new IllegalArgumentException("袋鼠要跳的总长度输入有误，不能小于0");
		}

		// 输入跳一次的距离（长）
		System.out.print("输入袋鼠跳一次较长的距离（m）：");
		text = br.readLine();
		try {
			longStep = Integer.valueOf(text);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("袋鼠跳一次较长的距离输入有误，请输入纯数字");
		}
		if (longStep <= 0 || longStep > totalDistance) {
			throw new IllegalArgumentException("袋鼠跳一次较长的距离输入有误，不能小于0，不能大于总长度");
		}

		// 输入跳一次的距离（短）
		System.out.print("输入袋鼠跳一次较短的距离（m）：");
		text = br.readLine();
		try {
			shortStep = Integer.valueOf(text);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("袋鼠跳一次较长的距离输入有误，请输入纯数字");
		}
		if (shortStep <= 0 || shortStep >= longStep) {
			throw new IllegalArgumentException("袋鼠跳一次较长的距离输入有误，必须于0，且小于较长的距离");
		}
	}
}
