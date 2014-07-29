/*
 * Author: Yi Xing
 * Written: 07/26/2014
 */

import java.util.Iterator;
import edu.princeton.cs.introcs.*;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] queue;
	private int size;

	// construct an empty randomized queue
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {

		this.queue = (Item[]) new Object[1];
		this.size = 0;
	}

	// is the queue empty?
	public boolean isEmpty() {
		return (size == 0);
	}

	// return the number of items on the queue
	public int size() {
		return size;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		if (size == queue.length)
			resize(2 * queue.length);
		queue[size++] = item;
	}

	// delete and return a random item
	public Item dequeue() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		int r = StdRandom.uniform(size);
		exch(queue, r, --size);
		Item result = queue[size];
		queue[size] = null;

		if (size > 1 && size <= queue.length / 4) {
			resize(queue.length / 4);
		}
		return result;
	}

	// return (but do not delete) a random item
	public Item sample() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		return queue[StdRandom.uniform(size)];
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator(queue, size);
	}

	private void resize(int capacity) {
		@SuppressWarnings("unchecked")
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			copy[i] = queue[i];
		}
		queue = copy;
	}

	private void exch(Item[] ex, int i, int j) {
		if (i == j) {
			return;
		}
		Item temp = ex[i];
		ex[i] = ex[j];
		ex[j] = temp;
	}

	private class RandomizedQueueIterator implements Iterator<Item> {
		
		private Item[] iteratorQueue;
		private int iteratorIndex = 0;

		@SuppressWarnings("unchecked")
		public RandomizedQueueIterator(Item[] queue, int size) {
			iteratorQueue = (Item[]) new Object[size];

			for (int i = 0; i < iteratorQueue.length; i++) {
				iteratorQueue[i] = queue[i];
			}
			StdRandom.shuffle(iteratorQueue);

		}

		public boolean hasNext() {
			return (iteratorIndex < iteratorQueue.length);
		}

		public Item next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			Item item = iteratorQueue[iteratorIndex];
			iteratorIndex++;

			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	
	// unit testing
	/*
	 * public static void main(String[] args) {
	 * 
	 * }
	 */

}
