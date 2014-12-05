package org.transgalactica.web.recent.impl;

import org.transgalactica.management.data.materiel.bo.VaisseauEntity;

public class RecentVaisseauLabel extends AbstractRecentLabel<String> {

	private static final long serialVersionUID = 1L;

	protected RecentVaisseauLabel(VaisseauEntity vaisseau) {
		super(vaisseau.getImmatriculation(), vaisseau.getImmatriculation());
	}
}
