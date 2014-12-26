package org.transgalactica.management.data.rest.bo.impl;

import static lombok.AccessLevel.PROTECTED;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.rest.bo.HangarSearchCriteria;

@DataBean
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class BasicHangarSearchCriteria implements HangarSearchCriteria {

	private static final long serialVersionUID = 1L;

	private String localisationHangar;
}
