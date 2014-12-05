package org.transgalactica.management.data.people.bo.impl;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.people.bo.EmployeSearchCriteria;

@DataBean
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasicEmployeSearchCriteria implements EmployeSearchCriteria {
	private static final long serialVersionUID = 1L;

	private String nomEmploye;

	private Date dateEmbaucheEmployeDebut;

	private Date dateEmbaucheEmployeFin;

	private String immatriculationVaisseau;
}
