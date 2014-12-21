package org.transgalactica.management.rest.logistics.data.impl;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;
import static lombok.AccessLevel.PROTECTED;
import static org.transgalactica.management.rest.Namespaces.LOGISTICS_NAMESPACE;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.logistics.data.HangarDto;

@DataBean("org.transgalactica.management.rest.logistics.data.HangarDto")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@XmlRootElement(name = "hangar")
@XmlType(name = "HangarDto", namespace = LOGISTICS_NAMESPACE)
@XmlAccessorType(FIELD)
public class JaxbHangarDto implements HangarDto {

	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	private Long numero;

	@XmlElement(required = true)
	private String localisation;

	@XmlElement(required = true)
	private int nombreEmplacements;
}
