package org.transgalactica.management.ws.logistics.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean
public class BasicHangarDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long numero;

	private String localisation;

	private int nombreEmplacements;

	public BasicHangarDto() {
	}

	/*
	 * Accesseurs
	 */

	@XmlElement(name = "numero", nillable = false, required = true)
	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	@XmlElement(name = "localisation", nillable = false)
	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	@XmlElement(name = "nombreEmplacements")
	public int getNombreEmplacements() {
		return nombreEmplacements;
	}

	public void setNombreEmplacements(int nombreEmplacements) {
		this.nombreEmplacements = nombreEmplacements;
	}
}
