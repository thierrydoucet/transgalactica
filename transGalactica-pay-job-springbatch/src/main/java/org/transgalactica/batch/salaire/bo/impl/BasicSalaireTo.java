package org.transgalactica.batch.salaire.bo.impl;

import static lombok.AccessLevel.PROTECTED;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

@DataBean("org.transgalactica.batch.salaire.bo.SalaireTo")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class BasicSalaireTo implements SalaireTo {
	private static final long serialVersionUID = 1L;

	private String nomEmploye;

	private Date dateEmbaucheEmploye;

	private EmployeType typeEmploye;

	private BigDecimal salaireBase;

	private BigDecimal primeAnciennete;

	private BigDecimal primeExperience;

	private BigDecimal salaire;
}
