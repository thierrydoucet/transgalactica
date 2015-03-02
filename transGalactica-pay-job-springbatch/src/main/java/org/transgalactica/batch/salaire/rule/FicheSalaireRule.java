package org.transgalactica.batch.salaire.rule;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.transgalactica.management.data.people.bo.EmployeEntity;

public interface FicheSalaireRule {

	BigDecimal calculerSalaireBase(EmployeEntity employe);

	BigDecimal calculerPrimeAnciennete(EmployeEntity employe, LocalDate date);

	BigDecimal calculerPrimeExperience(EmployeEntity employe);
}