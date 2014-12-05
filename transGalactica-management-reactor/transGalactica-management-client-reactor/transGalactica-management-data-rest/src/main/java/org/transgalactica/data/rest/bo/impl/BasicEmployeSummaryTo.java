package org.transgalactica.data.rest.bo.impl;

import java.util.Date;

import org.transgalactica.data.rest.bo.EmployeSummaryTo;
import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean("org.transgalactica.data.rest.bo.EmployeSummaryTo")
public class BasicEmployeSummaryTo implements EmployeSummaryTo {

	private static final long serialVersionUID = 1L;

	private Long matricule;

	private String nom;

	private String typeEmploye;

	private Date dateEmbauche;

	protected BasicEmployeSummaryTo() {
	}

	@Override
	public Long getMatricule() {
		return matricule;
	}

	public void setMatricule(Long matricule) {
		this.matricule = matricule;
	}

	@Override
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String getTypeEmploye() {
		return typeEmploye;
	}

	public void setTypeEmploye(String typeEmploye) {
		this.typeEmploye = typeEmploye;
	}

	@Override
	public Date getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(Date dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}
}
