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
import org.transgalactica.management.rest.logistics.data.VaisseauDetailDto;

@DataBean("org.transgalactica.management.rest.logistics.data.VaisseauDetailDto")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@XmlRootElement(name = "vaisseauDetail")
@XmlType(name = "VaisseauDetailDto", namespace = LOGISTICS_NAMESPACE)
@XmlAccessorType(FIELD)
public class JaxbVaisseauDetailDto extends JaxbVaisseauDto implements VaisseauDetailDto {

	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	private Long numeroHangar;

	@XmlElement(required = true)
	private short nombreDePassagers;

	@XmlElement(required = true)
	private long capaciteDeFret;

	@XmlElement(required = true)
	private int vitesse;

	@XmlElement(required = true)
	private int autonomie;

	@XmlElement(required = true)
	private Short multiplicateurHyperdrive;
}
