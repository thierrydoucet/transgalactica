package org.transgalactica.management.rest.hr.data.impl;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.hr.data.MecanicienDetailDto;

@DataBean("org.transgalactica.management.rest.hr.data.MecanicienDetailDto")
@XmlRootElement(name = "employeDetail")
@XmlType(name = "MecanicienDetailDto")
public class JaxbMecanicienDetailDto extends JaxbEmployeDto implements MecanicienDetailDto {

	private static final long serialVersionUID = 1L;

	private Set<org.transgalactica.management.rest.hr.data.VaisseauDto> vaisseaux;

	private Set<String> specialites;

	protected JaxbMecanicienDetailDto() {
	}

	@XmlElement(type = JaxbVaisseauDto.class)
	public Set<org.transgalactica.management.rest.hr.data.VaisseauDto> getVaisseaux() {
		return vaisseaux;
	}

	public void setVaisseaux(Set<org.transgalactica.management.rest.hr.data.VaisseauDto> vaisseaux) {
		this.vaisseaux = vaisseaux;
	}

	public Set<String> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(Set<String> specialites) {
		this.specialites = specialites;
	}
}
