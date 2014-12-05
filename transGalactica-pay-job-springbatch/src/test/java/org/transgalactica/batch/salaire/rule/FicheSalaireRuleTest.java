package org.transgalactica.batch.salaire.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.transgalactica.batch.salaire.AbstractContextTest;
import org.transgalactica.batch.salaire.rule.FicheSalaireRule;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.MecanicienEntity;
import org.transgalactica.management.data.people.bo.PiloteEntity;
import org.transgalactica.management.data.people.bo.impl.JpaMecanicienEntity;
import org.transgalactica.management.data.people.bo.impl.JpaPiloteEntity;
import org.transgalactica.management.data.referentiel.bo.impl.JpaMecanicienSpecialiteEntity;

public class FicheSalaireRuleTest extends AbstractContextTest {

	@Autowired
	private FicheSalaireRule ficheSalaireRule;

	@Test
	public void testCalculerSalaireBasePilote() {
		PiloteEntity employe = BeanUtils.instantiateClass(JpaPiloteEntity.class);
		BigDecimal salaire = ficheSalaireRule.calculerSalaireBase(employe);
		assertNotNull(salaire);
		assertEquals(8000, salaire.intValue());
	}

	@Test
	public void testCalculerSalaireBaseMecanicien() {
		MecanicienEntity employe = BeanUtils.instantiateClass(JpaMecanicienEntity.class);
		BigDecimal salaire = ficheSalaireRule.calculerSalaireBase(employe);
		assertNotNull(salaire);
		assertEquals(10000, salaire.intValue());
	}

	@Test
	public void testCalculerPrimeAnciennete() {
		EmployeEntity employe = BeanUtils.instantiateClass(JpaPiloteEntity.class);
		employe.setDateEmbauche(new GregorianCalendar(2000, 11, 23).getTime());
		BigDecimal salaire = ficheSalaireRule.calculerPrimeAnciennete(employe,
				new GregorianCalendar(2007, 11, 3).getTime());
		assertNotNull(salaire);
		assertEquals(600, salaire.intValue());
	}

	@Test
	public void testCalculerPrimeExperiencePilote() {
		PiloteEntity employe = BeanUtils.instantiateClass(JpaPiloteEntity.class);
		employe.setNombreHeuresVol(new Integer(7));
		BigDecimal salaire = ficheSalaireRule.calculerPrimeExperience(employe);
		assertNotNull(salaire);
		assertEquals(7, salaire.intValue());
	}

	@Test
	public void testCalculerPrimeExperienceMecanicien() {
		MecanicienEntity employe = BeanUtils.instantiateClass(JpaMecanicienEntity.class);
		JpaMecanicienSpecialiteEntity s1 = BeanUtils.instantiateClass(JpaMecanicienSpecialiteEntity.class);
		ReflectionTestUtils.invokeSetterMethod(s1, "nomSpecialite", "nomSpecialite");
		employe.getSpecialites().add(s1);
		JpaMecanicienSpecialiteEntity s2 = BeanUtils.instantiateClass(JpaMecanicienSpecialiteEntity.class);
		ReflectionTestUtils.invokeSetterMethod(s2, "nomSpecialite", "nomSpecialite2");
		employe.getSpecialites().add(s2);
		BigDecimal salaire = ficheSalaireRule.calculerPrimeExperience(employe);
		assertNotNull(salaire);
		assertEquals(2000, salaire.intValue());
	}
}
