package org.transgalactica.management.rest.logistics.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.management.data.materiel.bo.BasicHangarSummary;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaHangarEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauEntity;
import org.transgalactica.management.rest.AbstractWebTest;
import org.transgalactica.management.rest.logistics.data.HangarCommand;
import org.transgalactica.management.rest.logistics.data.HangarDetailDto;
import org.transgalactica.management.rest.logistics.data.HangarDtos;
import org.transgalactica.management.rest.logistics.data.impl.JaxbHangarCommand;

/**
 * Classe de tests pour le mapper relatif aux hangars.
 * 
 * @author Thierry
 */
public class HangarMapperTest extends AbstractWebTest {

	@Autowired
	private HangarMapper mapper;

	@Test
	public void testMapHangarCommandToEntity() {
		HangarCommand command = BeanUtils.instantiateClass(JaxbHangarCommand.class);
		HangarEntity entity = BeanUtils.instantiateClass(JpaHangarEntity.class);

		command.setNombreEmplacements(10);
		command.setLocalisation("localisation");

		mapper.mapHangarCommandToEntity(command, entity);

		assertNotNull(entity);
		assertEquals("localisation", entity.getLocalisation());
		assertEquals(10, entity.getNombreEmplacements());
	}

	@Test
	public void testMapToRechercheHangarCriteres() {
		HangarSearchCriteria criteres = mapper.mapToRechercheHangarCriteres("localisationHangar");

		assertNotNull(criteres);
		assertEquals("localisationHangar", criteres.getLocalisationHangar());
	}

	@Test
	public void testMapToHangarDtos() throws BeanInstantiationException, NoSuchMethodException, SecurityException {
		HangarSummary projection = BeanUtils.instantiateClass(
				BasicHangarSummary.class.getConstructor(Long.class, String.class, int.class), 10L,
				"localisationHangar", 5);

		HangarDtos dtos = mapper.mapToHangarDtos(Arrays.asList(projection));

		assertNotNull(dtos);
		assertEquals(1, dtos.getHangars().size());
		assertEquals(new Long(10), dtos.getHangars().get(0).getNumero());
		assertEquals("localisationHangar", dtos.getHangars().get(0).getLocalisation());
		assertEquals(5, dtos.getHangars().get(0).getNombreEmplacements());
	}

	@Test
	public void testMapToHangarDetailDto() {
		HangarEntity entity = BeanUtils.instantiateClass(JpaHangarEntity.class);
		((JpaHangarEntity) entity).setNumero(new Long(10));
		entity.setLocalisation("localisation");
		entity.setNombreEmplacements(5);
		VaisseauEntity vaisseau = BeanUtils.instantiateClass(JpaVaisseauEntity.class);
		vaisseau.setCapaciteDeFret(1);
		vaisseau.setImmatriculation("immatriculation");
		vaisseau.setModele("modele");
		vaisseau.setNombreDePassagers((short) 10);
		vaisseau.setAutonomie(100);
		vaisseau.setVitesse(1000);
		entity.add(vaisseau);

		HangarDetailDto dto = mapper.mapToHangarDetailDto(entity);

		assertNotNull(dto);
		assertEquals(new Long(10), dto.getNumero());
		assertEquals("localisation", dto.getLocalisation());
		assertEquals(1, dto.getVaisseaux().size());
		HangarDetailDto.VaisseauDto vaisseauDto = dto.getVaisseaux().iterator().next();
		assertEquals(1, vaisseauDto.getCapaciteDeFret());
		assertEquals("immatriculation", vaisseauDto.getImmatriculation());
		assertEquals("modele", vaisseauDto.getModele());
		assertEquals((short) 10, vaisseauDto.getNombreDePassagers());
		assertEquals(100, vaisseauDto.getAutonomie());
		assertEquals(1000, vaisseauDto.getVitesse());
	}
}
