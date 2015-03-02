package org.transgalactica.batch.salaire.item;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Locale;

import javax.inject.Named;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.transgalactica.batch.salaire.AbstractBatchTest;
import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.batch.salaire.bo.impl.BasicSalaireTo;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

public class FicheSalairePdfItemWriterTest extends AbstractBatchTest {

	private static final String TARGET_PATH = "target/BatchTest/" + System.currentTimeMillis() + "/";

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	@Named("salairePdfItemWriter")
	private ItemWriter<SalaireTo> writer;

	@Before
	public void createTargetDirectoryIfNotExist() throws IOException {
		Resource targetDirectory = applicationContext.getResource("file:./" + TARGET_PATH);
		if (!targetDirectory.exists()) {
			targetDirectory.getFile().mkdir();
		}
	}

	public StepExecution getStepExection() {
		JobParametersBuilder parametersBuilder = new JobParametersBuilder();
		parametersBuilder.addString("salaire.compute.date", "2011-12-03");
		parametersBuilder.addString("salaire.edit.output.directory", "file:./" + TARGET_PATH);

		StepExecution execution = MetaDataInstanceFactory.createStepExecution(parametersBuilder.toJobParameters());
		return execution;
	}

	@Test
	public void testWriteDefaultLocale() throws Exception {
		SalaireTo salaire = BeanUtils.instantiateClass(BasicSalaireTo.class);
		salaire.setNomEmploye("Wedge Antilles");
		salaire.setTypeEmploye(EmployeType.PILOTE);
		salaire.setDateEmbaucheEmploye(LocalDate.of(2000, 2, 23));
		salaire.setSalaireBase(new BigDecimal("8000"));
		salaire.setPrimeExperience(new BigDecimal("7"));
		salaire.setPrimeAnciennete(new BigDecimal("700"));
		salaire.setSalaire(new BigDecimal("8707"));

		writer.write(Collections.singletonList(salaire));

		assertTrue(new FileSystemResource(TARGET_PATH + "Wedge Antilles_2011-12.pdf").exists());
		// TODO : voir pour controler le contenu du fichier (hors metadata)
	}

	@Test
	public void testWriteLocaleJapan() throws Exception {
		Locale defaultLocale = Locale.getDefault();
		try {
			Locale.setDefault(Locale.JAPAN);

			SalaireTo salaire = BeanUtils.instantiateClass(BasicSalaireTo.class);
			salaire.setNomEmploye("C3PO");
			salaire.setTypeEmploye(EmployeType.PILOTE);
			salaire.setDateEmbaucheEmploye(LocalDate.of(2000, 2, 23));
			salaire.setSalaireBase(new BigDecimal("8000"));
			salaire.setPrimeExperience(new BigDecimal("337"));
			salaire.setPrimeAnciennete(new BigDecimal("100"));
			salaire.setSalaire(new BigDecimal("8437"));

			writer.write(Collections.singletonList(salaire));
			// TODO : voir pour controler le contenu du fichier (hors metadata)
		}
		finally {
			Locale.setDefault(defaultLocale);
		}
	}

	@Test
	public void testWriteLocaleArabe() throws Exception {
		Locale defaultLocale = Locale.getDefault();
		try {
			Locale.setDefault(new Locale("ar", "MA"));

			SalaireTo salaire = BeanUtils.instantiateClass(BasicSalaireTo.class);
			salaire.setNomEmploye("Dathcha");
			salaire.setTypeEmploye(EmployeType.PILOTE);
			salaire.setDateEmbaucheEmploye(LocalDate.of(2000, 2, 23));
			salaire.setSalaireBase(new BigDecimal("5000"));
			salaire.setPrimeExperience(new BigDecimal("1000"));
			salaire.setPrimeAnciennete(new BigDecimal("0"));
			salaire.setSalaire(new BigDecimal("6000"));

			writer.write(Collections.singletonList(salaire));
			// TODO : voir pour controler le contenu du fichier (hors metadata)
		}
		finally {
			Locale.setDefault(defaultLocale);
		}
	}
}
