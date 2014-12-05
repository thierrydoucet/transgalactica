package org.transgalactica.management.rest.hr.data.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.hr.data.MecanicienSpecialiteDtos;

@DataBean
@XmlRootElement(name = "mecanicienSpecialites")
@XmlType(name = "MecanicienSpecialiteDtos")
public class JaxbMecanicienSpecialiteDtos implements MecanicienSpecialiteDtos {

	private static final long serialVersionUID = 1L;

	private Set<String> mecanicienSpecialites = new LinkedHashSet<>();

	protected JaxbMecanicienSpecialiteDtos() {
	}

	@Override
	@XmlElement
	public Set<String> getMecanicienSpecialites() {
		return mecanicienSpecialites;
	}

	public void add(String mecanicienSpecialite) {
		mecanicienSpecialites.add(mecanicienSpecialite);
	}
}
