package org.transgalactica.management.rest.hr.data.impl;

import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.hr.data.VaisseauDto;

@DataBean
@XmlType(name = "EmployeVaisseau")
public class JaxbVaisseauDto implements VaisseauDto {

	private static final long serialVersionUID = 1L;

	private String immatriculation;

	private String modele;

	private short nombreDePassagers;

	private long capaciteDeFret;

	private int vitesse;

	private int autonomie;

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
	public short getNombreDePassagers() {
		return nombreDePassagers;
	}

	public void setNombreDePassagers(short nombreDePassagers) {
		this.nombreDePassagers = nombreDePassagers;
	}

	@Override
	public long getCapaciteDeFret() {
		return capaciteDeFret;
	}

	public void setCapaciteDeFret(long capaciteDeFret) {
		this.capaciteDeFret = capaciteDeFret;
	}

	@Override
	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	@Override
	public int getAutonomie() {
		return autonomie;
	}

	public void setAutonomie(int autonomie) {
		this.autonomie = autonomie;
	}
}