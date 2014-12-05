package org.transgalactica.batch.salaire.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.batch.salaire.AbstractContextTest;
import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.batch.salaire.mapper.SalaireMapper;
import org.transgalactica.management.data.people.bo.PiloteEntity;
import org.transgalactica.management.data.people.bo.impl.JpaPiloteEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

public class SalaireMapperTest extends AbstractContextTest {

	@Autowired
	private SalaireMapper salaireMapper;

	@Test
	public void testMapEmployeInformation() {
		PiloteEntity employe = BeanUtils.instantiateClass(JpaPiloteEntity.class);
		employe.setNom("nom du pilote");
		employe.setDateEmbauche(new GregorianCalendar(2000, 1, 23).getTime());
		employe.setNombreHeuresVol(new Integer(7));

		SalaireTo salaire = salaireMapper.mapEmployeInformation(employe);

		assertNotNull(salaire);
		assertEquals("nom du pilote", salaire.getNomEmploye());
		assertEquals(EmployeType.PILOTE, salaire.getTypeEmploye());
		assertEquals(new GregorianCalendar(2000, 1, 23).getTime(), salaire.getDateEmbaucheEmploye());
	}

}
