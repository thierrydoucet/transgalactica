package org.transgalactica.fwk.domain.bean;

import java.io.Serializable;
import java.util.SortedMap;

/**
 * Classe abstraite d'un Bo.
 * 
 * Modifie le comportement méthodes equals(), hashcode() et toString() afin de
 * s'appuyer la notion de BusinessIdentifier.
 * 
 * @author Thierry
 */
public abstract class AbstractBo implements Serializable {

	private static final long serialVersionUID = 1L;

	protected AbstractBo() {
	}

	/*
	 * @see java.lang.Object
	 */

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o == null || o.getClass().isAssignableFrom(Serializable.class)) {
			return false;
		}
		SortedMap<String, Serializable> key1 = BusinessIdentifierUtils.getBusinessIdentifiersValues(this);
		SortedMap<String, Serializable> key2 = BusinessIdentifierUtils.getBusinessIdentifiersValues((Serializable) o);
		return key1.equals(key2);
	}

	@Override
	public int hashCode() {
		return BusinessIdentifierUtils.getBusinessIdentifiersValues(this).hashCode();
	}

	@Override
	public String toString() {
		return new StringBuilder().append("[").append(getClass().getName()).append(" business id: ")
				.append(BusinessIdentifierUtils.getBusinessIdentifiersValues(this)).append("]").toString();
	}
}
