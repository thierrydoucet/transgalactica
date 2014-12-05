package org.transgalactica.info.data.motd.mapper;

import java.util.List;

import org.transgalactica.info.data.motd.bo.MessageTo;
import org.transgalactica.info.flux.motd.Message;
import org.transgalactica.info.flux.motd.Motd;

public interface MotdMapper {

	List<MessageTo> map(Motd messages);

	MessageTo map(Message message);
}
