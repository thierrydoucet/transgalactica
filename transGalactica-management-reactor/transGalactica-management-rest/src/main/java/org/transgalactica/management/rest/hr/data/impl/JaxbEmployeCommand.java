package org.transgalactica.management.rest.hr.data.impl;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.hr.data.EmployeCommand;

@DataBean
@XmlRootElement(name = "employeCommand")
@XmlType(name = "EmployeCommand")
public class JaxbEmployeCommand implements EmployeCommand {

	private static final long serialVersionUID = 1L;

	private String nom;

	private Date dateEmbauche;

	protected JaxbEmployeCommand() {
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
}
