/*
 * Author: Yi Xing
 * Written: 07/26/2014
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

//import java.util.*;

public class Deque<Item> implements Iterable<Item> {

	private class Node {
		private Item item;
		private Node next;
		private Node previous;
	}

	private class DequeIterator implements Iterator<Item> {
		private Node now = sentinel;

		public boolean hasNext() {
			return now.next != sentinel;
		}

		public Item next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			now = now.next;
			Item item = now.item;
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private int size = 0;
	private Node sentinel;

	// construct an empty deque
	public Deque() {
		sentinel = new Node();
		sentinel.item = null;
		sentinel.next = sentinel;
		sentinel.previous = sentinel;
		
	}

	// is the deque empty?
	public boolean isEmpty() {
		return sentinel.next == sentinel;
	}

	// return the number of items on the deque
	public int size() {
		return size;
	}

	// insert the item at the front
	public void addFirst(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}

		Node newFirst = new Node();
		newFirst.item = item;
		newFirst.previous = sentinel;
		newFirst.next = sentinel.next;
		sentinel.next.previous = newFirst;
		sentinel.next = newFirst;
	
		size++;
		
	}

	// insert the item at the end
	public void addLast(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}

		Node newLast = new Node();
		newLast.item = item;
		newLast.previous = sentinel.previous;
		newLast.next = sentinel;
		sentinel.previous.next = newLast;
		sentinel.previous = newLast;

		size++;
	
	}

	// delete and return the item at the front
	public Item removeFirst() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		Item result = sentinel.next.item;
		sentinel.next = sentinel.next.next;
		sentinel.next.previous = sentinel;
		
		size--;
		return result;
	}

	// delete and return the item at the end
	public Item removeLast() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		Item result = sentinel.previous.item;
		sentinel.previous = sentinel.previous.previous;
		sentinel.previous.next = sentinel;

		size--;

		return result;
	}

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	
	// unit testing
	/*
	 * public static void main(String[] args) {
	 * 
	 * }
	 */
}
