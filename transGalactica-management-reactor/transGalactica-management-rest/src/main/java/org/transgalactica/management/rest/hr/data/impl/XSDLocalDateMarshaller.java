package org.transgalactica.management.rest.hr.data.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;

//TODO : a supprimer lorsque Jaxb prendra en compte java.time
@XmlTransient
public class XSDLocalDateMarshaller extends XmlAdapter<Date, LocalDate> {

	@Override
	public LocalDate unmarshal(Date date) throws Exception {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	@Override
	public Date marshal(LocalDate localDate) throws Exception {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}