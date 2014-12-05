package org.transgalactica.management.rest.logistics.data.impl;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.logistics.data.VaisseauDto;

@DataBean("org.transgalactica.management.rest.logistics.data.VaisseauDto")
@XmlRootElement(name = "vaisseau")
@XmlType(name = "VaisseauDto")
public class JaxbVaisseauDto implements VaisseauDto {

	private static final long serialVersionUID = 1L;

	private String immatriculation;

	private String modele;

	private String localisationHangar;

	protected JaxbVaisseauDto() {
	}

	/*
	 * Accesseurs
	 */

	@Override
	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	@Override
	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	@Override
	public String getLocalisationHangar() {
		return localisationHangar;
	}

	public void setLocalisationHangar(String localisationHangar) {
		this.localisationHangar = localisationHangar;
	}
}