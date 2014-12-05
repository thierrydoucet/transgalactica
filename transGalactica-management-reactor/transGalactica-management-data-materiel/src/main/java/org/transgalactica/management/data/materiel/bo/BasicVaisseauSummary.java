package org.transgalactica.management.data.materiel.bo;

import lombok.Data;

import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean
@Data
public class BasicVaisseauSummary implements VaisseauSummary {
	private static final long serialVersionUID = 1L;

	private final String immatriculationVaisseau;

	private final String modeleVaisseau;

	private final String localisationHangar;

	protected BasicVaisseauSummary() {
		this(null, null);
	}

	public BasicVaisseauSummary(String immatriculationVaisseau, String modeleVaisseau) {
		this(immatriculationVaisseau, modeleVaisseau, null);
	}

	public BasicVaisseauSummary(String immatriculationVaisseau, String modeleVaisseau, String localisationHangar) {
		this.immatriculationVaisseau = immatriculationVaisseau;
		this.modeleVaisseau = modeleVaisseau;
		this.localisationHangar = localisationHangar;
	}
}
