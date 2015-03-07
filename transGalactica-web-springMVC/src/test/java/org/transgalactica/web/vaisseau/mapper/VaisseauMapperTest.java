package org.transgalactica.web.vaisseau.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauIntergalactiqueEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauIntergalactiqueEntity;
import org.transgalactica.web.AbstractWebTest;
import org.transgalactica.web.vaisseau.model.VaisseauCommand;

/**
 * Classe de tests pour le mapper relatif aux Vaisseaus.
 * 
 * @author Thierry
 */
public class VaisseauMapperTest extends AbstractWebTest {

	@Autowired
	private VaisseauMapper mapper;

	@Test
	public void testMapToVaisseauCommand() {

		VaisseauEntity entity = BeanUtils.instantiateClass(JpaVaisseauEntity.class);
		entity.setImmatriculation("immatriculation");
		entity.setCapaciteDeFret(10);
		entity.setModele("modele");
		entity.setNombreDePassagers((short) 100);
		entity.setAutonomie(100000);
		entity.setVitesse(1000);

		VaisseauCommand command = mapper.mapToVaisseauCommand(entity);

		assertNotNull(command);
		assertEquals("immatriculation", command.getImmatriculation());
		assertEquals(10, command.getCapaciteDeFret());
		assertEquals("modele", command.getModele());
		assertEquals(100, command.getNombreDePassagers());
		assertEquals(100000, command.getAutonomie());
		assertEquals(1000, command.getVitesse());
	}

	@Test
	public void testMapVaisseauCommandToEntity() {
		VaisseauCommand command = BeanUtils.instantiateClass(VaisseauCommand.class);
		VaisseauEntity entity = BeanUtils.instantiateClass(JpaVaisseauEntity.class);

		command.setImmatriculation("immatriculation");
		command.setCapaciteDeFret(10);
		command.setModele("modele");
		command.setNombreDePassagers((short) 100);
		command.setAutonomie(100000);
		command.setVitesse(1000);
		command.setMultiplicateurHyperdrive(null);

		mapper.mapVaisseauCommandToEntity(command, entity);

		assertNotNull(entity);
		assertEquals("immatriculation", entity.getImmatriculation());
		assertEquals(10, entity.getCapaciteDeFret());
		assertEquals("modele", entity.getModele());
		assertEquals(100, entity.getNombreDePassagers());
		assertEquals(100000, entity.getAutonomie());
		assertEquals(1000, entity.getVitesse());
	}

	@Test
	public void testMapVaisseauIntergalactiqueCommandToEntity() {
		VaisseauCommand command = BeanUtils.instantiateClass(VaisseauCommand.class);
		VaisseauIntergalactiqueEntity entity = BeanUtils.instantiateClass(JpaVaisseauIntergalactiqueEntity.class);

		command.setImmatriculation("immatriculation");
		command.setCapaciteDeFret(10);
		command.setModele("modele");
		command.setNombreDePassagers((short) 100);
		command.setAutonomie(100000);
		command.setVitesse(1000);
		command.setMultiplicateurHyperdrive((short) 2);

		mapper.mapVaisseauCommandToEntity(command, entity);

		assertNotNull(entity);
		assertEquals("immatriculation", entity.getImmatriculation());
		assertEquals(10, entity.getCapaciteDeFret());
		assertEquals("modele", entity.getModele());
		assertEquals(100, entity.getNombreDePassagers());
		assertEquals(100000, entity.getAutonomie());
		assertEquals(1000, entity.getVitesse());
		assertEquals(2, entity.getMultiplicateurHyperdrive());
	}
}
