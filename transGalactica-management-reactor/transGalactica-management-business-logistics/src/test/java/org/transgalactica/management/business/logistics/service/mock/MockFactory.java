package org.transgalactica.management.business.logistics.service.mock;

import org.springframework.beans.BeanUtils;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.impl.BasicHangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.impl.BasicVaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.impl.JpaHangarEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauEntity;

public class MockFactory {

	public static HangarSearchCriteria mockHangarSearchCriteria() {
		HangarSearchCriteria mock = BeanUtils.instantiateClass(BasicHangarSearchCriteria.class);
		mock.setLocalisationHangar("mock");
		return mock;
	}

	public static HangarEntity mockHangarEntity() {
		HangarEntity mock = BeanUtils.instantiateClass(JpaHangarEntity.class);
		mock.setLocalisation("mock");
		mock.setNombreEmplacements(1);
		return mock;
	}

	public static VaisseauSearchCriteria mockVaisseauSearchCriteria() {
		VaisseauSearchCriteria mock = BeanUtils.instantiateClass(BasicVaisseauSearchCriteria.class);
		mock.setImmatriculation("mock");
		return mock;
	}

	public static VaisseauEntity mockVaisseauEntity() {
		VaisseauEntity mock = BeanUtils.instantiateClass(JpaVaisseauEntity.class);
		mock.setImmatriculation("mock");
		mock.setModele("mock");
		return mock;
	}
}
