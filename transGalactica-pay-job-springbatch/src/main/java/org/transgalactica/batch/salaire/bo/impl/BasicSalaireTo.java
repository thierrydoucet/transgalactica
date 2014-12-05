package org.transgalactica.batch.salaire.bo.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

@DataBean("org.transgalactica.batch.salaire.bo.SalaireTo")
public class BasicSalaireTo implements SalaireTo {
	private static final long serialVersionUID = 1L;

	private String nomEmploye;

	private Date dateEmbaucheEmploye;

	private EmployeType typeEmploye;

	private BigDecimal salaireBase;

	private BigDecimal primeAnciennete;

	private BigDecimal primeExperience;

	private BigDecimal salaire;

	protected BasicSalaireTo() {
	}

	/*
	 * Accesseurs
	 */

	@Override
	public String getNomEmploye() {
		return nomEmploye;
	}

	@Override
	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	@Override
	public Date getDateEmbaucheEmploye() {
		return dateEmbaucheEmploye;
	}

	@Override
	public void setDateEmbaucheEmploye(Date dateEmbaucheEmploye) {
		this.dateEmbaucheEmploye = dateEmbaucheEmploye;
	}

	@Override
	public EmployeType getTypeEmploye() {
		return typeEmploye;
	}

	@Override
	public void setTypeEmploye(EmployeType typeEmploye) {
		this.typeEmploye = typeEmploye;
	}

	@Override
	public BigDecimal getSalaireBase() {
		return salaireBase;
	}

	@Override
	public void setSalaireBase(BigDecimal salaireBase) {
		this.salaireBase = salaireBase;
	}

	@Override
	public BigDecimal getPrimeAnciennete() {
		return primeAnciennete;
	}

	@Override
	public void setPrimeAnciennete(BigDecimal primeAnciennete) {
		this.primeAnciennete = primeAnciennete;
	}

	@Override
	public BigDecimal getPrimeExperience() {
		return primeExperience;
	}

	@Override
	public void setPrimeExperience(BigDecimal primeExperience) {
		this.primeExperience = primeExperience;
	}

	@Override
	public BigDecimal getSalaire() {
		return salaire;
	}

	@Override
	public void setSalaire(BigDecimal salaire) {
		this.salaire = salaire;
	}
}
