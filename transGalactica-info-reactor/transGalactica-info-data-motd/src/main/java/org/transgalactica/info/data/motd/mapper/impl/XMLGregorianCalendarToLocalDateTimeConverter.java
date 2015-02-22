package org.transgalactica.info.data.motd.mapper.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.xml.datatype.XMLGregorianCalendar;

import org.dozer.DozerConverter;

public class XMLGregorianCalendarToLocalDateTimeConverter extends DozerConverter<XMLGregorianCalendar, LocalDateTime> {

	public XMLGregorianCalendarToLocalDateTimeConverter() {
		super(XMLGregorianCalendar.class, LocalDateTime.class);
	}

	@Override
	public XMLGregorianCalendar convertFrom(LocalDateTime source, XMLGregorianCalendar destination) {
		throw new IllegalStateException("Should not be call");
	}

	@Override
	public LocalDateTime convertTo(XMLGregorianCalendar source, LocalDateTime destination) {
		if (source == null) {
			return null;
		}
		return source.toGregorianCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
}