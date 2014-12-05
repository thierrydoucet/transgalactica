package org.transgalactica.web.recent.impl;

import org.transgalactica.management.data.people.bo.EmployeEntity;

public class RecentEmployeLabel extends AbstractRecentLabel<Long> {

	private static final long serialVersionUID = 1L;

	protected RecentEmployeLabel(EmployeEntity employe) {
		super(employe.getMatricule(), employe.getNom());
	}
}
