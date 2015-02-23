package org.transgalactica.management.data.rest.bo.impl;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.rest.bo.EmployeSearchCriteria;

@DataBean
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class BasicEmployeSearchCriteria implements EmployeSearchCriteria {

	private static final long serialVersionUID = 1L;

	private String nomEmploye;

	private LocalDate dateEmbaucheEmployeDebut;

	private LocalDate dateEmbaucheEmployeFin;
}
