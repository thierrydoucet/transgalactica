package org.transgalactica.management.rest.logistics.mapper.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.BeanFactory;
import org.transgalactica.fwk.domain.stereotype.Mapper;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.rest.logistics.data.VaisseauCommand;
import org.transgalactica.management.rest.logistics.data.VaisseauDetailDto;
import org.transgalactica.management.rest.logistics.data.VaisseauDtos;
import org.transgalactica.management.rest.logistics.mapper.VaisseauMapper;

/**
 * Implementation par défaut de {@link DozerVaisseauMapper}
 * 
 * @author Thierry
 */
@Mapper
public class DozerVaisseauMapper implements VaisseauMapper {

	@Inject
	private BeanFactory beanFactory;

	@Inject
	private org.dozer.Mapper mapper;

	protected DozerVaisseauMapper() {
	}

	/**
	 * @see org.transgalactica.management.rest.logistics.mapper.DozerVaisseauMapper.mapper.impl.IVaisseauMapper#mapVaisseauCommandToEntity(org.transgalactica.management.rest.BasicVaisseauCommand.data.impl.VaisseauCommand,
	 * org.transgalactica.management.data.materiel.bo.VaisseauEntity)
	 */
	@Override
	public void mapVaisseauCommandToEntity(VaisseauCommand command, VaisseauEntity entity) {
		mapper.map(command, entity);
	}

	/**
	 * @see org.transgalactica.management.rest.logistics.mapper.DozerVaisseauMapper.mapper.impl.IVaisseauMapper#mapToVaisseauDtos(java.util.List)
	 */
	@Override
	public VaisseauDtos mapToVaisseauDtos(List<VaisseauSummary> projections) {
		return mapper.map(projections, VaisseauDtos.class);
	}

	/**
	 * @see org.transgalactica.management.rest.logistics.mapper.DozerVaisseauMapper.mapper.impl.IVaisseauMapper#mapToVaisseauDetailDto(org.transgalactica.management.data.materiel.bo.VaisseauEntity)
	 */
	@Override
	public VaisseauDetailDto mapToVaisseauDetailDto(VaisseauEntity entity) {
		return mapper.map(entity, VaisseauDetailDto.class);
	}

	/**
	 * @see org.transgalactica.management.rest.logistics.mapper.DozerVaisseauMapper.mapper.impl.IVaisseauMapper#mapToRechercheVaisseauCriteres(org.transgalactica.management.rest.materiel.data.impl.RechercheVaisseauCommand)
	 */
	@Override
	public VaisseauSearchCriteria mapToRechercheVaisseauCriteres(String immatriculation, String modele,
			boolean intergalactique) {
		VaisseauSearchCriteria criteres = beanFactory.getBean(VaisseauSearchCriteria.class);
		criteres.setImmatriculation(immatriculation);
		criteres.setModele(modele);
		criteres.setIntergalactique(intergalactique);
		return criteres;
	}
}
