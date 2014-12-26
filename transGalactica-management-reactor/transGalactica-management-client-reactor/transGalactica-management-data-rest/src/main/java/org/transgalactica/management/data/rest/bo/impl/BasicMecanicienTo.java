package org.transgalactica.management.data.rest.bo.impl;

import static lombok.AccessLevel.PROTECTED;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.rest.bo.MecanicienTo;

@DataBean
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class BasicMecanicienTo extends AbstractEmployeTo implements MecanicienTo {

	private static final long serialVersionUID = 1L;

	private List<String> specialites;

	@Override
	public String getTypeEmploye() {
		return "MECANICIEN";
	}
}
