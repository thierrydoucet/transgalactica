package org.transgalactica.batch.salaire.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.batch.salaire.AbstractBatchTest;
import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.management.data.people.bo.PiloteEntity;
import org.transgalactica.management.data.people.bo.impl.JpaPiloteEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

public class SalaireMapperTest extends AbstractBatchTest {

	@Autowired
	private SalaireMapper salaireMapper;

	@Test
	public void testMapEmployeInformation() {
		PiloteEntity employe = BeanUtils.instantiateClass(JpaPiloteEntity.class);
		employe.setNom("nom du pilote");
		employe.setDateEmbauche(LocalDate.of(2000, 2, 23));
		employe.setNombreHeuresVol(new Integer(7));

		SalaireTo salaire = salaireMapper.mapEmployeInformation(employe);

		assertNotNull(salaire);
		assertEquals("nom du pilote", salaire.getNomEmploye());
		assertEquals(EmployeType.PILOTE, salaire.getTypeEmploye());
		assertEquals(LocalDate.of(2000, 2, 23), salaire.getDateEmbaucheEmploye());
	}

}
