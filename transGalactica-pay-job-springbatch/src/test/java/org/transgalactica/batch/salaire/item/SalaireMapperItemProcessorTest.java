package org.transgalactica.batch.salaire.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import javax.inject.Named;

import org.junit.Test;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.batch.salaire.AbstractBatchTest;
import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.PiloteEntity;
import org.transgalactica.management.data.people.bo.impl.JpaPiloteEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

public class SalaireMapperItemProcessorTest extends AbstractBatchTest {

	@Autowired
	@Named("computeSalaireItemProcessor")
	private ItemProcessor<EmployeEntity, SalaireTo> processor;

	public StepExecution getStepExecution() {
		return MetaDataInstanceFactory.createStepExecution(new JobParametersBuilder().addString("salaire.compute.date",
				"2007-11-03").toJobParameters());
	}

	@Test
	public void testProcess() throws Exception {
		PiloteEntity employe = BeanUtils.instantiateClass(JpaPiloteEntity.class);
		employe.setNom("nom du pilote");
		employe.setDateEmbauche(LocalDate.of(2000, 2, 23));
		employe.setNombreHeuresVol(new Integer(7));

		SalaireTo salaire = processor.process(employe);

		assertNotNull(salaire);
		assertEquals("nom du pilote", salaire.getNomEmploye());
		assertEquals(EmployeType.PILOTE, salaire.getTypeEmploye());
		assertEquals(LocalDate.of(2000, 2, 23), salaire.getDateEmbaucheEmploye());
		assertEquals(8000, salaire.getSalaireBase().intValue());
		assertEquals(7, salaire.getPrimeExperience().intValue());
		assertEquals(700, salaire.getPrimeAnciennete().intValue());
		assertEquals(8707, salaire.getSalaire().intValue());
	}
}
