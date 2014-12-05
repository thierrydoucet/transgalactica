package org.transgalactica.management.rest.logistics.data.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.logistics.data.VaisseauDto;
import org.transgalactica.management.rest.logistics.data.VaisseauDtos;

@DataBean
@XmlRootElement(name = "vaisseaux")
@XmlType(name = "VaisseauDtos")
@XmlSeeAlso({ JaxbVaisseauDto.class })
public class JaxbVaisseauDtos implements VaisseauDtos {

	private static final long serialVersionUID = 1L;

	private List<VaisseauDto> vaisseaux = new ArrayList<>();

	protected JaxbVaisseauDtos() {
	}

	/**
	 * @see org.transgalactica.management.rest.logistics.data.VaisseauDtos#getVaisseaux()
	 */
	@Override
	@XmlElement(type = JaxbVaisseauDto.class)
	public List<VaisseauDto> getVaisseaux() {
		return vaisseaux;
	}

	public boolean add(VaisseauDto e) {
		return vaisseaux.add(e);
	}
}