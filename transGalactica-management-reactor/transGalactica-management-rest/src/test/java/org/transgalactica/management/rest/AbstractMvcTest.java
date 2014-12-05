package org.transgalactica.management.rest;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public abstract class AbstractMvcTest extends AbstractTransactionalTest {

	@Autowired
	private WebApplicationContext wac;
	protected MockMvc mockMvc;

	public AbstractMvcTest() {
		super();
	}

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

}