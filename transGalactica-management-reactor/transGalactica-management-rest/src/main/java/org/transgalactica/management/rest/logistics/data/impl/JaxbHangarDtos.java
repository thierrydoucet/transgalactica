package org.transgalactica.management.rest.logistics.data.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.logistics.data.HangarDto;
import org.transgalactica.management.rest.logistics.data.HangarDtos;

@DataBean
@XmlRootElement(name = "hangars")
@XmlType(name = "HangarDtos")
@XmlSeeAlso({ JaxbHangarDto.class })
public class JaxbHangarDtos implements HangarDtos {

	private static final long serialVersionUID = 1L;

	private List<HangarDto> hangars = new ArrayList<>();

	protected JaxbHangarDtos() {
	}

	/**
	 * @see org.transgalactica.management.rest.logistics.data.HangarDtos#getHangarDtos()
	 */
	@Override
	@XmlElement(type = JaxbHangarDto.class)
	public List<HangarDto> getHangars() {
		return hangars;
	}

	public boolean add(HangarDto e) {
		return hangars.add(e);
	}

}
