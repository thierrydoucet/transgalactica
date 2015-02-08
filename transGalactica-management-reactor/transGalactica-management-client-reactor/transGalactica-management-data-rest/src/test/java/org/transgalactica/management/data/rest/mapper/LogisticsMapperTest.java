package org.transgalactica.management.data.rest.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.transgalactica.management.data.rest.TestConfig;
import org.transgalactica.management.data.rest.bo.HangarSummaryTo;
import org.transgalactica.management.data.rest.bo.HangarTo;
import org.transgalactica.management.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.management.data.rest.bo.VaisseauTo;
import org.transgalactica.management.data.rest.bo.impl.BasicHangarTo;
import org.transgalactica.management.data.rest.bo.impl.BasicVaisseauTo;
import org.transgalactica.management.flux.rest.HangarCommand;
import org.transgalactica.management.flux.rest.HangarDetailDto;
import org.transgalactica.management.flux.rest.HangarDto;
import org.transgalactica.management.flux.rest.HangarVaisseau;
import org.transgalactica.management.flux.rest.ObjectFactory;
import org.transgalactica.management.flux.rest.VaisseauCommand;
import org.transgalactica.management.flux.rest.VaisseauDetailDto;
import org.transgalactica.management.flux.rest.VaisseauDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class LogisticsMapperTest {

	@Autowired
	private LogisticsMapper mapper;

	@Test
	public void testMapToHangarCommand() throws DatatypeConfigurationException {
		HangarTo hangarTo = BeanUtils.instantiateClass(BasicHangarTo.class);
		hangarTo.setNombreEmplacements(10);
		hangarTo.setLocalisation("test");

		HangarCommand command = mapper.mapToHangarCommand(hangarTo);

		assertNotNull(command);
		assertEquals("test", command.getLocalisation());
		assertEquals(10, command.getNombreEmplacements());
	}

	@Test
	public void testMapToHangarSummaryTo() throws DatatypeConfigurationException {
		ObjectFactory objectFactory = new ObjectFactory();
		HangarDto hangarDto = objectFactory.createHangarDto();
		hangarDto.setNumero(1L);
		hangarDto.setLocalisation("test");
		hangarDto.setNombreEmplacements(10);

		List<HangarSummaryTo> hangarSummaryTos = mapper.mapToHangarSummaryTo(Collections.singletonList(hangarDto));

		assertNotNull(hangarSummaryTos);
		assertEquals(1, hangarSummaryTos.size());
		assertEquals(new Long(1), hangarSummaryTos.get(0).getNumero());
		assertEquals("test", hangarSummaryTos.get(0).getLocalisation());
		assertEquals(10, hangarSummaryTos.get(0).getNombreEmplacements());
	}

	@Test
	public void testMapToHangarTo() throws DatatypeConfigurationException {
		ObjectFactory objectFactory = new ObjectFactory();
		HangarDetailDto hangar = objectFactory.createHangarDetailDto();
		hangar.setNumero(1L);
		hangar.setLocalisation("test");
		hangar.setNombreEmplacements(12);
		HangarVaisseau vaisseau = objectFactory.createHangarVaisseau();
		vaisseau.setCapaciteDeFret(1L);
		vaisseau.setImmatriculation("value");
		vaisseau.setModele("modele");
		vaisseau.setNombreDePassagers((short) 2);
		vaisseau.setAutonomie(3);
		vaisseau.setVitesse(4);
		hangar.setVaisseaux(objectFactory.createHangarDetailDtoVaisseaux());
		hangar.getVaisseaux().getVaisseau().add(vaisseau);

		HangarTo hangarTo = mapper.mapToHangarTo(hangar);
		assertNotNull(hangarTo);
		assertEquals(new Long(1), hangarTo.getNumero());
		assertEquals("test", hangarTo.getLocalisation());
		assertEquals(12, hangarTo.getNombreEmplacements());
		assertNotNull(hangarTo.getVaisseaux());
		assertEquals(1, hangarTo.getVaisseaux().size());
		assertEquals(1L, hangarTo.getVaisseaux().get(0).getCapaciteDeFret());
		assertEquals("value", hangarTo.getVaisseaux().get(0).getImmatriculation());
		assertEquals("modele", hangarTo.getVaisseaux().get(0).getModele());
		assertEquals((short) 2, hangarTo.getVaisseaux().get(0).getNombreDePassagers());
		assertEquals(3, hangarTo.getVaisseaux().get(0).getAutonomie());
		assertEquals(4, hangarTo.getVaisseaux().get(0).getVitesse());
	}

	@Test
	public void testMapToVaisseauCommand() throws DatatypeConfigurationException {
		VaisseauTo vaisseauTo = BeanUtils.instantiateClass(BasicVaisseauTo.class);
		vaisseauTo.setImmatriculation("immatriculation");
		vaisseauTo.setModele("modele");
		vaisseauTo.setCapaciteDeFret(5);
		vaisseauTo.setVitesse(4);
		vaisseauTo.setAutonomie(3);
		vaisseauTo.setNombreDePassagers((short) 2);
		vaisseauTo.setMultiplicateurHyperdrive(new Short((short) 1));

		VaisseauCommand command = mapper.mapToVaisseauCommand(vaisseauTo);

		assertNotNull(command);
		assertEquals("immatriculation", command.getImmatriculation());
		assertEquals("modele", command.getModele());
		assertEquals(5, command.getCapaciteDeFret());
		assertEquals(4, command.getVitesse());
		assertEquals(3, command.getAutonomie());
		assertEquals(2, command.getNombreDePassagers());
		assertEquals((short) 1, command.getMultiplicateurHyperdrive());
	}

	@Test
	public void testMapToVaisseauSummaryTo() throws DatatypeConfigurationException {
		ObjectFactory objectFactory = new ObjectFactory();
		VaisseauDto vaisseauDto = objectFactory.createVaisseauDto();
		vaisseauDto.setImmatriculation("immatriculation");
		vaisseauDto.setModele("modele");
		vaisseauDto.setLocalisationHangar("localisation");

		List<VaisseauSummaryTo> vaisseauSummaryTos = mapper.mapToVaisseauSummaryTo(Collections
				.singletonList(vaisseauDto));

		assertNotNull(vaisseauSummaryTos);
		assertEquals(1, vaisseauSummaryTos.size());
		assertEquals("immatriculation", vaisseauSummaryTos.get(0).getImmatriculation());
		assertEquals("modele", vaisseauSummaryTos.get(0).getModele());
		assertEquals("localisation", vaisseauSummaryTos.get(0).getLocalisationHangar());
	}

	@Test
	public void testMapToVaisseauTo() throws DatatypeConfigurationException {
		ObjectFactory objectFactory = new ObjectFactory();
		VaisseauDetailDto vaisseau = objectFactory.createVaisseauDetailDto();
		vaisseau.setImmatriculation("immatriculation");
		vaisseau.setModele("modele");
		vaisseau.setCapaciteDeFret(5);
		vaisseau.setVitesse(4);
		vaisseau.setAutonomie(3);
		vaisseau.setNombreDePassagers((short) 2);
		vaisseau.setMultiplicateurHyperdrive(new Short((short) 1));
		vaisseau.setLocalisationHangar("hangar");
		vaisseau.setNumeroHangar(new Long(10));

		VaisseauTo vaisseauTo = mapper.mapToVaisseauTo(vaisseau);

		assertNotNull(vaisseauTo);
		assertEquals("immatriculation", vaisseauTo.getImmatriculation());
		assertEquals("modele", vaisseauTo.getModele());
		assertEquals(5, vaisseauTo.getCapaciteDeFret());
		assertEquals(4, vaisseauTo.getVitesse());
		assertEquals(3, vaisseauTo.getAutonomie());
		assertEquals(2, vaisseauTo.getNombreDePassagers());
		assertEquals(new Short((short) 1), vaisseauTo.getMultiplicateurHyperdrive());
		assertEquals("hangar", vaisseauTo.getLocalisationHangar());
		assertEquals(new Long(10), vaisseauTo.getNumeroHangar());
	}
}
