package org.transgalactica.web.recent.impl;

import org.transgalactica.management.data.materiel.bo.HangarEntity;

public class RecentHangarLabel extends AbstractRecentLabel<Long> {

	private static final long serialVersionUID = 1L;

	protected RecentHangarLabel(HangarEntity hangar) {
		super(hangar.getNumero(), hangar.getNumero(), hangar.getLocalisation());
	}
}
