package org.transgalactica.management.rest.hr.mapper.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.beans.factory.BeanFactory;
import org.transgalactica.fwk.domain.stereotype.Mapper;
import org.transgalactica.management.data.referentiel.bo.EmployeType;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;
import org.transgalactica.management.rest.hr.data.EmployeTypeDtos;
import org.transgalactica.management.rest.hr.data.MecanicienDetailDto;
import org.transgalactica.management.rest.hr.data.MecanicienSpecialiteDtos;
import org.transgalactica.management.rest.hr.mapper.HrReferentielMapper;

/**
 * Implementation par défaut de
 * {@link org.transgalactica.management.rest.hr.mapper.EmployeMapper}
 * 
 * @author Thierry
 */
@Mapper
public class BasicHrReferentielMapper implements HrReferentielMapper {

	@Inject
	private BeanFactory beanFactory;

	protected BasicHrReferentielMapper() {
	}

	@Override
	public MecanicienSpecialiteDtos mapToMecanicienSpecialiteDtos(Collection<MecanicienSpecialiteEntity> specialites) {
		MecanicienSpecialiteDtos dtos = beanFactory.getBean(MecanicienSpecialiteDtos.class);
		for (MecanicienSpecialiteEntity specialite : specialites) {
			invokeMethod(dtos, "add", specialite.getNomSpecialite());
		}
		return dtos;
	}

	@Override
	public void mapSpecialitesToMecanicienDetailDto(MecanicienDetailDto dto,
			Collection<MecanicienSpecialiteEntity> specialitesEntities) {
		Set<String> specialites = new HashSet<>();
		for (MecanicienSpecialiteEntity specialite : specialitesEntities) {
			specialites.add(specialite.getNomSpecialite());
		}
		invokeMethod(dto, "setSpecialites", specialites);
	}

	@Override
	public EmployeTypeDtos mapToEmployeTypeDtos(EmployeType[] employeTypes) {
		EmployeTypeDtos dtos = beanFactory.getBean(EmployeTypeDtos.class);
		for (EmployeType type : employeTypes) {
			invokeMethod(dtos, "add", type.name());
		}
		return dtos;
	}

	private void invokeMethod(Object target, String method, Object value) {
		try {
			MethodUtils.invokeMethod(target, method, value);
		}
		catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalArgumentException("Can't map bean: check target type declare '" + method + "' method.", e);
		}
	}
}
