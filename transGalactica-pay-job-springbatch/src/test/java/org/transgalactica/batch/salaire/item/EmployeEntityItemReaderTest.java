package org.transgalactica.batch.salaire.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import javax.inject.Named;

import org.junit.Test;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.transgalactica.batch.salaire.AbstractBatchTest;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

public class EmployeEntityItemReaderTest extends AbstractBatchTest {

	@Autowired
	@Named("employeEntityItemReader")
	private ItemReader<EmployeEntity> reader;

	@Test
	@DirtiesContext
	public void testRead() throws Exception {
		((ItemStream) reader).open(MetaDataInstanceFactory.createJobExecution().getExecutionContext());
		EmployeEntity employe = reader.read();

		assertNotNull(employe);
		assertEquals(1, employe.getMatricule().intValue());
		assertEquals("Han Solo", employe.getNom());
		assertEquals(LocalDate.of(1977, 6, 9), employe.getDateEmbauche());
		assertEquals(EmployeType.PILOTE, employe.getType());
	}
}
