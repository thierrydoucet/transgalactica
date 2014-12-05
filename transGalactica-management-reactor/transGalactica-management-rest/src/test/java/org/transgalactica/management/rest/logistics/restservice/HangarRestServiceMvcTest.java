package org.transgalactica.management.rest.logistics.restservice;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.transgalactica.fwk.test.util.SecurityContextTestUtils;
import org.transgalactica.management.rest.AbstractMvcTest;

public class HangarRestServiceMvcTest extends AbstractMvcTest {

	@Before
	public void setAuthentification() {
		SecurityContextTestUtils.setUsernamePasswordAuthenticationTokenInSecurityContext("usename", "password",
				"ROLE_SUPER_GESTIONNAIRE");
	}

	@Test
	public void getAll() throws Exception {
		mockMvc.perform(get("/hangars").accept(APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$.hangars", hasSize(3)));
	}

	@Test
	public void getByNumero() throws Exception {
		mockMvc.perform(get("/hangars/3").accept(APPLICATION_XML)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentType("application/xml")) //
				.andExpect(content().xml(DARK_STAR));
	}

	@Test
	public void getByNumero_absent() throws Exception {
		mockMvc.perform(get("/hangars/-1").accept(APPLICATION_XML)) //
				.andExpect(status().isNotFound());
	}

	@Test
	public void create() throws Exception {
		String jsonCommand = "{\"localisation\":\"localisation test\",\"nombreEmplacements\":500}";
		mockMvc.perform(post("/hangars").contentType(APPLICATION_JSON).content(jsonCommand))//
				.andExpect(status().isCreated());

		mockMvc.perform(get("/hangars/4").accept(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.localisation").value("localisation test")) //
				.andExpect(jsonPath("$.nombreEmplacements").value(500)) //
				.andExpect(jsonPath("$.vaisseaux", empty()));
	}

	@Test
	public void save_xml() throws Exception {
		String xmlCommand = "<hangarCommand><localisation>localisation</localisation><nombreEmplacements>5</nombreEmplacements></hangarCommand>";
		mockMvc.perform(put("/hangars/1").contentType(APPLICATION_XML).content(xmlCommand)).andExpect(status().isOk());

		mockMvc.perform(get("/hangars/1").accept(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.localisation").value("localisation")) //
				.andExpect(jsonPath("$.nombreEmplacements").value(5)) //
				.andExpect(jsonPath("$.vaisseaux", hasSize(3)));
	}

	@Test
	public void delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/hangars/2")).andExpect(status().isOk());

		mockMvc.perform(get("/hangars/2")).andExpect(status().isNotFound());
	}

	@Test
	public void search() throws Exception {
		mockMvc.perform(get("/hangars/search?localisation=Arakis").accept(APPLICATION_XML)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentType("application/xml")) //
				.andExpect(content().xml(SEARCH_ARAKIS));
	}

	@Test
	public void addVaisseau() throws Exception {
		mockMvc.perform(post("/employes/1/vaisseaux/Leader rouge")).andExpect(status().isOk());

		mockMvc.perform(get("/employes/1").accept(APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$.vaisseaux[?(@.immatriculation == 'Leader rouge')]").exists());
	}

	@Test
	public void removeVaisseau() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/hangars/1/vaisseaux/Serenity")).andExpect(status().isOk());

		mockMvc.perform(get("/employes/1").accept(APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$.vaisseaux[?(@.immatriculation == 'Serenity')]").doesNotExist());
	}

	private static final String DARK_STAR = "<hangarDetail><localisation>Etoile noire</localisation><nombreEmplacements>1000</nombreEmplacements><numero>3</numero><vaisseaux><capaciteDeFret>0</capaciteDeFret><immatriculation>Dark Vador's Tie Advanced</immatriculation><modele>Tie Advanced</modele><nombreDePassagers>0</nombreDePassagers><autonomie>1000</autonomie><vitesse>103</vitesse></vaisseaux></hangarDetail>";

	private static final String SEARCH_ARAKIS = "<hangars><hangars><localisation>Arakis</localisation><nombreEmplacements>100</nombreEmplacements><numero>2</numero></hangars></hangars>";
}
