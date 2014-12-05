package org.transgalactica.fwk.test.domaine.bo;

public class DummyTo implements IDummyTo {

	private static final long serialVersionUID = 1L;

	private String dummyAttribut;

	protected DummyTo() {
	}

	@Override
	public String getDummyAttribut() {
		return dummyAttribut;
	}

	@Override
	public void setDummyAttribut(String dummyAttribut) {
		this.dummyAttribut = dummyAttribut;
	}
}
