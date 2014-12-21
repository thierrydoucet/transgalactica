package org.transgalactica.management.rest.logistics.data.impl;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;
import static lombok.AccessLevel.PROTECTED;
import static org.transgalactica.management.rest.Namespaces.LOGISTICS_NAMESPACE;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.logistics.data.HangarDetailDto;

@DataBean("org.transgalactica.management.rest.logistics.data.HangarDetailDto")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@XmlRootElement(name = "hangarDetail")
@XmlType(name = "HangarDetailDto", namespace = LOGISTICS_NAMESPACE)
@XmlAccessorType(FIELD)
public class JaxbHangarDetailDto extends JaxbHangarDto implements HangarDetailDto {

	private static final long serialVersionUID = 1L;

	@XmlElementWrapper(name = "vaisseaux")
	@XmlElement(name = "vaisseau", type = JaxbHangarDetailDto.JaxbVaisseauDto.class)
	private Set<HangarDetailDto.VaisseauDto> vaisseaux;

	@DataBean
	@Getter
	@Setter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@XmlType(name = "HangarVaisseau", namespace = LOGISTICS_NAMESPACE)
	@XmlAccessorType(FIELD)
	public static class JaxbVaisseauDto implements HangarDetailDto.VaisseauDto {

		private static final long serialVersionUID = 1L;

		@XmlElement(required = true)
		private String immatriculation;

		@XmlElement(required = true)
		private String modele;

		@XmlElement(required = true)
		private short nombreDePassagers;

		@XmlElement(required = true)
		private long capaciteDeFret;

		@XmlElement(required = true)
		private int vitesse;

		@XmlElement(required = true)
		private int autonomie;
	}
}
