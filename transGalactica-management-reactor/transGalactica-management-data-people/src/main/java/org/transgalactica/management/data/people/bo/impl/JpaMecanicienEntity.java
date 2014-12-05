package org.transgalactica.management.data.people.bo.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.data.people.bo.MecanicienEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;
import org.transgalactica.management.data.referentiel.bo.impl.JpaMecanicienSpecialiteEntity;

@DataBean("org.transgalactica.management.data.people.bo.MecanicienEntity")
@Getter
@Setter
@Entity
@DiscriminatorValue("MECANICIEN")
public class JpaMecanicienEntity extends AbstractJpaEmployeEntity implements MecanicienEntity {

	private static final long serialVersionUID = 1L;

	@ManyToMany(targetEntity = JpaMecanicienSpecialiteEntity.class)
	@JoinTable(name = "AFFECTATION_MECANICIENS_SPECIALITES", joinColumns = @JoinColumn(name = "id_employe", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_specialite", referencedColumnName = "id"))
	private Set<MecanicienSpecialiteEntity> specialites = new HashSet<MecanicienSpecialiteEntity>(1);

	protected JpaMecanicienEntity() {
		setType(EmployeType.MECANICIEN);
	}

	/*
	 * Methodes deleguées
	 */

	@Override
	public boolean addSpecialite(MecanicienSpecialiteEntity specialite) {
		return specialites.add(specialite);
	}

	@Override
	public boolean removeSpecialite(MecanicienSpecialiteEntity specialite) {
		return specialites.remove(specialite);
	}
}
