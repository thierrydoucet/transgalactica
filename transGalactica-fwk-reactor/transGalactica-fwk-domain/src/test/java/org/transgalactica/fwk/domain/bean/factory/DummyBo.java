package org.transgalactica.fwk.domain.bean.factory;

import org.transgalactica.fwk.domain.bean.AbstractBo;
import org.transgalactica.fwk.domain.bean.BusinessIdentifier;

public class DummyBo extends AbstractBo implements IDummyBo {
	private static final long serialVersionUID = 1L;

	@BusinessIdentifier
	private Long identifier;

	protected DummyBo() {
	}

	@Override
	public Long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}
}
