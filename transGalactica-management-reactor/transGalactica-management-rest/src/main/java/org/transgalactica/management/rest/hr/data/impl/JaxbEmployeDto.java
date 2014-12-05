package org.transgalactica.management.rest.hr.data.impl;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.referentiel.bo.EmployeType;
import org.transgalactica.management.rest.hr.data.EmployeDto;

@DataBean("org.transgalactica.management.rest.hr.data.EmployeDto")
@XmlRootElement(name = "employe")
@XmlType(name = "EmployeDto")
public class JaxbEmployeDto implements EmployeDto {

	private static final long serialVersionUID = 1L;

	private Long matricule;

	private String nom;

	private Date dateEmbauche;

	private EmployeType typeEmploye;

	protected JaxbEmployeDto() {
	}

	public Long getMatricule() {
		return matricule;
	}

	public void setMatricule(Long matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(Date dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	public EmployeType getTypeEmploye() {
		return typeEmploye;
	}

	public void setTypeEmploye(EmployeType typeEmploye) {
		this.typeEmploye = typeEmploye;
	}
}
