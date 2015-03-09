package org.transgalactica.jsf.web;

import org.springframework.stereotype.Service;

@Service
public class TimeService {

	public long getTime() {
		return System.currentTimeMillis();
	}
}