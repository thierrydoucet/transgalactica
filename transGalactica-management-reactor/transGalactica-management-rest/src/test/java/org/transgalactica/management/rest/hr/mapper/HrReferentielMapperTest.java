package org.transgalactica.management.rest.hr.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.transgalactica.management.data.referentiel.bo.EmployeType;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;
import org.transgalactica.management.data.referentiel.bo.impl.JpaMecanicienSpecialiteEntity;
import org.transgalactica.management.rest.AbstractWebTest;
import org.transgalactica.management.rest.hr.data.EmployeTypeDtos;
import org.transgalactica.management.rest.hr.data.MecanicienDetailDto;
import org.transgalactica.management.rest.hr.data.MecanicienSpecialiteDtos;
import org.transgalactica.management.rest.hr.data.impl.JaxbMecanicienDetailDto;

/**
 * Classe de tests pour le mapper relatif aux referentiel HR.
 * 
 * @author Thierry
 */
public class HrReferentielMapperTest extends AbstractWebTest {

	@Autowired
	private HrReferentielMapper mapper;

	@Test
	public void testMapToMecanicienSpecialiteDtos() {
		MecanicienSpecialiteEntity specialiteEntity = BeanUtils.instantiateClass(JpaMecanicienSpecialiteEntity.class);
		ReflectionTestUtils.setField(specialiteEntity, "nomSpecialite", "nomSpecialite");

		MecanicienSpecialiteDtos dtos = mapper.mapToMecanicienSpecialiteDtos(Collections
				.singletonList(specialiteEntity));

		assertNotNull(dtos);
		assertNotNull(dtos.getMecanicienSpecialites());
		assertEquals(1, dtos.getMecanicienSpecialites().size());
		assertEquals("nomSpecialite", dtos.getMecanicienSpecialites().iterator().next());
	}

	@Test
	public void testMapToEmployeTypeDtos() {
		EmployeTypeDtos dtos = mapper.mapToEmployeTypeDtos(EmployeType.values());

		assertNotNull(dtos);
		assertNotNull(dtos.getEmployeTypes());
		assertEquals(2, dtos.getEmployeTypes().size());
		assertEquals("PILOTE", dtos.getEmployeTypes().iterator().next());
	}

	@Test
	public void testMapSpecialitesToMecanicienDetailDto() {
		MecanicienSpecialiteEntity specialiteEntity = BeanUtils.instantiateClass(JpaMecanicienSpecialiteEntity.class);
		ReflectionTestUtils.setField(specialiteEntity, "nomSpecialite", "nomSpecialite");

		MecanicienDetailDto dto = BeanUtils.instantiateClass(JaxbMecanicienDetailDto.class);
		mapper.mapSpecialitesToMecanicienDetailDto(dto, Collections.singleton(specialiteEntity));

		assertNotNull(dto.getSpecialites());
		assertEquals(1, dto.getSpecialites().size());
		assertEquals("nomSpecialite", dto.getSpecialites().iterator().next());
	}
}
