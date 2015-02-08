package org.transgalactica.management.rest.logistics.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.management.data.materiel.bo.BasicVaisseauSummary;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauIntergalactiqueEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.data.materiel.bo.impl.JpaHangarEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauIntergalactiqueEntity;
import org.transgalactica.management.rest.AbstractWebTest;
import org.transgalactica.management.rest.logistics.data.VaisseauCommand;
import org.transgalactica.management.rest.logistics.data.VaisseauDetailDto;
import org.transgalactica.management.rest.logistics.data.VaisseauDtos;
import org.transgalactica.management.rest.logistics.data.impl.JaxbVaisseauCommand;

/**
 * Classe de tests pour le mapper relatif aux Vaisseaus.
 * 
 * @author Thierry
 */
public class VaisseauMapperTest extends AbstractWebTest {

	@Autowired
	private VaisseauMapper mapper;

	@Test
	public void testMapVaisseauCommandToEntity() {
		VaisseauCommand command = BeanUtils.instantiateClass(JaxbVaisseauCommand.class);
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
		VaisseauCommand command = BeanUtils.instantiateClass(JaxbVaisseauCommand.class);
		VaisseauIntergalactiqueEntity entity = BeanUtils.instantiateClass(JpaVaisseauIntergalactiqueEntity.class);

		command.setImmatriculation("immatriculation");
		command.setCapaciteDeFret(10);
		command.setModele("modele");
		command.setNombreDePassagers((short) 100);
		command.setAutonomie(100000);
		command.setVitesse(1000);
		command.setMultiplicateurHyperdrive(new Short("2"));

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

	@Test
	public void testMapToRechercheHangarCriteres() {

		VaisseauSearchCriteria criteres = mapper.mapToRechercheVaisseauCriteres("immatriculation", "modele", true);

		assertNotNull(criteres);
		assertEquals("immatriculation", criteres.getImmatriculation());
		assertEquals("modele", criteres.getModele());
		assertEquals(true, criteres.isIntergalactique());
	}

	@Test
	public void testMapToHangarDtos() throws BeanInstantiationException, NoSuchMethodException, SecurityException {
		VaisseauSummary projection = new BasicVaisseauSummary("immatriculationVaisseau", "modeleVaisseau",
				"localisationHangar");

		VaisseauDtos dtos = mapper.mapToVaisseauDtos(Arrays.asList(projection));

		assertNotNull(dtos);
		assertEquals(1, dtos.getVaisseaux().size());
		assertEquals("immatriculationVaisseau", dtos.getVaisseaux().get(0).getImmatriculation());
		assertEquals("localisationHangar", dtos.getVaisseaux().get(0).getLocalisationHangar());
		assertEquals("modeleVaisseau", dtos.getVaisseaux().get(0).getModele());
	}

	@Test
	public void testMapToHangarDetailDto() {
		VaisseauEntity entity = BeanUtils.instantiateClass(JpaVaisseauEntity.class);
		entity.setCapaciteDeFret(1);
		entity.setImmatriculation("immatriculation");
		entity.setModele("modele");
		entity.setNombreDePassagers((short) 10);
		entity.setAutonomie(100);
		entity.setVitesse(1000);
		HangarEntity hangarEntity = BeanUtils.instantiateClass(JpaHangarEntity.class);
		hangarEntity.setLocalisation("localisationHangar");
		((JpaHangarEntity) hangarEntity).setNumero(new Long(9));
		entity.setHangar(hangarEntity);

		VaisseauDetailDto dto = mapper.mapToVaisseauDetailDto(entity);

		assertNotNull(dto);
		assertEquals(1, dto.getCapaciteDeFret());
		assertEquals("immatriculation", dto.getImmatriculation());
		assertEquals("modele", dto.getModele());
		assertEquals((short) 10, dto.getNombreDePassagers());
		assertEquals(100, dto.getAutonomie());
		assertEquals(1000, dto.getVitesse());
		assertEquals(null, dto.getMultiplicateurHyperdrive());
		assertEquals("localisationHangar", dto.getLocalisationHangar());
		assertEquals(new Long(9), dto.getNumeroHangar());
	}

	@Test
	public void testMapToHangarDetailDto_vaisseauIntergalactique() {
		VaisseauIntergalactiqueEntity entity = BeanUtils.instantiateClass(JpaVaisseauIntergalactiqueEntity.class);
		entity.setCapaciteDeFret(1);
		entity.setImmatriculation("immatriculation");
		entity.setModele("modele");
		entity.setNombreDePassagers((short) 10);
		entity.setAutonomie(100);
		entity.setVitesse(1000);
		entity.setMultiplicateurHyperdrive((short) 6);
		HangarEntity hangarEntity = BeanUtils.instantiateClass(JpaHangarEntity.class);
		hangarEntity.setLocalisation("localisationHangar");
		((JpaHangarEntity) hangarEntity).setNumero(new Long(9));
		entity.setHangar(hangarEntity);

		VaisseauDetailDto dto = mapper.mapToVaisseauDetailDto(entity);

		assertNotNull(dto);
		assertEquals(1, dto.getCapaciteDeFret());
		assertEquals("immatriculation", dto.getImmatriculation());
		assertEquals("modele", dto.getModele());
		assertEquals((short) 10, dto.getNombreDePassagers());
		assertEquals(100, dto.getAutonomie());
		assertEquals(1000, dto.getVitesse());
		assertEquals(new Short("6"), dto.getMultiplicateurHyperdrive());
		assertEquals("localisationHangar", dto.getLocalisationHangar());
		assertEquals(new Long(9), dto.getNumeroHangar());
	}
}
