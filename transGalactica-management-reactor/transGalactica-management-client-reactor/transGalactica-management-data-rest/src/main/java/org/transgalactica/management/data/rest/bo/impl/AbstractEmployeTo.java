package org.transgalactica.management.data.rest.bo.impl;

import static lombok.AccessLevel.PROTECTED;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.rest.bo.EmployeTo;

@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public abstract class AbstractEmployeTo extends BasicEmployeSummaryTo implements EmployeTo {

	private static final long serialVersionUID = 1L;

	private List<VaisseauTo> vaisseaux;

	@DataBean
	@Data
	@EqualsAndHashCode(of = "immatriculation")
	@NoArgsConstructor(access = PROTECTED)
	public static class BasicVaisseauTo implements EmployeTo.VaisseauTo {

		private static final long serialVersionUID = 1L;

		private String immatriculation;

		private String modele;

		private short nombreDePassagers;

		private long capaciteDeFret;

		private int vitesse;

		private int autonomie;
	}
}
