package org.transgalactica.web.recent.impl;

import org.transgalactica.web.recent.RecentLabel;

public class AbstractRecentLabel<K> implements RecentLabel<K> {

	private static final long serialVersionUID = 1L;

	private final K key;
	private final Object[] arguments;

	public AbstractRecentLabel(K key, Object... arguments) {
		super();
		this.key = key;
		this.arguments = arguments;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public Object[] getArguments() {
		return arguments;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof RecentLabel) {
			return key.equals(((RecentLabel<?>) obj).getKey());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return key.hashCode();
	}
}