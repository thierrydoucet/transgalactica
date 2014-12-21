package org.transgalactica.management.rest.hr.data.impl;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;
import static lombok.AccessLevel.PROTECTED;
import static org.transgalactica.management.rest.Namespaces.HR_NAMESPACE;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.hr.data.EmployeTypeDtos;

@DataBean
@Getter
@NoArgsConstructor(access = PROTECTED)
@XmlRootElement(name = "employeTypes")
@XmlType(name = "EmployeTypes", namespace = HR_NAMESPACE)
@XmlAccessorType(FIELD)
public class JaxbEmployeTypeDtos implements EmployeTypeDtos {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "employeType")
	private Set<String> employeTypes = new LinkedHashSet<>();

	public void add(String employeType) {
		employeTypes.add(employeType);
	}
}
