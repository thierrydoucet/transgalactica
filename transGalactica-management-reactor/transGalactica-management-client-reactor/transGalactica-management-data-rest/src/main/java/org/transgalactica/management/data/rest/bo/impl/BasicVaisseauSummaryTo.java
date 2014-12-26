package org.transgalactica.management.data.rest.bo.impl;

import static lombok.AccessLevel.PROTECTED;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.rest.bo.VaisseauSummaryTo;

@DataBean("org.transgalactica.management.data.rest.bo.VaisseauSummaryTo")
@Data
@EqualsAndHashCode(of = "immatriculation")
@NoArgsConstructor(access = PROTECTED)
public class BasicVaisseauSummaryTo implements VaisseauSummaryTo {

	private static final long serialVersionUID = 1L;

	private String immatriculation;

	private String modele;

	private String localisationHangar;
}