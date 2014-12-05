package org.transgalactica.management.data.materiel.bo.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.transgalactica.fwk.domain.bean.BusinessIdentifier;
import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;

@DataBean
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "numero")
@Entity
@Table(name = "HANGARS")
public class JpaHangarEntity implements HangarEntity {

	private static final long serialVersionUID = 1L;

	@BusinessIdentifier
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;

	@Column(name = "localisation")
	private String localisation;

	@Column(name = "nombre_emplacements")
	private int nombreEmplacements;

	@OneToMany(mappedBy = "hangar", targetEntity = JpaVaisseauEntity.class)
	private Set<VaisseauEntity> vaisseaux = new HashSet<>();

	/*
	 * Methodes deleguées
	 */

	@Override
	public boolean add(VaisseauEntity vaisseau) {
		vaisseau.setHangar(this);
		return vaisseaux.add(vaisseau);
	}

	@Override
	public boolean remove(VaisseauEntity vaisseau) {
		vaisseau.setHangar(null);
		return vaisseaux.remove(vaisseau);
	}
}
