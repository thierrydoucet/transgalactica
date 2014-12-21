package org.transgalactica.management.rest.hr.data.impl;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;
import static lombok.AccessLevel.PROTECTED;
import static org.transgalactica.management.rest.Namespaces.HR_NAMESPACE;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.hr.data.PiloteDetailDto;

@DataBean("org.transgalactica.management.rest.hr.data.PiloteDetailDto")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@XmlRootElement(name = "employeDetail")
@XmlType(name = "PiloteDetailDto", namespace = HR_NAMESPACE)
@XmlAccessorType(FIELD)
public class JaxbPiloteDetailDto extends JaxbEmployeDto implements PiloteDetailDto {

	private static final long serialVersionUID = 1L;

	@XmlElementWrapper(name = "vaisseaux")
	@XmlElement(name = "vaisseau", type = JaxbVaisseauDto.class)
	private Set<org.transgalactica.management.rest.hr.data.VaisseauDto> vaisseaux;

	@XmlElement(required = true)
	private int nombreHeuresVol;
}
