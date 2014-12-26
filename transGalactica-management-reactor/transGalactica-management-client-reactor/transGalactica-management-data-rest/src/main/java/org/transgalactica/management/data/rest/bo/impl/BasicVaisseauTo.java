package org.transgalactica.management.data.rest.bo.impl;

import static lombok.AccessLevel.PROTECTED;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.rest.bo.VaisseauTo;

@DataBean
@Data
@EqualsAndHashCode(of = "numeroHangar", callSuper = false)
@NoArgsConstructor(access = PROTECTED)
public class BasicVaisseauTo extends BasicVaisseauSummaryTo implements VaisseauTo {

	private static final long serialVersionUID = 1L;

	private Long numeroHangar;

	private short nombreDePassagers;

	private long capaciteDeFret;

	private int vitesse;

	private int autonomie;

	private Short multiplicateurHyperdrive;
}
