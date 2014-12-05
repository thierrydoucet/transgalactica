package org.transgalactica.management.rest.hr.data.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.hr.data.EmployeTypeDtos;

@DataBean
@XmlRootElement(name = "employeTypes")
@XmlType(name = "EmployeTypeDtos")
public class JaxbEmployeTypeDtos implements EmployeTypeDtos {

	private static final long serialVersionUID = 1L;

	private Set<String> employeTypes = new LinkedHashSet<>();

	protected JaxbEmployeTypeDtos() {
	}

	@Override
	@XmlElement
	public Set<String> getEmployeTypes() {
		return employeTypes;
	}

	public void add(String employeType) {
		employeTypes.add(employeType);
	}
}
