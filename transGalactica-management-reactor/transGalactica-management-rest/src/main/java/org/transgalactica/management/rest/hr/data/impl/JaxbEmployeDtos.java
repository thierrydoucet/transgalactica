package org.transgalactica.management.rest.hr.data.impl;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;
import static org.transgalactica.management.rest.Namespaces.HR_NAMESPACE;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.hr.data.EmployeDto;
import org.transgalactica.management.rest.hr.data.EmployeDtos;

@DataBean
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@XmlRootElement(name = "employes")
@XmlType(name = "EmployeDtos", namespace = HR_NAMESPACE)
@XmlSeeAlso({ JaxbEmployeDto.class })
@XmlAccessorType(FIELD)
public class JaxbEmployeDtos implements EmployeDtos {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "employe", type = JaxbEmployeDto.class)
	private List<EmployeDto> employes = new ArrayList<>();
	
	public boolean add(EmployeDto e) {
		return employes.add(e);
	}
}
