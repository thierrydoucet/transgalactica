package org.transgalactica.management.ws.logistics.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;

import org.junit.Test;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.management.data.materiel.bo.BasicHangarSummary;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.ws.AbstractSpringContextTest;
import org.transgalactica.management.ws.logistics.data.BasicHangarDto;
import org.transgalactica.management.ws.logistics.mapper.HangarMapper;

public class HangarMapperTest extends AbstractSpringContextTest {

	@Autowired
	private HangarMapper mapper;

	@Test
	public void testMapToRechercheHangarCriteres() {
		HangarSearchCriteria criteres = mapper.mapToRechercheHangarCriteres("localisation");

		assertNotNull(criteres);
		assertEquals("localisation", criteres.getLocalisationHangar());
	}

	@Test
	public void testMapHangars() throws BeanInstantiationException, NoSuchMethodException, SecurityException {
		HangarSummary hangar = BeanUtils.instantiateClass(
				BasicHangarSummary.class.getConstructor(Long.class, String.class, int.class), 1L,
				"localisationHangar", 2);

		BasicHangarDto[] hangars = mapper.mapToHangars(Collections.singletonList(hangar));

		assertNotNull(hangars);
		assertEquals(1, hangars.length);
		BasicHangarDto hangarTransport = hangars[0];
		assertEquals(new Long(1), hangarTransport.getNumero());
		assertEquals("localisationHangar", hangarTransport.getLocalisation());
		assertEquals(2, hangarTransport.getNombreEmplacements());
	}
}
