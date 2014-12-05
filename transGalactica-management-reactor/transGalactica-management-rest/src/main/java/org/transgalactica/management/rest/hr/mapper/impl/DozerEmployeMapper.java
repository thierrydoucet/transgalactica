package org.transgalactica.management.rest.hr.mapper.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.BeanFactory;
import org.transgalactica.fwk.domain.stereotype.Mapper;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.EmployeSearchCriteria;
import org.transgalactica.management.data.people.bo.EmployeSummary;
import org.transgalactica.management.data.people.bo.MecanicienEntity;
import org.transgalactica.management.data.people.bo.PiloteEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;
import org.transgalactica.management.rest.hr.data.EmployeCommand;
import org.transgalactica.management.rest.hr.data.EmployeDetailDto;
import org.transgalactica.management.rest.hr.data.EmployeDtos;
import org.transgalactica.management.rest.hr.data.MecanicienDetailDto;
import org.transgalactica.management.rest.hr.data.PiloteCommand;
import org.transgalactica.management.rest.hr.data.PiloteDetailDto;
import org.transgalactica.management.rest.hr.mapper.EmployeMapper;
import org.transgalactica.management.rest.hr.mapper.HrReferentielMapper;

/**
 * Implementation par défaut de {@link EmployeMapper}
 * 
 * @author Thierry
 */
@Mapper
public class DozerEmployeMapper implements EmployeMapper {

	@Inject
	private BeanFactory beanFactory;

	@Inject
	private org.dozer.Mapper mapper;

	@Inject
	private HrReferentielMapper hrReferentielMapper;

	protected DozerEmployeMapper() {
	}

	@Override
	public EmployeEntity mapEmployeCommandToEntity(EmployeCommand command) {
		EmployeEntity entity;
		if (command instanceof PiloteCommand) {
			entity = beanFactory.getBean(PiloteEntity.class);
		}
		else {
			entity = beanFactory.getBean(MecanicienEntity.class);
		}
		mapEmployeCommandToEntity(command, entity);
		return entity;
	}

	@Override
	public void mapEmployeCommandToEntity(EmployeCommand command, EmployeEntity entity) {
		mapper.map(command, entity);
	}

	@Override
	public EmployeDetailDto mapToEmployeDetailDto(EmployeEntity entity) {
		if (entity.getType() == EmployeType.MECANICIEN) {
			MecanicienDetailDto dto = mapper.map(entity, MecanicienDetailDto.class);
			hrReferentielMapper.mapSpecialitesToMecanicienDetailDto(dto, ((MecanicienEntity) entity).getSpecialites());
			return dto;
		}
		if (entity.getType() == EmployeType.PILOTE) {
			return mapper.map(entity, PiloteDetailDto.class);
		}
		throw new IllegalArgumentException("Unkown employe type : " + entity.getType());
	}

	@Override
	public EmployeDtos mapToEmployeDtos(List<EmployeSummary> employes) {
		return mapper.map(employes, EmployeDtos.class);
	}

	@Override
	public EmployeSearchCriteria mapToRechercheEmployeCriteres(String nomEmploye, Date dateEmbaucheEmployeDebut,
			Date dateEmbaucheEmployeFin, String immatriculationVaisseau) {
		EmployeSearchCriteria criteria = beanFactory.getBean(EmployeSearchCriteria.class);
		criteria.setNomEmploye(nomEmploye);
		criteria.setDateEmbaucheEmployeDebut(dateEmbaucheEmployeDebut);
		criteria.setDateEmbaucheEmployeFin(dateEmbaucheEmployeFin);
		criteria.setImmatriculationVaisseau(immatriculationVaisseau);
		return criteria;
	}
}
