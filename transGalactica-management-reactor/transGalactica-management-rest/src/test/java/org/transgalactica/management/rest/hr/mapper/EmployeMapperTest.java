package org.transgalactica.management.rest.hr.mapper;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauEntity;
import org.transgalactica.management.data.people.bo.BasicEmployeSummary;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.EmployeSearchCriteria;
import org.transgalactica.management.data.people.bo.EmployeSummary;
import org.transgalactica.management.data.people.bo.MecanicienEntity;
import org.transgalactica.management.data.people.bo.PiloteEntity;
import org.transgalactica.management.data.people.bo.impl.JpaMecanicienEntity;
import org.transgalactica.management.data.people.bo.impl.JpaPiloteEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;
import org.transgalactica.management.data.referentiel.bo.impl.JpaMecanicienSpecialiteEntity;
import org.transgalactica.management.rest.AbstractWebTest;
import org.transgalactica.management.rest.hr.data.EmployeCommand;
import org.transgalactica.management.rest.hr.data.EmployeDetailDto;
import org.transgalactica.management.rest.hr.data.EmployeDtos;
import org.transgalactica.management.rest.hr.data.MecanicienDetailDto;
import org.transgalactica.management.rest.hr.data.PiloteCommand;
import org.transgalactica.management.rest.hr.data.PiloteDetailDto;
import org.transgalactica.management.rest.hr.data.VaisseauDto;
import org.transgalactica.management.rest.hr.data.impl.JaxbEmployeCommand;
import org.transgalactica.management.rest.hr.data.impl.JaxbPiloteCommand;

/**
 * Classe de tests pour le mapper relatif aux employes.
 * 
 * @author Thierry
 */
public class EmployeMapperTest extends AbstractWebTest {

	@Autowired
	private EmployeMapper mapper;

	@Test
	public void testMapEmployeCommandToEntity_Mecanicien() {
		EmployeCommand command = BeanUtils.instantiateClass(JaxbEmployeCommand.class);

		command.setNom("Nom");
		Date dateEmbauche = new Date();
		command.setDateEmbauche(dateEmbauche);

		EmployeEntity entity = mapper.mapEmployeCommandToEntity(command);

		assertNotNull(entity);
		assertEquals("Nom", entity.getNom());
		assertEquals(dateEmbauche, entity.getDateEmbauche());
		assertEquals(EmployeType.MECANICIEN, entity.getType());
	}

	@Test
	public void testMapEmployeCommandToEntity_Pilote() {
		PiloteCommand command = BeanUtils.instantiateClass(JaxbPiloteCommand.class);

		command.setNom("Nom");
		Date dateEmbauche = new Date();
		command.setDateEmbauche(dateEmbauche);
		command.setNombreHeuresVol(50);

		EmployeEntity entity = mapper.mapEmployeCommandToEntity(command);

		assertNotNull(entity);
		assertEquals("Nom", entity.getNom());
		assertEquals(dateEmbauche, entity.getDateEmbauche());
		assertEquals(EmployeType.PILOTE, entity.getType());
		assertThat(entity, instanceOf(PiloteEntity.class));
		assertEquals(50, ((PiloteEntity) entity).getNombreHeuresVol());
	}

	@Test
	public void testMapToRechercheEmployeCriteres() {
		Date dateEmbaucheDebut = new Date();
		Date dateEmbaucheFin = DateUtils.addDays(new Date(), 1);

		EmployeSearchCriteria criteres = mapper.mapToRechercheEmployeCriteres("Nom%", dateEmbaucheDebut,
				dateEmbaucheFin, "immatriculation");

		assertNotNull(criteres);
		assertEquals("Nom%", criteres.getNomEmploye());
		assertEquals(dateEmbaucheDebut, criteres.getDateEmbaucheEmployeDebut());
		assertEquals(dateEmbaucheFin, criteres.getDateEmbaucheEmployeFin());
		assertEquals("immatriculation", criteres.getImmatriculationVaisseau());
	}

	@Test
	public void testMapToEmployeDtos() throws BeanInstantiationException, NoSuchMethodException, SecurityException {
		Date dateEmbauche = new Date();
		EmployeSummary projection = BeanUtils.instantiateClass(
				BasicEmployeSummary.class.getConstructor(Long.class, String.class, Date.class, EmployeType.class), 10L,
				"Nom", dateEmbauche, EmployeType.PILOTE);

		EmployeDtos dtos = mapper.mapToEmployeDtos(Arrays.asList(projection));

		assertNotNull(dtos);
		assertEquals(1, dtos.getEmployes().size());
		assertEquals(new Long(10), dtos.getEmployes().get(0).getMatricule());
		assertEquals("Nom", dtos.getEmployes().get(0).getNom());
		assertEquals(dateEmbauche, dtos.getEmployes().get(0).getDateEmbauche());
		assertEquals(EmployeType.PILOTE, dtos.getEmployes().get(0).getTypeEmploye());
	}

