package org.transgalactica.management.data.rest.bo.impl;

import static lombok.AccessLevel.PROTECTED;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.rest.bo.EmployeSummaryTo;

@DataBean("org.transgalactica.management.data.rest.bo.EmployeSummaryTo")
@Data
@EqualsAndHashCode(of = "matricule")
@NoArgsConstructor(access = PROTECTED)
public class BasicEmployeSummaryTo implements EmployeSummaryTo {

	private static final long serialVersionUID = 1L;

	private Long matricule;

	private String nom;

	private String typeEmploye;

	private Date dateEmbauche;
}
