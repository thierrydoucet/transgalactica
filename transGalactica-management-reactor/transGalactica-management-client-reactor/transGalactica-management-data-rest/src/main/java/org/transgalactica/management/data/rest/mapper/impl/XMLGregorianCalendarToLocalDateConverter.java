package org.transgalactica.management.data.rest.mapper.impl;

import java.time.LocalDate;
import java.time.ZoneId;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.dozer.DozerConverter;

public class XMLGregorianCalendarToLocalDateConverter extends DozerConverter<XMLGregorianCalendar, LocalDate> {

	public XMLGregorianCalendarToLocalDateConverter() {
		super(XMLGregorianCalendar.class, LocalDate.class);
	}

	@Override
	public XMLGregorianCalendar convertFrom(LocalDate source, XMLGregorianCalendar destination) {
		if (source == null) {
			return null;
		}
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(source.toString());
		}
		catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public LocalDate convertTo(XMLGregorianCalendar source, LocalDate destination) {
		if (source == null) {
			return null;
		}
		return source.toGregorianCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}