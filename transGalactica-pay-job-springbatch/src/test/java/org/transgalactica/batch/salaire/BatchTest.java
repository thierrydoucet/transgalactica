package org.transgalactica.batch.salaire;

import static org.junit.Assert.assertEquals;
import static org.springframework.batch.test.AssertFile.assertFileEquals;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class BatchTest extends AbstractBatchContextTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void testLaunch() throws Exception {
		JobParametersBuilder parametersBuilder = new JobParametersBuilder();
		parametersBuilder.addDate("salaire.compute.date", new GregorianCalendar(2007, 11, 3).getTime());
		parametersBuilder.addString("salaire.compute.output.filename",
				"file:./target/BatchTest/salaireComputeOuput.txt");
		parametersBuilder.addString("salaire.edit.output.directory", "file:./target/BatchTest/");

		JobExecution jobExecution = jobLauncherTestUtils.launchJob(parametersBuilder.toJobParameters());

		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());

		checkComputeStep();
		checkEditStep();
	}

	private void checkComputeStep() throws Exception {
		assertFileEquals(new ClassPathResource("org/transgalactica/batch/salaire/compute/SalaireToOuput.txt"),
				new FileSystemResource("target/BatchTest/salaireComputeOuput.txt"));
	}

	private void checkEditStep() throws Exception {
		Resource[] fiches = applicationContext.getResources("file:./target/BatchTest/*_2007-12.pdf");
		assertEquals(7, fiches.length);
	}
}
