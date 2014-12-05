package org.transgalactica.management.data.materiel.bo.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;

@DataBean
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasicHangarSearchCriteria implements HangarSearchCriteria {
	private static final long serialVersionUID = 1L;

	private String localisationHangar;
}
