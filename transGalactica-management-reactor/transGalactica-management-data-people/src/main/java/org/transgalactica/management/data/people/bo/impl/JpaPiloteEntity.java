package org.transgalactica.management.data.people.bo.impl;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.people.bo.PiloteEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

@DataBean("org.transgalactica.management.data.people.bo.PiloteEntity")
@Getter
@Setter
@Entity
@DiscriminatorValue("PILOTE")
public class JpaPiloteEntity extends AbstractJpaEmployeEntity implements PiloteEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "heures_vol")
	private int nombreHeuresVol;

	protected JpaPiloteEntity() {
		setType(EmployeType.PILOTE);
	}
}
