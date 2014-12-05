package org.transgalactica.management.rest.hr.data.impl;

import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.hr.data.PiloteCommand;

@DataBean
@XmlType(name = "PiloteCommand")
public class JaxbPiloteCommand extends JaxbEmployeCommand implements PiloteCommand {

	private static final long serialVersionUID = 1L;

	private int nombreHeuresVol;

	protected JaxbPiloteCommand() {
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
