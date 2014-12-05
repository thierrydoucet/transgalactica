package org.transgalactica.data.rest.bo.impl;

import java.util.Date;

import org.transgalactica.data.rest.bo.EmployeSearchCriteria;
import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean
public class BasicEmployeSearchCriteria implements EmployeSearchCriteria {

	private static final long serialVersionUID = 1L;

	private String nomEmploye;

	private Date dateEmbaucheEmployeDebut;

	private Date dateEmbaucheEmployeFin;

	protected BasicEmployeSearchCriteria() {
	}

	@Override
	public String getNomEmploye() {
		return nomEmploye;
	}

	@Override
	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	@Override
	public Date getDateEmbaucheEmployeDebut() {
		return dateEmbaucheEmployeDebut;
	}

	@Override
	public void setDateEmbaucheEmployeDebut(Date dateEmbaucheEmployeDebut) {
		this.dateEmbaucheEmployeDebut = dateEmbaucheEmployeDebut;
	}

	@Override
	public Date getDateEmbaucheEmployeFin() {
		return dateEmbaucheEmployeFin;
	}

	@Override
	public void setDateEmbaucheEmployeFin(Date dateEmbaucheEmployeFin) {
		this.dateEmbaucheEmployeFin = dateEmbaucheEmployeFin;
	}

}
