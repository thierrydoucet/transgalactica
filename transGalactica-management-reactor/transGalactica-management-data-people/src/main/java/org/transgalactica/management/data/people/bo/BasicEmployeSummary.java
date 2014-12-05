package org.transgalactica.management.data.people.bo;

import java.util.Date;

import lombok.Data;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

@DataBean
@Data
public class BasicEmployeSummary implements EmployeSummary {
	private static final long serialVersionUID = 1L;

	private final Long matriculeEmploye;

	private final String nomEmploye;

	private final Date dateEmbaucheEmploye;

	private final EmployeType typeEmploye;

	protected BasicEmployeSummary() {
		this(null, null, null, null);
	}

	public BasicEmployeSummary(Long matriculeEmploye, String nomEmploye, Date dateEmbaucheEmploye,
			EmployeType typeEmploye) {
		this.matriculeEmploye = matriculeEmploye;
		this.nomEmploye = nomEmploye;
		this.dateEmbaucheEmploye = dateEmbaucheEmploye;
		this.typeEmploye = typeEmploye;
	}
}
