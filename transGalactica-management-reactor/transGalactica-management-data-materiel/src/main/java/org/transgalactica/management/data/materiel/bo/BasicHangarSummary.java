package org.transgalactica.management.data.materiel.bo;

import lombok.Data;

import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean
@Data
public class BasicHangarSummary implements HangarSummary {
	private static final long serialVersionUID = 1L;

	private final Long numeroHangar;

	private final String localisationHangar;

	private final int nombreEmplacementsHangar;

	protected BasicHangarSummary() {
		this(null, null, 0);
	}

	public BasicHangarSummary(Long numeroHangar, String localisationHangar, int nombreEmplacementsHangar) {
		this.numeroHangar = numeroHangar;
		this.localisationHangar = localisationHangar;
		this.nombreEmplacementsHangar = nombreEmplacementsHangar;
	}
}
