package org.transgalactica.info.data.motd.repository.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.transgalactica.info.data.motd.bo.MessageTo;
import org.transgalactica.info.data.motd.mapper.MotdMapper;
import org.transgalactica.info.data.motd.repository.MotdReadException;
import org.transgalactica.info.data.motd.repository.MotdRepository;
import org.transgalactica.info.flux.motd.Message;
import org.transgalactica.info.flux.motd.Motd;

@Repository
public class ResourceMotdRepository implements MotdRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceMotdRepository.class);

	private JAXBContext jaxbcontext = null;

	@Value("${motd.url}")
	private Resource motd;

	@Autowired
	private MotdMapper mapperMotd;

	protected ResourceMotdRepository() {
	}

	@PostConstruct
	protected void createJaxbContext() throws JAXBException {
		if (jaxbcontext == null) {
			jaxbcontext = JAXBContext.newInstance(Motd.class);
		}
	}

	@Override
	public List<MessageTo> findAll() {
		Motd messages = unmarshal();
		return mapperMotd.map(messages);
	}

	@Override
	public MessageTo findOne(final String id) {
		// TODO lambda expression
		Message message = CollectionUtils.find(unmarshal().getMessage(), new Predicate<Message>() {
			@Override
			public boolean evaluate(Message message) {
				return message.getId().equals(id);
			}
		});
		return mapperMotd.map(message);
	}

	private Motd unmarshal() {
		if (motd == null || !motd.exists()) {
			LOGGER.info("Ressource motd ({}) inexistante", motd);
			return null;
		}
		Motd messages = null;
		try (InputStream is = motd.getInputStream()) {
			Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
			messages = unmarshaller.unmarshal(new StreamSource(is), Motd.class).getValue();
		}
		catch (JAXBException e) {
			throw new MotdReadException("Erreur lors de l'interpretation du flux Motd", e);
		}
		catch (IOException e) {
			throw new MotdReadException("Erreur lors de la lecture du flux Motd", e);
		}
		return messages;
	}
}
