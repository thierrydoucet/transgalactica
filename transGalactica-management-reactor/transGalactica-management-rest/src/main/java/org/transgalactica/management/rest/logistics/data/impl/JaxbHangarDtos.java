package org.transgalactica.management.rest.logistics.data.impl;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;
import static lombok.AccessLevel.PROTECTED;
import static org.transgalactica.management.rest.Namespaces.LOGISTICS_NAMESPACE;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.logistics.data.HangarDto;
import org.transgalactica.management.rest.logistics.data.HangarDtos;

@DataBean
@Getter
@NoArgsConstructor(access = PROTECTED)
@XmlRootElement(name = "hangars")
@XmlType(name = "HangarDtos", namespace = LOGISTICS_NAMESPACE)
@XmlAccessorType(FIELD)
@XmlSeeAlso({ JaxbHangarDto.class })
public class JaxbHangarDtos implements HangarDtos {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "hangar", type = JaxbHangarDto.class)
	private List<HangarDto> hangars = new ArrayList<>();

	public boolean add(HangarDto e) {
		return hangars.add(e);
	}
}