	@Test
	public void testMapToEmployeDetailDto_Pilote() {
		Date dateEmbauche = new Date();
		PiloteEntity entity = BeanUtils.instantiateClass(JpaPiloteEntity.class);
		((JpaPiloteEntity) entity).setMatricule(new Long(10));
		entity.setNom("Nom");
		entity.setDateEmbauche(dateEmbauche);
		entity.setNombreHeuresVol(20);

		VaisseauEntity vaisseau = BeanUtils.instantiateClass(JpaVaisseauEntity.class);
		vaisseau.setCapaciteDeFret(1);
		vaisseau.setImmatriculation("immatriculation");
		vaisseau.setModele("modele");
		vaisseau.setNombreDePassagers((short) 10);
		vaisseau.setAutonomie(100);
		vaisseau.setVitesse(1000);
		entity.addVaisseau(vaisseau);

		EmployeDetailDto dto = mapper.mapToEmployeDetailDto(entity);

		assertNotNull(dto);
		Assert.assertThat(dto, instanceOf(PiloteDetailDto.class));
		assertEquals(new Long(10), dto.getMatricule());
		assertEquals("Nom", dto.getNom());
		assertEquals(dateEmbauche, dto.getDateEmbauche());
		assertEquals(20, ((PiloteDetailDto) dto).getNombreHeuresVol());

		assertEquals(1, dto.getVaisseaux().size());
		VaisseauDto vaisseauDto = dto.getVaisseaux().iterator().next();
		assertEquals(1, vaisseauDto.getCapaciteDeFret());
		assertEquals("immatriculation", vaisseauDto.getImmatriculation());
		assertEquals("modele", vaisseauDto.getModele());
		assertEquals((short) 10, vaisseauDto.getNombreDePassagers());
		assertEquals(100, vaisseauDto.getAutonomie());
		assertEquals(1000, vaisseauDto.getVitesse());
	}

	@Test
	public void testMapToEmployeDetailDto_Mecanicien() {
		Date dateEmbauche = new Date();
		MecanicienEntity entity = BeanUtils.instantiateClass(JpaMecanicienEntity.class);
		((JpaMecanicienEntity) entity).setMatricule(new Long(10));
		entity.setNom("Nom");
		entity.setDateEmbauche(dateEmbauche);

		MecanicienSpecialiteEntity specialiteEntity = BeanUtils.instantiateClass(JpaMecanicienSpecialiteEntity.class);
		ReflectionTestUtils.setField(specialiteEntity, "nomSpecialite", "nomSpecialite");
		entity.addSpecialite(specialiteEntity);

		VaisseauEntity vaisseau = BeanUtils.instantiateClass(JpaVaisseauEntity.class);
		vaisseau.setCapaciteDeFret(1);
		vaisseau.setImmatriculation("immatriculation");
		vaisseau.setModele("modele");
		vaisseau.setNombreDePassagers((short) 10);
		vaisseau.setAutonomie(100);
		vaisseau.setVitesse(1000);
		entity.addVaisseau(vaisseau);

		EmployeDetailDto dto = mapper.mapToEmployeDetailDto(entity);

		assertNotNull(dto);
		Assert.assertThat(dto, instanceOf(MecanicienDetailDto.class));
		assertEquals(new Long(10), dto.getMatricule());
		assertEquals("Nom", dto.getNom());
		assertEquals(dateEmbauche, dto.getDateEmbauche());

		assertNotNull(((MecanicienDetailDto) dto).getSpecialites());
		assertEquals(1, ((MecanicienDetailDto) dto).getSpecialites().size());
		assertEquals("nomSpecialite", ((MecanicienDetailDto) dto).getSpecialites().iterator().next());

		assertEquals(1, dto.getVaisseaux().size());
		VaisseauDto vaisseauDto = dto.getVaisseaux().iterator().next();
		assertEquals(1, vaisseauDto.getCapaciteDeFret());
		assertEquals("immatriculation", vaisseauDto.getImmatriculation());
		assertEquals("modele", vaisseauDto.getModele());
		assertEquals((short) 10, vaisseauDto.getNombreDePassagers());
		assertEquals(100, vaisseauDto.getAutonomie());
		assertEquals(1000, vaisseauDto.getVitesse());
	}
}
