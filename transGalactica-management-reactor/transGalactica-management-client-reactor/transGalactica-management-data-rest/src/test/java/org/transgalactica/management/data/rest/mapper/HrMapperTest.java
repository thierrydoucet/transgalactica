package org.transgalactica.management.data.rest.mapper;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.transgalactica.management.data.rest.TestConfig;
import org.transgalactica.management.data.rest.bo.EmployeSummaryTo;
import org.transgalactica.management.data.rest.bo.EmployeTo;
import org.transgalactica.management.data.rest.bo.MecanicienTo;
import org.transgalactica.management.data.rest.bo.PiloteTo;
import org.transgalactica.management.data.rest.bo.impl.BasicMecanicienTo;
import org.transgalactica.management.data.rest.bo.impl.BasicPiloteTo;
import org.transgalactica.management.flux.rest.EmployeCommand;
import org.transgalactica.management.flux.rest.EmployeDto;
import org.transgalactica.management.flux.rest.EmployeType;
import org.transgalactica.management.flux.rest.EmployeVaisseau;
import org.transgalactica.management.flux.rest.MecanicienDetailDto;
import org.transgalactica.management.flux.rest.ObjectFactory;
import org.transgalactica.management.flux.rest.PiloteCommand;
import org.transgalactica.management.flux.rest.PiloteDetailDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class HrMapperTest {

	@Autowired
	private HrMapper mapper;

	@Test
	public void testMapToEmployeCommand_pilote() throws DatatypeConfigurationException {
		PiloteTo employeTo = BeanUtils.instantiateClass(BasicPiloteTo.class);
		employeTo.setNom("Nom");
		employeTo.setDateEmbauche(LocalDate.of(2012, 4, 4));
		employeTo.setNombreHeuresVol(23);

		EmployeCommand command = mapper.mapToEmployeCommand(employeTo);

		assertNotNull(command);
		assertThat(command, instanceOf(PiloteCommand.class));
		assertEquals("Nom", command.getNom());
		assertEquals(DatatypeFactory.newInstance().newXMLGregorianCalendar("2012-04-04"), command.getDateEmbauche());
		assertEquals(23, ((PiloteCommand) command).getNombreHeuresVol());
	}

	@Test
	public void testMapToEmployeCommand_mecanicien() throws DatatypeConfigurationException {
		MecanicienTo employeTo = BeanUtils.instantiateClass(BasicMecanicienTo.class);
		employeTo.setNom("Nom");
		employeTo.setDateEmbauche(LocalDate.of(2012, 4, 4));

		EmployeCommand command = mapper.mapToEmployeCommand(employeTo);

		assertNotNull(command);
		assertThat(command, not(instanceOf(PiloteCommand.class)));
		assertEquals("Nom", command.getNom());
		assertEquals(DatatypeFactory.newInstance().newXMLGregorianCalendar("2012-04-04"), command.getDateEmbauche());
	}

	@Test
	public void testMapToEmployeSummaryTo() throws DatatypeConfigurationException {
		ObjectFactory objectFactory = new ObjectFactory();
		EmployeDto employeDto = objectFactory.createEmployeDto();
		employeDto.setMatricule(1L);
		employeDto.setNom("Nom");
		LocalDate dateEmbauche = LocalDate.of(2012, 4, 4);
		employeDto.setDateEmbauche(DatatypeFactory.newInstance().newXMLGregorianCalendar(dateEmbauche.toString()));
		employeDto.setTypeEmploye(EmployeType.PILOTE);

		List<EmployeSummaryTo> employeSummaryTos = mapper.mapToEmployeSummaryTo(Collections.singletonList(employeDto));

		assertNotNull(employeSummaryTos);
		assertEquals(1, employeSummaryTos.size());
		assertEquals(new Long(1), employeSummaryTos.get(0).getMatricule());
		assertEquals("Nom", employeSummaryTos.get(0).getNom());
		assertEquals(dateEmbauche, employeSummaryTos.get(0).getDateEmbauche());
		assertEquals("PILOTE", employeSummaryTos.get(0).getTypeEmploye());
	}

	@Test
	public void testMapToEmployeTo_Mecanicien() throws DatatypeConfigurationException {
		ObjectFactory objectFactory = new ObjectFactory();
		MecanicienDetailDto employe = objectFactory.createMecanicienDetailDto();
		employe.setMatricule(1L);
		employe.setNom("Nom");
		employe.setTypeEmploye(EmployeType.MECANICIEN);
		LocalDate dateEmbauche = LocalDate.of(2012, 4, 4);
		employe.setDateEmbauche(DatatypeFactory.newInstance().newXMLGregorianCalendar(dateEmbauche.toString()));

		employe.setSpecialites(objectFactory.createMecanicienDetailDtoSpecialites());
		employe.getSpecialites().getSpecialite().add("Specialite");

		EmployeVaisseau vaisseau = objectFactory.createEmployeVaisseau();
		vaisseau.setCapaciteDeFret(1L);
		vaisseau.setImmatriculation("value");
		vaisseau.setModele("modele");
		vaisseau.setNombreDePassagers((short) 2);
		vaisseau.setAutonomie(3);
		vaisseau.setVitesse(4);
		employe.setVaisseaux(objectFactory.createMecanicienDetailDtoVaisseaux());
		employe.getVaisseaux().getVaisseau().add(vaisseau);

		EmployeTo employeTo = mapper.mapToEmployeTo(employe);

		assertNotNull(employeTo);
		assertThat(employeTo, instanceOf(MecanicienTo.class));
		assertEquals(new Long(1), employeTo.getMatricule());
		assertEquals("Nom", employeTo.getNom());
		assertEquals("MECANICIEN", employeTo.getTypeEmploye());
		assertEquals(dateEmbauche, employeTo.getDateEmbauche());

		assertNotNull(((MecanicienTo) employeTo).getSpecialites());
		assertEquals(1, ((MecanicienTo) employeTo).getSpecialites().size());
		assertEquals("Specialite", ((MecanicienTo) employeTo).getSpecialites().get(0));

		assertNotNull(employeTo.getVaisseaux());
		assertEquals(1, employeTo.getVaisseaux().size());
		assertEquals(1L, employeTo.getVaisseaux().get(0).getCapaciteDeFret());
		assertEquals("value", employeTo.getVaisseaux().get(0).getImmatriculation());
		assertEquals("modele", employeTo.getVaisseaux().get(0).getModele());
		assertEquals((short) 2, employeTo.getVaisseaux().get(0).getNombreDePassagers());
		assertEquals(3, employeTo.getVaisseaux().get(0).getAutonomie());
		assertEquals(4, employeTo.getVaisseaux().get(0).getVitesse());
	}

	@Test
	public void testMapToEmployeTo_Pilote() throws DatatypeConfigurationException {
		ObjectFactory objectFactory = new ObjectFactory();
		PiloteDetailDto employe = objectFactory.createPiloteDetailDto();
		employe.setMatricule(1L);
		employe.setNom("Nom");
		employe.setTypeEmploye(EmployeType.PILOTE);
		LocalDate dateEmbauche = LocalDate.of(2012, 4, 4);
		employe.setDateEmbauche(DatatypeFactory.newInstance().newXMLGregorianCalendar(dateEmbauche.toString()));
		employe.setNombreHeuresVol(12);

		EmployeVaisseau vaisseau = objectFactory.createEmployeVaisseau();
		vaisseau.setCapaciteDeFret(1L);
		vaisseau.setImmatriculation("value");
		vaisseau.setModele("modele");
		vaisseau.setNombreDePassagers((short) 2);
		vaisseau.setAutonomie(3);
		vaisseau.setVitesse(4);
		employe.setVaisseaux(objectFactory.createPiloteDetailDtoVaisseaux());
		employe.getVaisseaux().getVaisseau().add(vaisseau);

		EmployeTo employeTo = mapper.mapToEmployeTo(employe);

		assertNotNull(employeTo);
		assertThat(employeTo, instanceOf(PiloteTo.class));
		assertEquals(new Long(1), employeTo.getMatricule());
		assertEquals("Nom", employeTo.getNom());
		assertEquals("PILOTE", employeTo.getTypeEmploye());
		assertEquals(dateEmbauche, employeTo.getDateEmbauche());
		assertEquals(12, ((PiloteTo) employeTo).getNombreHeuresVol());

		assertNotNull(employeTo.getVaisseaux());
		assertEquals(1, employeTo.getVaisseaux().size());
		assertEquals(1L, employeTo.getVaisseaux().get(0).getCapaciteDeFret());
		assertEquals("value", employeTo.getVaisseaux().get(0).getImmatriculation());
		assertEquals("modele", employeTo.getVaisseaux().get(0).getModele());
		assertEquals((short) 2, employeTo.getVaisseaux().get(0).getNombreDePassagers());
		assertEquals(3, employeTo.getVaisseaux().get(0).getAutonomie());
		assertEquals(4, employeTo.getVaisseaux().get(0).getVitesse());
	}
}
