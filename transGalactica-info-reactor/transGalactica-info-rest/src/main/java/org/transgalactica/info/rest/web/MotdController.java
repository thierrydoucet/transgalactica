package org.transgalactica.info.rest.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.transgalactica.info.data.motd.bo.MessageTo;
import org.transgalactica.info.data.motd.repository.MotdRepository;
import org.transgalactica.info.rest.RestPreconditions;

@RestController
@RequestMapping("/motd")
public class MotdController {

	@Autowired
	private MotdRepository motdRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<MessageTo> getAll() {
		return this.motdRepository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public MessageTo getById(@PathVariable String id) {
		return RestPreconditions.checkFound(this.motdRepository.findOne(id));
	}
}