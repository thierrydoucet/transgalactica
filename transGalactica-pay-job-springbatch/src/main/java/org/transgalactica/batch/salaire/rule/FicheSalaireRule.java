package org.transgalactica.batch.salaire.rule;

import java.math.BigDecimal;
import java.util.Date;

import org.transgalactica.management.data.people.bo.EmployeEntity;

public interface FicheSalaireRule {

	BigDecimal calculerSalaireBase(EmployeEntity employe);

	BigDecimal calculerPrimeAnciennete(EmployeEntity employe, Date date);

	BigDecimal calculerPrimeExperience(EmployeEntity employe);

}