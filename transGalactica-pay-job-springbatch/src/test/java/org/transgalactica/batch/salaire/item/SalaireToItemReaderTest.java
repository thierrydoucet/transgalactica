package org.transgalactica.batch.salaire.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.batch.salaire.AbstractBatchTest;
import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

public class SalaireToItemReaderTest extends AbstractBatchTest {

	@Autowired
	private FlatFileItemReader<SalaireTo> reader;

	public StepExecution getStepExection() {
		return MetaDataInstanceFactory.createStepExecution( //
				new JobParametersBuilder().addString("salaire.compute.output.filename",
						"classpath:org/transgalactica/batch/salaire/compute/SalaireToOuput.txt").toJobParameters());
	}

	@Test
	public void testRead() throws Throwable {
		reader.open(MetaDataInstanceFactory.createJobExecution().getExecutionContext());
		SalaireTo salaire = reader.read();

		assertNotNull(salaire);
		assertEquals(LocalDate.of(1977, 6, 9), salaire.getDateEmbaucheEmploye());
		assertEquals("Han Solo", salaire.getNomEmploye());
		assertEquals(3000, salaire.getPrimeAnciennete().intValue());
		assertEquals(542, salaire.getPrimeExperience().intValue());
		assertEquals(11542, salaire.getSalaire().intValue());
		assertEquals(8000, salaire.getSalaireBase().intValue());
		assertEquals(EmployeType.PILOTE, salaire.getTypeEmploye());
	}
}
