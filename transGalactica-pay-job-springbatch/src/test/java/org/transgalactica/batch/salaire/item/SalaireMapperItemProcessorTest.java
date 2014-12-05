package org.transgalactica.batch.salaire.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.GregorianCalendar;

import javax.inject.Named;

import org.junit.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.batch.salaire.AbstractBatchContextTest;
import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.PiloteEntity;
import org.transgalactica.management.data.people.bo.impl.JpaPiloteEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

public class SalaireMapperItemProcessorTest extends AbstractBatchContextTest {

	@Autowired
	@Named("org.transgalactica.batch.salaire.item.SalaireMapperItemProcessor")
	private ItemProcessor<EmployeEntity, SalaireTo> processor;

	public StepExecution getStepExecution() {
		JobParameters jobParameters = new JobParametersBuilder().addDate("salaire.compute.date",
				new GregorianCalendar(2007, 11, 3).getTime()).toJobParameters();
		StepExecution execution = MetaDataInstanceFactory.createStepExecution(jobParameters);
		return execution;
	}

	@Test
	public void testProcess() throws Exception {
		PiloteEntity employe = BeanUtils.instantiateClass(JpaPiloteEntity.class);
		employe.setNom("nom du pilote");
		employe.setDateEmbauche(new GregorianCalendar(2000, 1, 23).getTime());
		employe.setNombreHeuresVol(new Integer(7));

		SalaireTo salaire = processor.process(employe);

		assertNotNull(salaire);
		assertEquals("nom du pilote", salaire.getNomEmploye());
		assertEquals(EmployeType.PILOTE, salaire.getTypeEmploye());
		assertEquals(new GregorianCalendar(2000, 1, 23).getTime(), salaire.getDateEmbaucheEmploye());
		assertEquals(8000, salaire.getSalaireBase().intValue());
		assertEquals(7, salaire.getPrimeExperience().intValue());
		assertEquals(700, salaire.getPrimeAnciennete().intValue());
		assertEquals(8707, salaire.getSalaire().intValue());
	}
}
