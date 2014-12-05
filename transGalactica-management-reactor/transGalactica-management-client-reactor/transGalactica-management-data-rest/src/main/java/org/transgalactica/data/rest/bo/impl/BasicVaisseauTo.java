package org.transgalactica.data.rest.bo.impl;

import org.transgalactica.data.rest.bo.VaisseauTo;
import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean
public class BasicVaisseauTo extends BasicVaisseauSummaryTo implements VaisseauTo {

	private static final long serialVersionUID = 1L;

	private Long numeroHangar;

	private short nombreDePassagers;

	private long capaciteDeFret;

	private int vitesse;

	private int autonomie;

	private Short multiplicateurHyperdrive;

	protected BasicVaisseauTo() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public short getNombreDePassagers() {
		return nombreDePassagers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNombreDePassagers(short nombreDePassagers) {
		this.nombreDePassagers = nombreDePassagers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getCapaciteDeFret() {
		return capaciteDeFret;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCapaciteDeFret(long capaciteDeFret) {
		this.capaciteDeFret = capaciteDeFret;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getVitesse() {
		return vitesse;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getAutonomie() {
		return autonomie;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAutonomie(int autonomie) {
		this.autonomie = autonomie;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getNumeroHangar() {
		return numeroHangar;
	}

	public void setNumeroHangar(Long numeroHangar) {
		this.numeroHangar = numeroHangar;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Short getMultiplicateurHyperdrive() {
		return this.multiplicateurHyperdrive;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMultiplicateurHyperdrive(Short multiplicateurHyperdrive) {
		this.multiplicateurHyperdrive = multiplicateurHyperdrive;
	}
}
