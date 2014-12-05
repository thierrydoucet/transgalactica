package org.transgalactica.web.recent;

import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Pile cyclique FIFO, pas de doublons.
 * 
 * @author Thierry
 */
public class CyclicMostRecentQueue<L extends RecentLabel<?>> extends AbstractQueue<L> implements Queue<L>, Serializable {

	private static final long serialVersionUID = 1L;

	private final Deque<L> items;

	private final int sizeMax;

	public CyclicMostRecentQueue() {
		this(5);
	}

	public CyclicMostRecentQueue(int sizeMax) {
		this.sizeMax = sizeMax;
		items = new LinkedList<>();
	}

	@Override
	public boolean offer(L label) {
		if (items.contains(label)) {
			items.remove(label);
		}
		if (size() >= sizeMax) {
			items.removeLast();
		}
		return items.offerFirst(label);
	}

	@Override
	public L poll() {
		return items.removeLast();
	}

	@Override
	public L peek() {
		return items.getLast();
	}

	@Override
	public Iterator<L> iterator() {
		return items.iterator();
	}

	@Override
	public int size() {
		return items.size();
	}
}
