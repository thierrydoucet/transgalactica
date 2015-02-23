package org.transgalactica.management.rest.hr.data.impl;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;
import static lombok.AccessLevel.PROTECTED;
import static org.transgalactica.management.rest.Namespaces.HR_NAMESPACE;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.referentiel.bo.EmployeType;
import org.transgalactica.management.rest.hr.data.EmployeDto;

@DataBean("org.transgalactica.management.rest.hr.data.EmployeDto")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@XmlRootElement(name = "employe")
@XmlType(name = "EmployeDto", namespace = HR_NAMESPACE)
@XmlAccessorType(FIELD)
public class JaxbEmployeDto implements EmployeDto {

	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	private Long matricule;

	@XmlElement(required = true)
	private String nom;

	@XmlElement(required = true)
	@XmlJavaTypeAdapter(type = LocalDate.class, value = XSDLocalDateMarshaller.class)
	private LocalDate dateEmbauche;

	@XmlElement(required = true)
	private EmployeType typeEmploye;
}
