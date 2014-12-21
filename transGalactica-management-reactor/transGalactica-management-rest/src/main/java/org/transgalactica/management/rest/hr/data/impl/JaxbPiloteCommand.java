package org.transgalactica.management.rest.hr.data.impl;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;
import static lombok.AccessLevel.PROTECTED;
import static org.transgalactica.management.rest.Namespaces.HR_NAMESPACE;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.hr.data.PiloteCommand;

@DataBean
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@XmlType(name = "PiloteCommand", namespace = HR_NAMESPACE)
@XmlAccessorType(FIELD)
public class JaxbPiloteCommand extends JaxbEmployeCommand implements PiloteCommand {

	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	private int nombreHeuresVol;
}
