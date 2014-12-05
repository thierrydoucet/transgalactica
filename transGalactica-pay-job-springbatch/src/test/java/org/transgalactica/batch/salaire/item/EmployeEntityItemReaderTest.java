package org.transgalactica.batch.salaire.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.GregorianCalendar;

import javax.inject.Named;

import org.junit.Test;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.transgalactica.batch.salaire.AbstractBatchContextTest;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

public class EmployeEntityItemReaderTest extends AbstractBatchContextTest {

	@Autowired
	@Named("org.transgalactica.batch.salaire.item.EmployeEntityItemReader")
	private ItemReader<EmployeEntity> reader;

	@Test
	@DirtiesContext
	public void testRead() throws Exception {
		((ItemStream) reader).open(MetaDataInstanceFactory.createJobExecution().getExecutionContext());
		EmployeEntity employe = reader.read();

		assertNotNull(employe);
		assertEquals(1, employe.getMatricule().intValue());
		assertEquals("Han Solo", employe.getNom());
		assertEquals(new GregorianCalendar(1977, 5, 9).getTime(), employe.getDateEmbauche());
		assertEquals(EmployeType.PILOTE, employe.getType());
	}
}
