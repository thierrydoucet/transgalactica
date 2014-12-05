package org.transgalactica.management.rest.logistics.data.impl;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.logistics.data.VaisseauDetailDto;

@DataBean("org.transgalactica.management.rest.logistics.data.VaisseauDetailDto")
@XmlRootElement(name = "vaisseauDetail")
@XmlType(name = "VaisseauDetailDto")
public class JaxbVaisseauDetailDto extends JaxbVaisseauDto implements VaisseauDetailDto {

	private static final long serialVersionUID = 1L;

	private Long numeroHangar;

	private short nombreDePassagers;

	private long capaciteDeFret;

	private int vitesse;

	private int autonomie;

	private Short multiplicateurHyperdrive;

	protected JaxbVaisseauDetailDto() {
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

	@Override
	public Long getNumeroHangar() {
		return numeroHangar;
	}

	public void setNumeroHangar(Long numeroHangar) {
		this.numeroHangar = numeroHangar;
	}

	@Override
	public Short getMultiplicateurHyperdrive() {
		return this.multiplicateurHyperdrive;
	}

	public void setMultiplicateurHyperdrive(Short multiplicateurHyperdrive) {
		this.multiplicateurHyperdrive = multiplicateurHyperdrive;
	}
}
