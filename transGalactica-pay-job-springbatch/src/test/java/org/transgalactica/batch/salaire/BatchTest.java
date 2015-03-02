package org.transgalactica.batch.salaire;

import static org.junit.Assert.assertEquals;
import static org.springframework.batch.core.ExitStatus.COMPLETED;
import static org.springframework.batch.test.AssertFile.assertFileEquals;

import org.junit.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class BatchTest extends AbstractBatchTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	private static final String TARGET_DIR = "target/BatchTest/" + System.currentTimeMillis() + "/";

	@Test
	public void testLaunch() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(new JobParametersBuilder()
				.addString("salaire.compute.date", "2007-12-03")
				.addString("salaire.edit.output.directory", "file:./" + TARGET_DIR)
				.addString("salaire.compute.output.filename", "file:./" + TARGET_DIR + "salaireComputeOuput.txt")
				.toJobParameters());

		assertEquals(COMPLETED, jobExecution.getExitStatus());

		checkComputeStep();
		checkEditStep();
	}

	private void checkComputeStep() throws Exception {
		assertFileEquals(new ClassPathResource("org/transgalactica/batch/salaire/compute/SalaireToOuput.txt"),
				new FileSystemResource(TARGET_DIR + "salaireComputeOuput.txt"));
	}

	private void checkEditStep() throws Exception {
		Resource[] fiches = applicationContext.getResources("file:./" + TARGET_DIR + "*_2007-12.pdf");
		assertEquals(7, fiches.length);
	}
}