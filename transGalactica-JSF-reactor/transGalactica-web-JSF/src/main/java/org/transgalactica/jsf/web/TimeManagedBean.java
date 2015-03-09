package org.transgalactica.jsf.web;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class TimeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDateTime now;

	@Inject
	private TimeService timeService;

	@PostConstruct
	public void initNow() {
		now = LocalDateTime.ofEpochSecond(timeService.getTime(), 0, ZoneOffset.UTC);
	}

	public LocalDateTime getNow() {
		return now;
	}
}