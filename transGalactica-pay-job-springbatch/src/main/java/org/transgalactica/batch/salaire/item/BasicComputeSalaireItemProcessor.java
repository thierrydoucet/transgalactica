package org.transgalactica.batch.salaire.item;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.inject.Inject;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Required;
import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.batch.salaire.mapper.SalaireMapper;
import org.transgalactica.batch.salaire.rule.FicheSalaireRule;
import org.transgalactica.management.data.people.bo.EmployeEntity;

public class BasicComputeSalaireItemProcessor implements ItemProcessor<EmployeEntity, SalaireTo> {

	@Inject
	private SalaireMapper salaireMapper;

	@Inject
	private FicheSalaireRule ficheSalaireRule;

	private LocalDate dateCalcul;

	protected BasicComputeSalaireItemProcessor() {
	}

	@Override
	public SalaireTo process(EmployeEntity item) {
		SalaireTo salaireTo = salaireMapper.mapEmployeInformation(item);

		BigDecimal base = ficheSalaireRule.calculerSalaireBase(item);
		BigDecimal anciennete = ficheSalaireRule.calculerPrimeAnciennete(item, dateCalcul);
		BigDecimal experience = ficheSalaireRule.calculerPrimeExperience(item);

		salaireTo.setSalaireBase(base);
		salaireTo.setPrimeAnciennete(anciennete);
		salaireTo.setPrimeExperience(experience);
		salaireTo.setSalaire(base.add(anciennete).add(experience));

		return salaireTo;
	}

	@Required
	public void setDateCalcul(LocalDate dateCalcul) {
		this.dateCalcul = dateCalcul;
	}
}
