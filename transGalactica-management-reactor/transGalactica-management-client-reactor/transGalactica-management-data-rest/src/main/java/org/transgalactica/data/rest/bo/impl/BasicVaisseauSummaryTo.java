package org.transgalactica.data.rest.bo.impl;

import org.transgalactica.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean("org.transgalactica.data.rest.bo.VaisseauSummaryTo")
public class BasicVaisseauSummaryTo implements VaisseauSummaryTo {

	private static final long serialVersionUID = 1L;

	private String immatriculation;

	private String modele;

	private String localisationHangar;

	protected BasicVaisseauSummaryTo() {
	}

	/*
	 * Accesseurs
	 */

	/**
	 * @see org.transgalactica.data.rest.bo.VaisseauSummaryTo#getImmatriculation()
	 */
	@Override
	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * @see org.transgalactica.data.rest.bo.VaisseauSummaryTo#setImmatriculation(java.lang.String)
	 */
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	/**
	 * @see org.transgalactica.data.rest.bo.VaisseauSummaryTo#getModele()
	 */
	@Override
	public String getModele() {
		return modele;
	}

	/**
	 * @see org.transgalactica.data.rest.bo.VaisseauSummaryTo#setModele(java.lang.String)
	 */
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
	 * @see org.transgalactica.data.rest.bo.VaisseauSummaryTo#getLocalisationHangar()
	 */
	@Override
	public String getLocalisationHangar() {
		return localisationHangar;
	}

	public void setLocalisationHangar(String localisationHangar) {
		this.localisationHangar = localisationHangar;
	}
}