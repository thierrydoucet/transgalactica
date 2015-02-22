package org.transgalactica.info.data.motd.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DescriptiveResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.transgalactica.info.data.motd.TestContext;
import org.transgalactica.info.data.motd.bo.MessageTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class)
public class MotdRepositoryTest {

	@Autowired
	private MotdRepository motdDao;

	@Test
	@DirtiesContext
	public void testFindMessages_fichierAbsent() {
		ReflectionTestUtils.setField(motdDao, "motd", new DescriptiveResource("test-absent"));

		List<MessageTo> messages = motdDao.findAll();

		assertNotNull(messages);
		assertEquals(0, messages.size());
	}

	@Test
	public void testFindMessages() {
		List<MessageTo> messages = motdDao.findAll();

		assertNotNull(messages);
		assertEquals(2, messages.size());

		assertNotNull(messages.get(0));
		assertEquals("1", messages.get(0).getId());
		assertEquals("Message TU", messages.get(0).getTitre());
		assertEquals("Contenu test unitaires", messages.get(0).getContenu());
		assertNull(messages.get(0).getDatePublication());
		assertNotNull(messages.get(0).getImage());
		assertEquals(
				"http://localhost:8080/transGalactica-web/src/main/webapp/themes/default/images/transGalactica-logo.png",
				messages.get(0).getImage().getUrl());
		assertEquals("Logo Trans'Galactica", messages.get(0).getImage().getTexteAlternatif());
	}

	@Test
	public void testFindOne_absent() {
		MessageTo message = motdDao.findOne("absent");
		assertNull(message);
	}

	@Test
	public void testFindOne() {
		MessageTo message = motdDao.findOne("2");

		assertNotNull(message);
		assertEquals("2", message.getId());
		assertEquals("Message 2 TU", message.getTitre());
		assertEquals("Contenu test unitaires", message.getContenu());
		assertEquals(LocalDateTime.of(2012, 3, 29, 11, 9, 34), message.getDatePublication());
		assertNotNull(message.getImage());
	}
}
