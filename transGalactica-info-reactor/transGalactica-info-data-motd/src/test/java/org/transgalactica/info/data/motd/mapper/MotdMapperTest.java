package org.transgalactica.info.data.motd.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.transgalactica.info.data.motd.bo.MessageTo;
import org.transgalactica.info.data.motd.config.TestContext;
import org.transgalactica.info.data.motd.mapper.MotdMapper;
import org.transgalactica.info.flux.motd.Image;
import org.transgalactica.info.flux.motd.Message;
import org.transgalactica.info.flux.motd.Motd;
import org.transgalactica.info.flux.motd.ObjectFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class)
public class MotdMapperTest {

	@Autowired
	private MotdMapper motdMapper;

	@Test
	public void testMap_motd() throws DatatypeConfigurationException {
		ObjectFactory objectFactory = new ObjectFactory();
		Image image = objectFactory.createImage();
		image.setUrl("url");
		image.setTexteAlternatif("texte alternatif");
		Message message = objectFactory.createMessage();
		message.setId("id");
		message.setTitre("titre");
		message.setContenu("contenu");
		message.setDatePublication(DatatypeFactory.newInstance().newXMLGregorianCalendar(
				new GregorianCalendar(2012, 2, 29, 11, 9, 34)));
		message.setImage(image);
		Motd messages = objectFactory.createMotd();
		messages.getMessage().add(message);

		List<MessageTo> messageTos = motdMapper.map(messages);

		assertNotNull(messageTos);
		assertEquals(1, messageTos.size());
		assertNotNull(messageTos.get(0));
		assertEquals("id", messageTos.get(0).getId());
		assertEquals("titre", messageTos.get(0).getTitre());
		assertEquals("contenu", messageTos.get(0).getContenu());
		assertEquals(new GregorianCalendar(2012, 2, 29, 11, 9, 34).getTime(), messageTos.get(0).getDatePublication());
		assertNotNull(messageTos.get(0).getImage());
		assertEquals("url", messageTos.get(0).getImage().getUrl());
		assertEquals("texte alternatif", messageTos.get(0).getImage().getTexteAlternatif());
	}

	@Test
	public void testMap_message() throws DatatypeConfigurationException {
		ObjectFactory objectFactory = new ObjectFactory();
		Image image = objectFactory.createImage();
		image.setUrl("url");
		image.setTexteAlternatif("texte alternatif");
		Message message = objectFactory.createMessage();
		message.setId("id");
		message.setTitre("titre");
		message.setContenu("contenu");
		message.setDatePublication(DatatypeFactory.newInstance().newXMLGregorianCalendar(
				new GregorianCalendar(2012, 2, 29, 11, 9, 34)));
		message.setImage(image);

		MessageTo messageTo = motdMapper.map(message);

		assertNotNull(messageTo);
		assertEquals("id", messageTo.getId());
		assertEquals("titre", messageTo.getTitre());
		assertEquals("contenu", messageTo.getContenu());
		assertEquals(new GregorianCalendar(2012, 2, 29, 11, 9, 34).getTime(), messageTo.getDatePublication());
		assertNotNull(messageTo.getImage());
		assertEquals("url", messageTo.getImage().getUrl());
		assertEquals("texte alternatif", messageTo.getImage().getTexteAlternatif());
	}
}
