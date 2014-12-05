package org.transgalactica.management.data.people.bo;

import java.util.Set;

import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;

public interface MecanicienEntity extends EmployeEntity {

	Set<MecanicienSpecialiteEntity> getSpecialites();

	boolean addSpecialite(MecanicienSpecialiteEntity specialite);

	boolean removeSpecialite(MecanicienSpecialiteEntity specialite);
}
