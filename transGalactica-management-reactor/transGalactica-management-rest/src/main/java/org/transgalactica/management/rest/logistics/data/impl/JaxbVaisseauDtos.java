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
import org.transgalactica.management.rest.logistics.data.VaisseauDto;
import org.transgalactica.management.rest.logistics.data.VaisseauDtos;

@DataBean
@Getter
@NoArgsConstructor(access = PROTECTED)
@XmlRootElement(name = "vaisseaux")
@XmlType(name = "VaisseauDtos", namespace = LOGISTICS_NAMESPACE)
@XmlAccessorType(FIELD)
@XmlSeeAlso({ JaxbVaisseauDto.class })
public class JaxbVaisseauDtos implements VaisseauDtos {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "vaisseau", type = JaxbVaisseauDto.class)
	private List<VaisseauDto> vaisseaux = new ArrayList<>();

	public boolean add(VaisseauDto e) {
		return vaisseaux.add(e);
	}
}