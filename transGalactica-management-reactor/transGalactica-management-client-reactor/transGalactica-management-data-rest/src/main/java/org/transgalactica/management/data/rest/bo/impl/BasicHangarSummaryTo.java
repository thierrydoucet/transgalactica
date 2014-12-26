package org.transgalactica.management.data.rest.bo.impl;

import static lombok.AccessLevel.PROTECTED;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.rest.bo.HangarSummaryTo;

@DataBean("org.transgalactica.management.data.rest.bo.HangarSummaryTo")
@Data
@EqualsAndHashCode(of = "numero")
@NoArgsConstructor(access = PROTECTED)
public class BasicHangarSummaryTo implements HangarSummaryTo {

	private static final long serialVersionUID = 1L;

	private Long numero;

	private String localisation;

	private int nombreEmplacements;
}
