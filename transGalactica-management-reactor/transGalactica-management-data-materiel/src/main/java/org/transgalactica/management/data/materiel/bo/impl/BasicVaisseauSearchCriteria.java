package org.transgalactica.management.data.materiel.bo.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.validation.VaisseauSearchCriteriaConstraint;

@DataBean
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@VaisseauSearchCriteriaConstraint
public class BasicVaisseauSearchCriteria implements VaisseauSearchCriteria {
	private static final long serialVersionUID = 1L;

	private String immatriculation;

	private String modele;

	private boolean intergalactique;
}
