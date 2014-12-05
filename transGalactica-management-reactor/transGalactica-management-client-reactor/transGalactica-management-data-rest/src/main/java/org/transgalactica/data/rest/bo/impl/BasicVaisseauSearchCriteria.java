package org.transgalactica.data.rest.bo.impl;

import org.transgalactica.data.rest.bo.VaisseauSearchCriteria;
import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean
public class BasicVaisseauSearchCriteria implements VaisseauSearchCriteria {

	private static final long serialVersionUID = 1L;

	private String immatriculation;

	private String modele;

	private boolean intergalactique;

	protected BasicVaisseauSearchCriteria() {
	}

	/*
	 * Accesseurs
	 */

	/**
	 * @see org.transgalactica.data.rest.bo.impl.VaisseauSearchCriteria#getImmatriculation()
	 */
	@Override
	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * @see org.transgalactica.data.rest.bo.impl.VaisseauSearchCriteria#setImmatriculation(java.lang.String)
	 */
	@Override
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	/**
	 * @see org.transgalactica.data.rest.bo.impl.VaisseauSearchCriteria#getModele()
	 */
	@Override
	public String getModele() {
		return modele;
	}

	/**
	 * @see org.transgalactica.data.rest.bo.impl.VaisseauSearchCriteria#setModele(java.lang.String)
	 */
	@Override
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
	 * @see org.transgalactica.data.rest.bo.impl.VaisseauSearchCriteria#isIntergalactique()
	 */
	@Override
	public boolean isIntergalactique() {
		return intergalactique;
	}

	/**
	 * @see org.transgalactica.data.rest.bo.impl.VaisseauSearchCriteria#setIntergalactique(boolean)
	 */
	@Override
	public void setIntergalactique(boolean intergalactique) {
		this.intergalactique = intergalactique;
	}
}
