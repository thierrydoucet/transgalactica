package org.transgalactica.data.rest.bo.impl;

import org.transgalactica.data.rest.bo.HangarSummaryTo;
import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean("org.transgalactica.data.rest.bo.HangarSummaryTo")
public class BasicHangarSummaryTo implements HangarSummaryTo {

	private static final long serialVersionUID = 1L;

	private Long numero;

	private String localisation;

	private int nombreEmplacements;

	protected BasicHangarSummaryTo() {
	}

	/*
	 * Accesseur
	 */

	/**
	 * @see org.transgalactica.data.rest.bo.HangarSummaryTo#getNumero()
	 */
	@Override
	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	/**
	 * @see org.transgalactica.data.rest.bo.HangarSummaryTo#getLocalisation()
	 */
	@Override
	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	/**
	 * @see org.transgalactica.data.rest.bo.HangarSummaryTo#getNombreEmplacements()
	 */
	@Override
	public int getNombreEmplacements() {
		return nombreEmplacements;
	}

	public void setNombreEmplacements(int nombreEmplacements) {
		this.nombreEmplacements = nombreEmplacements;
	}
}
