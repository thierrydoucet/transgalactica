package org.transgalactica.management.rest.hr.data.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.hr.data.EmployeDto;
import org.transgalactica.management.rest.hr.data.EmployeDtos;

@DataBean
@XmlRootElement(name = "employes")
@XmlType(name = "EmployeDtos")
@XmlSeeAlso({ JaxbEmployeDto.class })
public class JaxbEmployeDtos implements EmployeDtos {

	private static final long serialVersionUID = 1L;

	private List<EmployeDto> employes = new ArrayList<>();

	protected JaxbEmployeDtos() {
	}

	@Override
	@XmlElement(type = JaxbEmployeDto.class)
	public List<EmployeDto> getEmployes() {
		return employes;
	}

	public boolean add(EmployeDto e) {
		return employes.add(e);
	}
}
