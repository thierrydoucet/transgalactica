package org.transgalactica.fwk.domain.bean;

import java.io.Serializable;

public class CompositeId implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cle1;

	private long cle2;

	public CompositeId() {
		this(null, 0);
	}

	public CompositeId(String cle1, long cle2) {
		this.cle1 = cle1;
		this.cle2 = cle2;
	}

	public String getCle1() {
		return cle1;
	}

	public void setCle1(String cle1) {
		this.cle1 = cle1;
	}

	public long getCle2() {
		return cle2;
	}

	public void setCle2(long cle2) {
		this.cle2 = cle2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cle1 == null) ? 0 : cle1.hashCode());
		result = prime * result + (int) (cle2 ^ (cle2 >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CompositeId other = (CompositeId) obj;
		if (cle1 == null) {
			if (other.cle1 != null) {
				return false;
			}
		}
		else if (!cle1.equals(other.cle1)) {
			return false;
		}
		if (cle2 != other.cle2) {
			return false;
		}
		return true;
	}
}
