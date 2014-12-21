package org.transgalactica.management.rest.hr.restservice;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.transgalactica.fwk.test.util.SecurityContextTestUtils;
import org.transgalactica.management.rest.AbstractMvcTest;

public class EmployeRestServiceMvcTest extends AbstractMvcTest {

	private TimeZone defaultTimeZone;

	@Before
	public void setTimeZone() {
		defaultTimeZone = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
	}

	@After
	public void setRestoreTimeZone() {
		TimeZone.setDefault(defaultTimeZone);
	}

	@Before
	public void setAuthentification() {
		SecurityContextTestUtils.setUsernamePasswordAuthenticationTokenInSecurityContext("usename", "password",
				"ROLE_SUPER_GESTIONNAIRE");
	}

	@Test
	public void getByMatricule_pilote() throws Exception {
		mockMvc.perform(get("/employes/1").accept(APPLICATION_XML)) //
				.andExpect(status().isOk()) //
				.andExpect(header().string("TransGalactica-Content-Type", "PiloteDetail")) //
				.andExpect(content().contentType("application/xml")) //
				.andExpect(content().xml(HAN_SOLO));
	}

	@Test
	public void getByMatricule_mecanicien() throws Exception {
		mockMvc.perform(get("/employes/2").accept(APPLICATION_XML)) //
				.andExpect(status().isOk()) //
				.andExpect(header().string("TransGalactica-Content-Type", "MecanicienDetail")) //
				.andExpect(content().contentType("application/xml")) //
				.andExpect(content().xml(CHEWBACCA));
	}

	@Test
	public void getByMatricule_absent() throws Exception {
		mockMvc.perform(get("/employes/-1").accept(APPLICATION_XML)) //
				.andExpect(status().isNotFound());
	}

