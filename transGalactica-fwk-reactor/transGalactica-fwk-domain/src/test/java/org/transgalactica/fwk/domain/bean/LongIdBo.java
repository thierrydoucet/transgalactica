package org.transgalactica.fwk.domain.bean;

import org.transgalactica.fwk.domain.bean.AbstractBo;
import org.transgalactica.fwk.domain.bean.BusinessIdentifier;

public class LongIdBo extends AbstractBo {
	private static final long serialVersionUID = 1L;

	@BusinessIdentifier
	private Long longId;

	private String champ1LongIdBo;

	private String champ2LongIdBo;

	public LongIdBo() {
	}

	public LongIdBo(Long identifier) {
		setLongId(identifier);
	}

	public Long getLongId() {
		return longId;
	}

	public void setLongId(Long longId) {
		this.longId = longId;
	}

	public String getChamp1LongIdBo() {
		return champ1LongIdBo;
	}

	public void setChamp1LongIdBo(String champ1LongIdBo) {
		this.champ1LongIdBo = champ1LongIdBo;
	}

	public String getChamp2LongIdBo() {
		return champ2LongIdBo;
	}

	public void setChamp2LongIdBo(String champ2LongIdBo) {
		this.champ2LongIdBo = champ2LongIdBo;
	}
}
