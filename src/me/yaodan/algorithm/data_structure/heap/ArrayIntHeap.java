package me.yaodan.algorithm.data_structure.heap;

/**
 * 用数组实现的<tt>int</tt>型堆
 * 
 * @author yaodan.zhang
 * 
 */
public class ArrayIntHeap {

	private boolean maxFirst;
	private int[] array;
	/**
	 * 整个数组的长度
	 */
	private int arrayLength;
	/**
	 * 当前堆中元素的个数，初始化过后，应该与{@linkplain #arrayLength}相等，在堆排序的过程中不会相等。
	 */
	private int heapLength;

	/**
	 * 构造方法，构造一个空堆。
	 * 
	 * @param maxFirst
	 *            值为<tt>true</tt>，则构造一个最大堆对象，否则，构造一个最小堆对象。
	 */
	public ArrayIntHeap(boolean maxFirst) {
		this.arrayLength = 0;
		this.heapLength = 0;
		this.maxFirst = maxFirst;
		this.array = new int[0];
	}

	/**
	 * 构造方法，将参数中的<tt>array</tt>数组构造为一个堆。<br/>
	 * <br/>
	 * 注意，此方法获取了<tt>array</tt>的引用，会改变<tt>array</tt>。
	 * 
	 * @param maxFirst
	 *            值为<tt>true</tt>，则构造一个最大堆对象，否则，构造一个最小堆对象。
	 * @param array
	 *            用于建堆的数组。
	 */
	public ArrayIntHeap(boolean maxFirst, int[] array) {
		this.maxFirst = maxFirst;
		this.arrayLength = array.length;
		// 初始化，堆中元素个数为整个数组的长度
		this.heapLength = this.arrayLength;
		this.array = array;
		initializeHeap();
	}

	/**
	 * 完成堆排序过程。
	 */
	public void sort() {
		for (int i = this.arrayLength - 1; i > 0; i--) {
			int temp = array[0];
			array[0] = array[i];
			array[i] = temp;
			heapLength--;
			heapify(0);
		}
		// 完成排序过后要将堆的长度重新设置为数组长度，因为排序好后的数组仍然是一个堆。
		heapLength = arrayLength;
	}

	/**
	 * 获取当前堆中的最大元素
	 * 
	 * @return 当前堆中的最大元素
	 */
	public int getMax() {
		return this.array[0];
	}

	/**
	 * 获取当前堆中的最大元素并将其从堆中去除。
	 * 
	 * @return 当前堆中的最大元素
	 */
	public int extractMax() {
		int max = array[0];
		array[0] = array[heapLength - 1];
		arrayLength--;
		heapLength--;
		System.arraycopy(array, 0, array, 0, arrayLength);
		heapify(0);
		return max;
	}

	/**
	 * 将指定节点的元素值增加成<tt>value</tt>，如果当前元素的值比<tt>value</tt>大（对于最大堆）/比
	 * <tt>value</tt>小（对于最小堆），则不会改变。
	 * 
	 * @param index
	 *            要改变值的节点索引
	 * @param value
	 *            新的值
	 */
	public void increaseValue(int index, int value) {
		if (checkTop(value, array[index])) {
			array[index] = value;
			int parentIndex = HeapUtil.getParentIndex(index);
			while (index > 0 && checkTop(array[index], array[parentIndex])) {
				int temp = array[index];
				array[index] = array[parentIndex];
				array[parentIndex] = temp;
				index = HeapUtil.getParentIndex(index);
				parentIndex = HeapUtil.getParentIndex(index);
			}
		} else {
			System.out.println("The value of the element at the index \""
					+ index + "\" is \"" + array[index]
					+ "\", it\'s more than the new value \"" + value
					+ "\" that would be increased to,"
					+ " and this application won\'t change it!");
			return;
		}
	}

	/**
	 * 向堆中插入一个元素
	 * 
	 * @param value
	 *            要插入的元素的值
	 */
	public void insert(int value) {
		arrayLength++;
		heapLength++;
		System.arraycopy(array, 0, array, 0, arrayLength);
		// TODO 要改这一行，这是最大堆最小堆的问题
		array[arrayLength] = Integer.MIN_VALUE;
		increaseValue(arrayLength - 1, value);
	}

	/**
	 * 堆以<tt>index</tt>为父节点的子树进行一次保持堆性质的操作。
	 * 
	 * @param index
	 *            要进行操作的子树的父节点。
	 */
	private void heapify(int index) {
		int topIndex = index;
		int leftIndex = HeapUtil.getLetfChildIndex(index);
		int rightIndex = HeapUtil.getRightChildIndex(index);

		// 找到父当前节点和左右子节点中的最大/小节点的索引，赋值给mtopIndex。
		if (leftIndex < heapLength && checkTop(array[leftIndex], array[index])) {
			topIndex = leftIndex;
		}
		if (rightIndex < heapLength
				&& checkTop(array[rightIndex], array[topIndex])) {
			topIndex = rightIndex;
		}

		// 如果当前节点不是最大节点，则将当前节点与最大/小节点交换，并对该子节点调用一次heapify。
		if (topIndex != index) {
			int temp = array[topIndex];
			array[topIndex] = array[index];
			array[index] = temp;
			heapify(topIndex);
		}
	}

	/**
	 * 初始化构造堆，从最后一个非叶子节点开始，自底向上地调用{@link #heapify(int)}方法，实现建堆。
	 */
	private void initializeHeap() {
		for (int i = heapLength / 2 - 1; i >= 0; i--) {
			heapify(i);
		}
	}

	/**
	 * 判断两个元素中的哪个适合做堆顶元素。<br/>
	 * <br/>
	 * 如果当前堆是最大堆，且 <tt>element1 > element2</tt>，则返回<tt>true</tt>，否则返回
	 * <tt>false</tt>；<br/>
	 * 如果当前堆是最小堆，且 <tt>element1 > element2</tt>，则返回<tt>false</tt>，否则返回
	 * <tt>true</tt>。
	 */
	private boolean checkTop(int element1, int element2) {
		if (this.maxFirst) {
			return element1 > element2;
		} else {
			return element1 < element2;
		}
	}
}
