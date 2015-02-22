package org.transgalactica.info.rest.web;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.transgalactica.info.InfoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InfoApplication.class)
@WebAppConfiguration
public class MotdControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getAll() throws Exception {
		mockMvc.perform(get("/motd")).andExpect(status().isOk())//
				.andExpect(content().contentType("application/json;charset=UTF-8"))//
				.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	public void getById() throws Exception {
		mockMvc.perform(get("/motd/test")).andExpect(status().isOk())//
				.andExpect(content().contentType("application/json;charset=UTF-8"))//
				.andExpect(jsonPath("$.id").value("test"))//
				.andExpect(jsonPath("$.titre").value("Titre test"))//
				.andExpect(jsonPath("$.contenu").value("Contenu test"))//
				.andExpect(jsonPath("$.datePublication").value("2014-11-22T23:15"))//
				.andExpect(jsonPath("$.image.url").value("http://xx/test.png"))//
				.andExpect(jsonPath("$.image.texteAlternatif").value("Image test"));
	}

	@Test
	public void getById_notFound() throws Exception {
		mockMvc.perform(get("/motd/xxx")).andExpect(status().isNotFound());
	}
}
