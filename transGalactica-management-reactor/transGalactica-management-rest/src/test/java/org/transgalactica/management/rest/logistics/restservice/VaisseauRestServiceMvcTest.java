package org.transgalactica.management.rest.logistics.restservice;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.transgalactica.fwk.test.util.SecurityContextTestUtils;
import org.transgalactica.management.rest.AbstractMvcTest;

public class VaisseauRestServiceMvcTest extends AbstractMvcTest {

	@Before
	public void setAuthentification() {
		SecurityContextTestUtils.setUsernamePasswordAuthenticationTokenInSecurityContext("usename", "password",
				"ROLE_SUPER_GESTIONNAIRE");
	}

	@Test
	public void getByImmatriculation() throws Exception {
		mockMvc.perform(get("/vaisseaux/Flying Bird").accept(APPLICATION_XML)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentType("application/xml")) //
				.andExpect(content().xml(FLYING_BIRD));
	}

	@Test
	public void getByImmatriculation_absent() throws Exception {
		mockMvc.perform(get("/vaisseaux/sqdkfjqs").accept(APPLICATION_XML)) //
				.andExpect(status().isNotFound());
	}

	@Test
	public void exists() throws Exception {
		mockMvc.perform(get("/vaisseaux/Flying Bird").accept(APPLICATION_XML)) //
				.andExpect(status().isOk());
	}

	@Test
	public void exists_absent() throws Exception {
		mockMvc.perform(get("/vaisseaux/sqdkfjqs").accept(APPLICATION_XML)) //
				.andExpect(status().isNotFound());
	}

	@Test
	public void create() throws Exception {
		String jsonCommand = "{\"capaciteDeFret\":1000,\"immatriculation\":\"immatriculation creation\",\"modele\":\"modele creation\",\"multiplicateurHyperdrive\":5,\"nombreDePassagers\":100,\"autonomie\":10000,\"vitesse\":2000}";
		mockMvc.perform(post("/vaisseaux").contentType(APPLICATION_JSON).content(jsonCommand)) //
				.andExpect(status().isCreated());

		mockMvc.perform(get("/vaisseaux/immatriculation creation").accept(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.capaciteDeFret").value(1000))
				.andExpect(jsonPath("$.immatriculation").value("immatriculation creation"))
				.andExpect(jsonPath("$.modele").value("modele creation"))
				.andExpect(jsonPath("$.multiplicateurHyperdrive").value(5))
				.andExpect(jsonPath("$.nombreDePassagers").value(100)).andExpect(jsonPath("$.autonomie").value(10000))
				.andExpect(jsonPath("$.vitesse").value(2000));
	}

	@Test
	public void save() throws Exception {
		String jsonCommand = "{\"capaciteDeFret\":1000,\"immatriculation\":\"immatriculation maj\",\"modele\":\"modele maj\",\"multiplicateurHyperdrive\":5,\"nombreDePassagers\":100,\"autonomie\":10000,\"vitesse\":2000}";
		mockMvc.perform(put("/vaisseaux/Faucon Millenium").contentType(APPLICATION_JSON).content(jsonCommand))
				.andExpect(status().isOk());

		mockMvc.perform(get("/vaisseaux/immatriculation maj").accept(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.capaciteDeFret").value(1000))
				.andExpect(jsonPath("$.immatriculation").value("immatriculation maj"))
				.andExpect(jsonPath("$.modele").value("modele maj"))
				.andExpect(jsonPath("$.multiplicateurHyperdrive").value(5))
				.andExpect(jsonPath("$.nombreDePassagers").value(100)).andExpect(jsonPath("$.autonomie").value(10000))
				.andExpect(jsonPath("$.vitesse").value(2000));
	}

	@Test
	public void delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/vaisseaux/Flying Bird")).andExpect(status().isOk());

		mockMvc.perform(get("/vaisseaux/Flying Bird")).andExpect(status().isNotFound());
	}

	@Test
	public void getEnTransit() throws Exception {
		// querystring : transit=, transit devrait etre suffisant, mais
		// l'implementation de request MockMvc ne detecte pas le paramètre
		mockMvc.perform(get("/vaisseaux?transit=").accept(APPLICATION_XML)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentType("application/xml")) //
				.andExpect(xpath("count(/vaisseaux/*)").number(2D)) //
				// Contenu complet testé dans "search"
				.andExpect(xpath("/vaisseaux/*[1]/immatriculation").string("Leader rouge"));
	}

	@Test
	public void search() throws Exception {
		mockMvc.perform(
				get("/vaisseaux?immatriculation=Fau%&modele=cargo%&intergalactique=true").accept(APPLICATION_XML))//
				.andExpect(status().isOk()) //
				.andExpect(content().contentType("application/xml")) //
				.andExpect(content().xml(SEARCH_FAUCON_MILLENIUM));
	}

	@Test
	public void search_validationError() throws Exception {
		mockMvc.perform(get("/vaisseaux").accept(APPLICATION_XML))//
				.andExpect(status().isBadRequest());
	}

	private static final String FLYING_BIRD = "<vaisseauDetail><immatriculation>Flying Bird</immatriculation><localisationHangar>Alderaan</localisationHangar><modele>cargo YT-1300</modele><capaciteDeFret>100000</capaciteDeFret><nombreDePassagers>6</nombreDePassagers><numeroHangar>1</numeroHangar><autonomie>100000</autonomie><vitesse>95</vitesse></vaisseauDetail>";

	private static final String SEARCH_FAUCON_MILLENIUM = "<vaisseaux><vaisseau><immatriculation>Faucon Millenium</immatriculation><localisationHangar>Alderaan</localisationHangar><modele>cargo YT-1300</modele></vaisseau></vaisseaux>";
}
