package org.transgalactica.management.data.materiel.bo.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.materiel.bo.VaisseauIntergalactiqueEntity;

@DataBean("org.transgalactica.management.data.materiel.bo.VaisseauIntergalactiqueEntity")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "VAISSEAUX_INTER_GALACTIQUE")
@PrimaryKeyJoinColumn(name = "id")
public class JpaVaisseauIntergalactiqueEntity extends JpaVaisseauEntity implements VaisseauIntergalactiqueEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "multiplicateur_hyperdrive")
	private short multiplicateurHyperdrive;
}
