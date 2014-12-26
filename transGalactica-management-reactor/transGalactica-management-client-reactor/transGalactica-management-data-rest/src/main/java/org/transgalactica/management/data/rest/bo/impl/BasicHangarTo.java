package org.transgalactica.management.data.rest.bo.impl;

import static lombok.AccessLevel.PROTECTED;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.rest.bo.HangarTo;

@DataBean
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class BasicHangarTo extends BasicHangarSummaryTo implements HangarTo {

	private static final long serialVersionUID = 1L;

	private List<HangarTo.VaisseauTo> vaisseaux;

	@DataBean
	@Data
	@EqualsAndHashCode(of = "immatriculation")
	@NoArgsConstructor(access = PROTECTED)
	public static class BasicVaisseauTo implements HangarTo.VaisseauTo {

		private static final long serialVersionUID = 1L;

		private String immatriculation;

		private String modele;

		private short nombreDePassagers;

		private long capaciteDeFret;

		private int vitesse;

		private int autonomie;
	}
}
