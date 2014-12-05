package org.transgalactica.info.data.motd.mapper.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.fwk.domain.stereotype.Mapper;
import org.transgalactica.info.data.motd.bo.MessageTo;
import org.transgalactica.info.data.motd.mapper.MotdMapper;
import org.transgalactica.info.flux.motd.Message;
import org.transgalactica.info.flux.motd.Motd;

@Mapper
public class DozerMotdMapper implements MotdMapper {

	@Autowired
	private org.dozer.Mapper mapper;

	protected DozerMotdMapper() {
	}

	@Override
	public List<MessageTo> map(Motd messages) {
		if (messages == null) {
			return Collections.emptyList();
		}
		List<MessageTo> messageTos = new ArrayList<MessageTo>(messages.getMessage().size());
		for (Message message : messages.getMessage()) {
			MessageTo messageTo = map(message);
			messageTos.add(messageTo);
		}
		return messageTos;
	}

	@Override
	public MessageTo map(Message message) {
		if (message == null) {
			return null;
		}
		return mapper.map(message, MessageTo.class);
	}
}
