package org.transgalactica.web.vaisseau.model.impl;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean
public class VaisseauCommand implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 30)
	private String immatriculation;

	@NotBlank
	@Size(max = 30)
	private String modele;

	@NotNull
	@Min(0)
	private short nombreDePassagers;

	@NotNull
	@Min(0)
	private long capaciteDeFret;

	@NotNull
	@Min(0)
	private int vitesse;

	@NotNull
	@Min(0)
	private int autonomie;

	@Min(0)
	private Short multiplicateurHyperdrive;

	protected VaisseauCommand() {
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public short getNombreDePassagers() {
		return nombreDePassagers;
	}

	public void setNombreDePassagers(short nombreDePassagers) {
		this.nombreDePassagers = nombreDePassagers;
	}

	public long getCapaciteDeFret() {
		return capaciteDeFret;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getAutonomie() {
		return autonomie;
	}

	public void setAutonomie(int autonomie) {
		this.autonomie = autonomie;
	}

	public void setCapaciteDeFret(long capaciteDeFret) {
		this.capaciteDeFret = capaciteDeFret;
	}

	public Short getMultiplicateurHyperdrive() {
		return multiplicateurHyperdrive;
	}

	public void setMultiplicateurHyperdrive(Short multiplicateurHyperdrive) {
		this.multiplicateurHyperdrive = multiplicateurHyperdrive;
	}
}
