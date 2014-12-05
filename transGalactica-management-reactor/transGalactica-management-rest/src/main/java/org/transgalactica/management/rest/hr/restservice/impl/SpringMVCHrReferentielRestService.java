package org.transgalactica.management.rest.hr.restservice.impl;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.transgalactica.management.business.hr.service.EmployeService;
import org.transgalactica.management.data.referentiel.bo.EmployeType;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;
import org.transgalactica.management.rest.hr.data.EmployeTypeDtos;
import org.transgalactica.management.rest.hr.data.MecanicienSpecialiteDtos;
import org.transgalactica.management.rest.hr.mapper.HrReferentielMapper;
import org.transgalactica.management.rest.hr.restservice.HrReferentielRestService;

/**
 * Implementation Spring MVC de {@link HrReferentielRestService}
 * 
 * @author Thierry
 */
@Controller
@RequestMapping(value = "/hrreferentiel")
public class SpringMVCHrReferentielRestService implements HrReferentielRestService {

	@Inject
	private EmployeService employeService;

	@Inject
	private HrReferentielMapper hrReferentielMapper;

	protected SpringMVCHrReferentielRestService() {
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void parameterNotValid(HttpServletResponse request) {
		// NOP
	}

	@Override
	@RequestMapping(value = "/mecanicienspecialites", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public MecanicienSpecialiteDtos getMecanicienSpecialites() {
		List<MecanicienSpecialiteEntity> specialites = employeService.chargerMecanicienSpecialites();
		return hrReferentielMapper.mapToMecanicienSpecialiteDtos(specialites);
	}

	@Override
	@RequestMapping(value = "/employetypes", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EmployeTypeDtos getEmployeTypes() {
		return hrReferentielMapper.mapToEmployeTypeDtos(EmployeType.values());
	}
}
