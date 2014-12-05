package org.transgalactica.management.data.materiel.bo.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.transgalactica.fwk.domain.bean.BusinessIdentifier;
import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;

@DataBean("org.transgalactica.management.data.materiel.bo.VaisseauEntity")
@Data
@EqualsAndHashCode(of = "immatriculation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "VAISSEAUX")
@Inheritance(strategy = InheritanceType.JOINED)
public class JpaVaisseauEntity implements VaisseauEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long technicalId;

	@BusinessIdentifier
	@Column(name = "immatriculation")
	private String immatriculation;

	@Column(name = "modele")
	private String modele;

	@Column(name = "nombre_passager_max")
	private short nombreDePassagers;

	@Column(name = "capacite_fret")
	private long capaciteDeFret;

	@Column(name = "vitesse_atmospherique")
	private int vitesse;

	@Column(name = "autonomie")
	private int autonomie;

	@ManyToOne(targetEntity = JpaHangarEntity.class)
	@JoinColumn(name = "id_hangar")
	private HangarEntity hangar;
}
