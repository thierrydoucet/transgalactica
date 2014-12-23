package org.transgalactica.data.rest.test.mapper;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.data.rest.bo.EmployeSummaryTo;
import org.transgalactica.data.rest.bo.EmployeTo;
import org.transgalactica.data.rest.bo.MecanicienTo;
import org.transgalactica.data.rest.bo.PiloteTo;
import org.transgalactica.data.rest.bo.impl.BasicMecanicienTo;
import org.transgalactica.data.rest.bo.impl.BasicPiloteTo;
import org.transgalactica.data.rest.mapper.HrMapper;
import org.transgalactica.flux.rest.EmployeCommand;
import org.transgalactica.flux.rest.EmployeDto;
import org.transgalactica.flux.rest.EmployeType;
import org.transgalactica.flux.rest.EmployeVaisseau;
import org.transgalactica.flux.rest.MecanicienDetailDto;
import org.transgalactica.flux.rest.ObjectFactory;
import org.transgalactica.flux.rest.PiloteCommand;
import org.transgalactica.flux.rest.PiloteDetailDto;
import org.transgalactica.test.AbstractContextTest;

public class HrMapperTest extends AbstractContextTest {

	@Autowired
	private HrMapper mapper;

	@Test
	public void testMapToEmployeCommand_pilote() throws DatatypeConfigurationException {
		PiloteTo employeTo = BeanUtils.instantiateClass(BasicPiloteTo.class);
		GregorianCalendar dateEmbauche = new GregorianCalendar(2012, 3, 4);
		employeTo.setNom("Nom");
		employeTo.setDateEmbauche(dateEmbauche.getTime());
		employeTo.setNombreHeuresVol(23);

		EmployeCommand command = mapper.mapToEmployeCommand(employeTo);

		assertNotNull(command);
		assertThat(command, instanceOf(PiloteCommand.class));
		assertEquals("Nom", command.getNom());
		assertEquals(DatatypeFactory.newInstance().newXMLGregorianCalendar(dateEmbauche), command.getDateEmbauche());
		assertEquals(23, ((PiloteCommand) command).getNombreHeuresVol());
	}

	@Test
	public void testMapToEmployeCommand_mecanicien() throws DatatypeConfigurationException {
		MecanicienTo employeTo = BeanUtils.instantiateClass(BasicMecanicienTo.class);
		GregorianCalendar dateEmbauche = new GregorianCalendar(2012, 3, 4);
		employeTo.setNom("Nom");
		employeTo.setDateEmbauche(dateEmbauche.getTime());

		EmployeCommand command = mapper.mapToEmployeCommand(employeTo);

		assertNotNull(command);
		assertThat(command, not(instanceOf(PiloteCommand.class)));
		assertEquals("Nom", command.getNom());
		assertEquals(DatatypeFactory.newInstance().newXMLGregorianCalendar(dateEmbauche), command.getDateEmbauche());
	}

	@Test
	public void testMapToEmployeSummaryTo() throws DatatypeConfigurationException {
		ObjectFactory objectFactory = new ObjectFactory();
		EmployeDto employeDto = objectFactory.createEmployeDto();
		employeDto.setMatricule(1L);
		employeDto.setNom("Nom");
		GregorianCalendar dateEmbauche = new GregorianCalendar(2012, 3, 4);
		employeDto.setDateEmbauche(DatatypeFactory.newInstance().newXMLGregorianCalendar(dateEmbauche));
		employeDto.setTypeEmploye(EmployeType.PILOTE);

		List<EmployeSummaryTo> employeSummaryTos = mapper.mapToEmployeSummaryTo(Collections.singletonList(employeDto));

		assertNotNull(employeSummaryTos);
		assertEquals(1, employeSummaryTos.size());
		assertEquals(new Long(1), employeSummaryTos.get(0).getMatricule());
		assertEquals("Nom", employeSummaryTos.get(0).getNom());
		assertEquals(dateEmbauche.getTime(), employeSummaryTos.get(0).getDateEmbauche());
		assertEquals("PILOTE", employeSummaryTos.get(0).getTypeEmploye());
	}

	@Test
	public void testMapToEmployeTo_Mecanicien() throws DatatypeConfigurationException {
		ObjectFactory objectFactory = new ObjectFactory();
		MecanicienDetailDto employe = objectFactory.createMecanicienDetailDto();
		employe.setMatricule(1L);
		employe.setNom("Nom");
		employe.setTypeEmploye(EmployeType.MECANICIEN);
		GregorianCalendar dateEmbauche = new GregorianCalendar(2012, 3, 4);
		employe.setDateEmbauche(DatatypeFactory.newInstance().newXMLGregorianCalendar(dateEmbauche));

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
		assertEquals(dateEmbauche.getTime(), employeTo.getDateEmbauche());

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
		GregorianCalendar dateEmbauche = new GregorianCalendar(2012, 3, 4);
		employe.setDateEmbauche(DatatypeFactory.newInstance().newXMLGregorianCalendar(dateEmbauche));
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
		assertEquals(dateEmbauche.getTime(), employeTo.getDateEmbauche());
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
