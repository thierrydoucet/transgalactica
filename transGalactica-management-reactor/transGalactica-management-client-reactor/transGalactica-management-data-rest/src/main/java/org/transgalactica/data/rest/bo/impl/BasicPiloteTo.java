package org.transgalactica.data.rest.bo.impl;

import org.transgalactica.data.rest.bo.PiloteTo;
import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean
public class BasicPiloteTo extends AbstractEmployeTo implements PiloteTo {

	private static final long serialVersionUID = 1L;

	private int nombreHeuresVol;

	protected BasicPiloteTo() {
	}

	@Override
	public String getTypeEmploye() {
		return "PILOTE";
	}

	@Override
	public int getNombreHeuresVol() {
		return nombreHeuresVol;
	}

	@Override
	public void setNombreHeuresVol(int nombreHeuresVol) {
		this.nombreHeuresVol = nombreHeuresVol;
	}
}
