package org.transgalactica.batch.salaire.item.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Required;
import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.batch.salaire.mapper.SalaireMapper;
import org.transgalactica.batch.salaire.rule.FicheSalaireRule;
import org.transgalactica.management.data.people.bo.EmployeEntity;

public class BasicSalaireMapperItemProcessor implements ItemProcessor<EmployeEntity, SalaireTo> {

	@Inject
	private SalaireMapper salaireMapper;

	@Inject
	private FicheSalaireRule ficheSalaireRule;

	private Date dateCalcul;

	protected BasicSalaireMapperItemProcessor() {
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
	public void setDateCalcul(Date dateCalcul) {
		this.dateCalcul = dateCalcul;
	}
}
