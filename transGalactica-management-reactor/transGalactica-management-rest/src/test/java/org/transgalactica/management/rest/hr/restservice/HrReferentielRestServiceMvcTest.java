package org.transgalactica.management.rest.hr.restservice;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Before;
import org.junit.Test;
import org.transgalactica.fwk.test.util.SecurityContextTestUtils;
import org.transgalactica.management.rest.AbstractMvcTest;

public class HrReferentielRestServiceMvcTest extends AbstractMvcTest {

	@Before
	public void setAuthentification() {
		SecurityContextTestUtils.setUsernamePasswordAuthenticationTokenInSecurityContext("usename", "password",
				"ROLE_SUPER_GESTIONNAIRE");
	}

	@Test
	public void getEmployeTypes() throws Exception {
		mockMvc.perform(get("/hrreferentiel/employetypes").accept(APPLICATION_JSON).characterEncoding("UTF-8")) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$.employeTypes").value(hasSize(2))) //
				.andExpect(jsonPath("$.employeTypes[0]").exists()) //
				.andExpect(jsonPath("$.employeTypes[0]").value("PILOTE"));
	}

	@Test
	public void getMecanicienSpecialites() throws Exception {
		mockMvc.perform(get("/hrreferentiel/mecanicienspecialites").accept(APPLICATION_XML)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentType("application/xml")) //
				.andExpect(xpath("count(/mecanicienSpecialites/*)").number(5D)) //
				.andExpect(xpath("/mecanicienSpecialites/*[1]").string("Armement"));
	}
}