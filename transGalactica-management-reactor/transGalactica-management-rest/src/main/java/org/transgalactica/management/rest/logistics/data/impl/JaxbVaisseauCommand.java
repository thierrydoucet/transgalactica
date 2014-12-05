package org.transgalactica.management.rest.logistics.data.impl;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.logistics.data.VaisseauCommand;

@DataBean
@XmlRootElement(name = "vaisseauCommand")
@XmlType(name = "VaisseauCommand")
public class JaxbVaisseauCommand implements VaisseauCommand {

	private static final long serialVersionUID = 1L;

	private String immatriculation;

	private String modele;

	private short nombreDePassagers;

	private long capaciteDeFret;

	private int vitesse;

	private int autonomie;

	private Short multiplicateurHyperdrive;

	protected JaxbVaisseauCommand() {
	}

	/*
	 * Accesseurs
	 */

	@Override
	public String getImmatriculation() {
		return immatriculation;
	}

	@Override
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	@Override
	public String getModele() {
		return modele;
	}

	@Override
	public void setModele(String modele) {
		this.modele = modele;
	}

	@Override
	public short getNombreDePassagers() {
		return nombreDePassagers;
	}

	@Override
	public void setNombreDePassagers(short nombreDePassagers) {
		this.nombreDePassagers = nombreDePassagers;
	}

	@Override
	public long getCapaciteDeFret() {
		return capaciteDeFret;
	}

	@Override
	public int getVitesse() {
		return vitesse;
	}

	@Override
	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	@Override
	public int getAutonomie() {
		return autonomie;
	}

	@Override
	public void setAutonomie(int autonomie) {
		this.autonomie = autonomie;
	}

	@Override
	public void setCapaciteDeFret(long capaciteDeFret) {
		this.capaciteDeFret = capaciteDeFret;
	}

	@Override
	public Short getMultiplicateurHyperdrive() {
		return multiplicateurHyperdrive;
	}

	@Override
	public void setMultiplicateurHyperdrive(Short multiplicateurHyperdrive) {
		this.multiplicateurHyperdrive = multiplicateurHyperdrive;
	}
}
