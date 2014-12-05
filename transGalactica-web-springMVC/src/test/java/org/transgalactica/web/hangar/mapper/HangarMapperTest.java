package org.transgalactica.web.hangar.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaHangarEntity;
import org.transgalactica.web.AbstractWebContextTest;
import org.transgalactica.web.hangar.mapper.HangarMapper;
import org.transgalactica.web.hangar.model.impl.HangarCommand;

/**
 * Classe de tests pour le mapper relatif aux hangars.
 * 
 * @author Thierry
 */
public class HangarMapperTest extends AbstractWebContextTest {

	@Autowired
	private HangarMapper mapper;

	@Test
	public void testMapToHangarCommand() {
		HangarEntity entity = BeanUtils.instantiateClass(JpaHangarEntity.class);
		entity.setNombreEmplacements(10);
		entity.setLocalisation("localisation");

		HangarCommand command = mapper.mapToHangarCommand(entity);

		assertNotNull(command);
		assertEquals("localisation", command.getLocalisation());
		assertEquals(10, command.getNombreEmplacements().intValue());
	}

	@Test
	public void testMapHangarCommandToEntity() {
		HangarCommand command = BeanUtils.instantiateClass(HangarCommand.class);
		HangarEntity entity = BeanUtils.instantiateClass(JpaHangarEntity.class);

		command.setNombreEmplacements(10);
		command.setLocalisation("localisation");

		mapper.mapHangarCommandToEntity(command, entity);

		assertNotNull(entity);
		assertEquals("localisation", entity.getLocalisation());
		assertEquals(10, entity.getNombreEmplacements());
	}
}
