package org.transgalactica.fwk.domain.bean;

import org.transgalactica.fwk.domain.bean.AbstractBo;
import org.transgalactica.fwk.domain.bean.BusinessIdentifier;

public class CompositeIdBo extends AbstractBo {

	private static final long serialVersionUID = 1L;

	@BusinessIdentifier(name = "id_1")
	private CompositeId compositeId;

	private String otherId;

	private String otherField;

	public CompositeIdBo() {
	}

	public CompositeIdBo(CompositeId identifier, String otherId, String otherField) {
		setCompositeId(identifier);
		setOtherId(otherId);
		setOtherField(otherField);
	}

	public CompositeId getCompositeId() {
		return compositeId;
	}

	public void setCompositeId(CompositeId compositeId) {
		this.compositeId = compositeId;
	}

	public String getOtherField() {
		return otherField;
	}

	public void setOtherField(String otherField) {
		this.otherField = otherField;
	}

	@BusinessIdentifier(name = "id_2")
	public String getOtherId() {
		return otherId;
	}

	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}

	@BusinessIdentifier
	protected String getId3() {
		return "valeur id 3";
	}
}