	@Test
	@DirtiesContext
	// reinit database ...
	public void create_pilote_xml() throws Exception {
		String xmlCommand = "<employeCommand><nom>nom test xml</nom><dateEmbauche>2013-01-23T00:00:00+01:00</dateEmbauche><nombreHeuresVol>12</nombreHeuresVol></employeCommand>";
		mockMvc.perform(post("/employes") //
				.header("TransGalactica-Content-Type", "PiloteCommand") //
				.contentType(APPLICATION_XML) //
				.content(xmlCommand)) //
				.andExpect(status().isCreated());

		mockMvc.perform(get("/employes/8").accept(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.nom").value("nom test xml")) //
				.andExpect(jsonPath("$.dateEmbauche").value(1358895600000L)) //
				.andExpect(jsonPath("$.typeEmploye").value("PILOTE")) //
				.andExpect(jsonPath("$.nombreHeuresVol").value(12));
	}

	@Test
	@DirtiesContext
	// reinit database ...
	public void create_mecanicien_json() throws Exception {
		String jsonCommand = "{\"nom\":\"nom test\",\"dateEmbauche\":\"2013-01-23T00:00:00+01:00\"}";
		mockMvc.perform(post("/employes")//
				.header("TransGalactica-Content-Type", "EmployeCommand")//
				.contentType(APPLICATION_JSON)//
				.content(jsonCommand)) //
				.andExpect(status().isCreated());

		mockMvc.perform(get("/employes/8").accept(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.nom").value("nom test"))
				.andExpect(jsonPath("$.dateEmbauche").value(1358895600000L)) //
				.andExpect(jsonPath("$.typeEmploye").value("MECANICIEN"));
	}

	@Test
	public void save_pilote_json() throws Exception {
		String jsonCommand = "{\"nom\":\"nvx nom\",\"dateEmbauche\":\"1977-06-09T00:00:00+02:00\",\"nombreHeuresVol\":1234}";
		mockMvc.perform(put("/employes/1")//
				.header("TransGalactica-Content-Type", "PiloteCommand")//
				.contentType(APPLICATION_JSON) //
				.content(jsonCommand))//
				.andExpect(status().isOk());

		mockMvc.perform(get("/employes/1").accept(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.nom").value("nvx nom"))
				.andExpect(jsonPath("$.dateEmbauche").value(234655200000L))
				.andExpect(jsonPath("$.typeEmploye").value("PILOTE"))
				.andExpect(jsonPath("$.nombreHeuresVol").value(1234))//
				.andExpect(jsonPath("$.vaisseaux", hasSize(1)));
	}

	@Test
	public void save_mecanicien_xml() throws Exception {
		String xmlCommand = "<employeCommand><nom>nvx nom</nom><dateEmbauche>1977-06-09T00:00:00+02:00</dateEmbauche></employeCommand>";
		mockMvc.perform(put("/employes/2")//
				.header("TransGalactica-Content-Type", "EmployeCommand") //
				.contentType(APPLICATION_XML) //
				.content(xmlCommand))//
				.andExpect(status().isOk());

		mockMvc.perform(get("/employes/2").accept(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.nom").value("nvx nom"))
				.andExpect(jsonPath("$.dateEmbauche").value(234655200000L))
				.andExpect(jsonPath("$.typeEmploye").value("MECANICIEN"))
				.andExpect(jsonPath("$.specialites", hasSize(1))) //
				.andExpect(jsonPath("$.vaisseaux", hasSize(1)));
	}

	@Test
	public void delete_mecanicien() throws Exception {
		mockMvc.perform(delete("/employes/2")) //
				.andExpect(status().isOk());

		mockMvc.perform(get("/employes/2")) //
				.andExpect(status().isNotFound());
	}

	@Test
	public void search() throws Exception {
		mockMvc.perform(get("/employes?nomEmploye=Chew%").accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.employes").value(hasSize(1)))
				.andExpect(jsonPath("$.employes[0].nom").value("Chewbacca"))
				.andExpect(jsonPath("$.employes[0].matricule").value(2))
				.andExpect(
						jsonPath("$.employes[0].dateEmbauche").value(
								new GregorianCalendar(1977, 5, 9).getTime().getTime()))
				.andExpect(jsonPath("$.employes[0].typeEmploye").value("MECANICIEN"));
	}

	@Test
	public void addVaisseau() throws Exception {
		mockMvc.perform(post("/employes/2/vaisseaux/Flying Bird")) //
				.andExpect(status().isOk());

		mockMvc.perform(get("/employes/2").accept(APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$.vaisseaux[?(@.immatriculation == 'Flying Bird')]").exists());
	}

	@Test
	public void removeVaisseau() throws Exception {
		mockMvc.perform(delete("/employes/1/vaisseaux/Faucon Millenium")) //
				.andExpect(status().isOk());

		mockMvc.perform(get("/employes/1").accept(APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$.vaisseaux[?(@.immatriculation == 'Faucon Millenium')]").doesNotExist());
	}

	@Test
	public void addMecanicienSpecialite() throws Exception {
		mockMvc.perform(post("/employes/2/specialites/Moteur")) //
				.andExpect(status().isOk());

		mockMvc.perform(get("/employes/2").accept(APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$.specialites[*]", hasItem("Moteur")));
	}

	@Test
	public void removeMecanicienSpecialite() throws Exception {
		mockMvc.perform(delete("/employes/2/specialites/Armement")) //
				.andExpect(status().isOk());

		mockMvc.perform(get("/employes/2").accept(APPLICATION_JSON)) //
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.specialites[*]", not(hasItem("Armement"))));
	}

	private static final String HAN_SOLO = "<employeDetail>" //
			+ "<dateEmbauche>1977-06-09T00:00:00Z</dateEmbauche>" //
			+ "<matricule>1</matricule>" //
			+ "<nom>Han Solo</nom>" //
			+ "<typeEmploye>PILOTE</typeEmploye>" //
			+ "<nombreHeuresVol>542</nombreHeuresVol>" //
			+ "<vaisseaux>" //
			+ "<vaisseau>" //
			+ "	<capaciteDeFret>100000</capaciteDeFret>" //
			+ "	<immatriculation>Faucon Millenium</immatriculation>" //
			+ "	<modele>cargo YT-1300</modele>" //
			+ "	<nombreDePassagers>6</nombreDePassagers>" //
			+ "	<autonomie>100000</autonomie>" //
			+ "	<vitesse>105</vitesse>" //
			+ "</vaisseau>" //
			+ "</vaisseaux>" //
			+ "</employeDetail>";

	private static final String CHEWBACCA = "<employeDetail>" //
			+ "<dateEmbauche>1977-06-09T00:00:00Z</dateEmbauche>" //
			+ "<matricule>2</matricule>" //
			+ "<nom>Chewbacca</nom>" //
			+ "<typeEmploye>MECANICIEN</typeEmploye>" //
			+ "<specialites>" //
			+ " <specialite>Armement</specialite>" //
			+ "</specialites>" //
			+ "<vaisseaux>" //
			+ "<vaisseau>" //
			+ "	<capaciteDeFret>100000</capaciteDeFret>" //
			+ "	<immatriculation>Faucon Millenium</immatriculation>" //
			+ "	<modele>cargo YT-1300</modele>" //
			+ "	<nombreDePassagers>6</nombreDePassagers>" //
			+ "	<autonomie>100000</autonomie>" //
			+ "	<vitesse>105</vitesse>" //
			+ "</vaisseau>" //
			+ "</vaisseaux>" //
			+ "</employeDetail>";
}
