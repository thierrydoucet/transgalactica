package org.transgalactica.data.rest.mapper.impl;

import org.dozer.DozerConverter;
import org.transgalactica.flux.rest.EmployeType;

public class EmployeTypeToStringConverter extends DozerConverter<EmployeType, String> {

	public EmployeTypeToStringConverter() {
		super(EmployeType.class, String.class);
	}

	@Override
	public EmployeType convertFrom(String source, EmployeType destination) {
		return EmployeType.valueOf(source);
	}

	@Override
	public String convertTo(EmployeType source, String destination) {
		return source.name();
	}
}
