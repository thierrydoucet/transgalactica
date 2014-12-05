package org.transgalactica.info.data.motd.repository;

import java.util.List;

import org.transgalactica.info.data.motd.bo.MessageTo;

public interface MotdRepository {

	MessageTo findOne(String id);

	List<MessageTo> findAll();
}