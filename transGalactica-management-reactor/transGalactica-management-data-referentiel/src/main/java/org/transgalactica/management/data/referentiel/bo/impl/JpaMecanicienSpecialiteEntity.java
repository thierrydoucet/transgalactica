package org.transgalactica.management.data.referentiel.bo.impl;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.transgalactica.fwk.domain.bean.BusinessIdentifier;
import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;

@DataBean
@Data
@EqualsAndHashCode(of = "nomSpecialite")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "MECANICIENS_SPECIALITES")
@Cacheable
public class JpaMecanicienSpecialiteEntity implements MecanicienSpecialiteEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private Long technicalId;

	@BusinessIdentifier
	@Column(name = "nom")
	private String nomSpecialite;

}
