package org.transgalactica.management.rest.hr.data.impl;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.hr.data.PiloteDetailDto;

@DataBean("org.transgalactica.management.rest.hr.data.PiloteDetailDto")
@XmlRootElement(name = "employeDetail")
@XmlType(name = "PiloteDetailDto")
public class JaxbPiloteDetailDto extends JaxbEmployeDto implements PiloteDetailDto {

	private static final long serialVersionUID = 1L;

	private Set<org.transgalactica.management.rest.hr.data.VaisseauDto> vaisseaux;

	private int nombreHeuresVol;

	protected JaxbPiloteDetailDto() {
	}

	@XmlElement(type = JaxbVaisseauDto.class)
	public Set<org.transgalactica.management.rest.hr.data.VaisseauDto> getVaisseaux() {
		return vaisseaux;
	}

	public void setVaisseaux(Set<org.transgalactica.management.rest.hr.data.VaisseauDto> vaisseaux) {
		this.vaisseaux = vaisseaux;
	}

	public int getNombreHeuresVol() {
		return nombreHeuresVol;
	}

	public void setNombreHeuresVol(int nombreHeuresVol) {
		this.nombreHeuresVol = nombreHeuresVol;
	}
}
