package org.transgalactica.management.rest.logistics.data.impl;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.logistics.data.HangarDto;

@DataBean("org.transgalactica.management.rest.logistics.data.HangarDto")
@XmlRootElement(name = "hangar")
@XmlType(name = "HangarDto")
public class JaxbHangarDto implements HangarDto {

	private static final long serialVersionUID = 1L;

	private Long numero;

	private String localisation;

	private int nombreEmplacements;

	protected JaxbHangarDto() {
	}

	/*
	 * Accesseur
	 */

	@Override
	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	@Override
	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	@Override
	public int getNombreEmplacements() {
		return nombreEmplacements;
	}

	public void setNombreEmplacements(int nombreEmplacements) {
		this.nombreEmplacements = nombreEmplacements;
	}
}
