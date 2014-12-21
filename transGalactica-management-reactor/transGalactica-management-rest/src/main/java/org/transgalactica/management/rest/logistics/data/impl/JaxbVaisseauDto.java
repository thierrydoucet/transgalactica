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
import org.transgalactica.management.rest.logistics.data.VaisseauDto;

@DataBean("org.transgalactica.management.rest.logistics.data.VaisseauDto")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@XmlRootElement(name = "vaisseau")
@XmlType(name = "VaisseauDto", namespace = LOGISTICS_NAMESPACE)
@XmlAccessorType(FIELD)
public class JaxbVaisseauDto implements VaisseauDto {

	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	private String immatriculation;

	@XmlElement(required = true)
	private String modele;

	@XmlElement(required = true)
	private String localisationHangar;
}