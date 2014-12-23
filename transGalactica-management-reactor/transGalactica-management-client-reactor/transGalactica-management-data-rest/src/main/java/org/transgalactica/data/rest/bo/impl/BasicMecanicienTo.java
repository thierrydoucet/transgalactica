package org.transgalactica.data.rest.bo.impl;

import java.util.List;

import org.transgalactica.data.rest.bo.MecanicienTo;
import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean
public class BasicMecanicienTo extends AbstractEmployeTo implements MecanicienTo {

	private static final long serialVersionUID = 1L;

	private List<String> specialites;

	protected BasicMecanicienTo() {
	}

	@Override
	public String getTypeEmploye() {
		return "MECANICIEN";
	}

	@Override
	public List<String> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(List<String> specialites) {
		this.specialites = specialites;
	}
}
