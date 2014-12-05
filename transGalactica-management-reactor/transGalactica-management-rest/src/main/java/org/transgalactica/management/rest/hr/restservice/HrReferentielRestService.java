package org.transgalactica.management.rest.hr.restservice;

import org.transgalactica.management.rest.hr.data.EmployeTypeDtos;
import org.transgalactica.management.rest.hr.data.MecanicienSpecialiteDtos;

public interface HrReferentielRestService {

	EmployeTypeDtos getEmployeTypes();

	MecanicienSpecialiteDtos getMecanicienSpecialites();

}
