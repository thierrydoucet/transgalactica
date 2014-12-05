package org.transgalactica.fwk.test.domaine.bo;

import org.transgalactica.fwk.domain.bean.AbstractBo;
import org.transgalactica.fwk.domain.bean.BusinessIdentifier;

public class DummyBo extends AbstractBo implements IDummyBo {

	private static final long serialVersionUID = 1L;

	@BusinessIdentifier
	private Long businessIdentifier;

	private String dummyAttribut;

	protected DummyBo() {
	}

	protected DummyBo(Long bi) {
		this.businessIdentifier = bi;
	}

	@Override
	public String getDummyAttribut() {
		return dummyAttribut;
	}

	@Override
	public void setDummyAttribut(String dummyAttribut) {
		this.dummyAttribut = dummyAttribut;
	}

	@Override
	public Long getBusinessIdentifier() {
		return businessIdentifier;
	}

	@Override
	public void setBusinessIdentifier(Long businessIdentifier) {
		this.businessIdentifier = businessIdentifier;
	}
}
