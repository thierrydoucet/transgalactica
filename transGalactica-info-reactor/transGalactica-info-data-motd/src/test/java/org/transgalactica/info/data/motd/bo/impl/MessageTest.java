package org.transgalactica.info.data.motd.bo.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class MessageTest {

	private BasicMessageTo m1, m2, m3;

	@Before
	public void createMessage() {
		m1 = new BasicMessageTo();
		m1.setId("1");
		m1.setContenu("C1");
		m2 = new BasicMessageTo();
		m2.setId("1");
		m2.setContenu("C2");
		m3 = new BasicMessageTo();
		m3.setId("3");
		m3.setContenu("C3");
	}

	@Test
	public void testEquals() {
		assertThat(m1, equalTo(m2));
		assertThat(m1, not(equalTo(m3)));
	}

	@Test
	public void testHascode() {
		assertThat(m1.hashCode(), equalTo(m2.hashCode()));
		assertThat(m1.hashCode(), not(equalTo(m3.hashCode())));
	}
}
