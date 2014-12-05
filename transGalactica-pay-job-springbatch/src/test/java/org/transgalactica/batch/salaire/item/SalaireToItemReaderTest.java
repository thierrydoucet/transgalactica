package org.transgalactica.batch.salaire.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.GregorianCalendar;

import javax.inject.Named;

import org.junit.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.batch.salaire.AbstractBatchContextTest;
import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

public class SalaireToItemReaderTest extends AbstractBatchContextTest {

	@Autowired
	@Named("org.transgalactica.batch.salaire.item.SalaireToItemReader")
	private ItemReader<SalaireTo> reader;

	public StepExecution getStepExection() {
		JobParameters parameters = new JobParametersBuilder().addString("salaire.compute.output.filename",
				"classpath:org/transgalactica/batch/salaire/compute/SalaireToOuput.txt").toJobParameters();
		StepExecution execution = MetaDataInstanceFactory.createStepExecution(parameters);
		return execution;
	}

	@Test
	public void testRead() throws Exception {

		((ItemStream) reader).open(MetaDataInstanceFactory.createJobExecution().getExecutionContext());
		SalaireTo salaire = reader.read();

		assertNotNull(salaire);
		assertEquals(new GregorianCalendar(1977, 5, 9).getTime(), salaire.getDateEmbaucheEmploye());
		assertEquals("Han Solo", salaire.getNomEmploye());
		assertEquals(3000, salaire.getPrimeAnciennete().intValue());
		assertEquals(542, salaire.getPrimeExperience().intValue());
		assertEquals(11542, salaire.getSalaire().intValue());
		assertEquals(8000, salaire.getSalaireBase().intValue());
		assertEquals(EmployeType.PILOTE, salaire.getTypeEmploye());
	}
}
