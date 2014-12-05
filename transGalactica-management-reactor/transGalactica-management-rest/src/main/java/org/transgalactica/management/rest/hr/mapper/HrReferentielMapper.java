package org.transgalactica.management.rest.hr.mapper;

import java.util.Collection;

import org.transgalactica.management.data.referentiel.bo.EmployeType;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;
import org.transgalactica.management.rest.hr.data.EmployeTypeDtos;
import org.transgalactica.management.rest.hr.data.MecanicienDetailDto;
import org.transgalactica.management.rest.hr.data.MecanicienSpecialiteDtos;

public interface HrReferentielMapper {

	EmployeTypeDtos mapToEmployeTypeDtos(EmployeType[] employeTypes);

	MecanicienSpecialiteDtos mapToMecanicienSpecialiteDtos(Collection<MecanicienSpecialiteEntity> specialites);

	void mapSpecialitesToMecanicienDetailDto(MecanicienDetailDto dto, Collection<MecanicienSpecialiteEntity> specialites);
}
