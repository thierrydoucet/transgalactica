package org.transgalactica.management.data.rest.bo.impl;

import static lombok.AccessLevel.PROTECTED;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.rest.bo.VaisseauSearchCriteria;

@DataBean
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class BasicVaisseauSearchCriteria implements VaisseauSearchCriteria {

	private static final long serialVersionUID = 1L;

	private String immatriculation;

	private String modele;

	private boolean intergalactique;
}
