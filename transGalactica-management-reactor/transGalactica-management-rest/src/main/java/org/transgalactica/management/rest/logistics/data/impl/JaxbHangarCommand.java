package org.transgalactica.management.rest.logistics.data.impl;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.logistics.data.HangarCommand;

@DataBean
@XmlRootElement(name = "hangarCommand")
@XmlType(name = "HangarCommand")
public class JaxbHangarCommand implements HangarCommand {

	private static final long serialVersionUID = 1L;

	private String localisation;

	private Integer nombreEmplacements;

	protected JaxbHangarCommand() {
	}

	@Override
	public String getLocalisation() {
		return localisation;
	}

	@Override
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	@Override
	public Integer getNombreEmplacements() {
		return nombreEmplacements;
	}

	@Override
	public void setNombreEmplacements(Integer nombreEmplacements) {
		this.nombreEmplacements = nombreEmplacements;
	}
}
