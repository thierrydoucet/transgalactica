package org.transgalactica.data.rest.bo.impl;

import org.transgalactica.data.rest.bo.HangarSearchCriteria;
import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean
public class BasicHangarSearchCriteria implements HangarSearchCriteria {

	private static final long serialVersionUID = 1L;

	private String localisationHangar;

	protected BasicHangarSearchCriteria() {
	}

	/*
	 * Accesseurs
	 */

	/**
	 * @see org.transgalactica.data.rest.bo.HangarSearchCriteria#getLocalisationHangar()
	 */
	@Override
	public String getLocalisationHangar() {
		return localisationHangar;
	}

	/**
	 * @see org.transgalactica.data.rest.bo.HangarSearchCriteria#setLocalisationHangar(java.lang.String)
	 */
	@Override
	public void setLocalisationHangar(String localisationHangar) {
		this.localisationHangar = localisationHangar;
	}
}
