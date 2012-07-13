package me.yaodan.algorithm.data_structure;

import junit.framework.TestCase;

import me.yaodan.algorithm.data_structure.DisjointSet;

import org.junit.Test;

/**
 * 查并集算法的单元测试类。
 * 
 * @author yaodan.zhang
 * 
 */
public class TestDisjointSet {

	@Test
	public void test() {
		DisjointSet<String> disjointSet = new DisjointSet<String>();
		String[] chars = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z" };
		String[] charsA = { "a", "b", "c", "d", "e", "f", "g" };
		String[] charsH = { "h", "i", "j", "k", "l", "m", "n" };
		String[] charsO = { "o", "p", "q", "r", "s", "t" };
		String[] charsU = { "u", "v", "w", "x", "y", "z" };

		for (String c : chars) {
			disjointSet.makeSet(c);
		}

		disjointSet.union("a", charsA);
		disjointSet.union("h", charsH);
		disjointSet.union("o", charsO);
		disjointSet.union("u", charsU);

		for (String c : charsA) {
			TestCase.assertEquals("a", disjointSet.findSet(c));
		}
		for (String c : charsH) {
			TestCase.assertEquals("h", disjointSet.findSet(c));
		}
		for (String c : charsO) {
			TestCase.assertEquals("o", disjointSet.findSet(c));
		}
		for (String c : charsU) {
			TestCase.assertEquals("u", disjointSet.findSet(c));
		}
	}
}
