package org.transgalactica.swing.logistics.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.transgalactica.data.rest.bo.HangarSearchCriteria;
import org.transgalactica.data.rest.bo.HangarSummaryTo;
import org.transgalactica.data.rest.bo.HangarTo;
import org.transgalactica.data.rest.dao.HangarDao;
import org.transgalactica.swing.logistics.service.HangarService;

@Service
public class DaoHangarService implements HangarService {

	@Inject
	private HangarDao hangarDao;

	protected DaoHangarService() {
	}

	/**
	 * @see org.transgalactica.swing.logistics.service.impl.DaoHangarService#chargerHangar(java.lang.Long)
	 */
	@Override
	public HangarTo chargerHangar(Long numero) {
		Assert.notNull(numero);
		return hangarDao.getByNumero(numero);
	}

	/**
	 * @see org.transgalactica.swing.logistics.service.impl.DaoHangarService#enregistrerHangar(org.transgalactica.data.rest.bo.HangarTo)
	 */
	@Override
	public void enregistrerHangar(HangarTo hangar) {
		Assert.notNull(hangar);
		hangarDao.persist(hangar);
	}

	/**
	 * @see org.transgalactica.swing.logistics.service.impl.DaoHangarService#rechercherHangars(org.transgalactica.data.rest.bo.HangarSearchCriteria)
	 */
	@Override
	public List<HangarSummaryTo> rechercherHangars(HangarSearchCriteria criteres) {
		Assert.notNull(criteres);
		return hangarDao.searchByCriteria(criteres);
	}

	/**
	 * @see org.transgalactica.swing.logistics.service.impl.DaoHangarService#supprimerHangar(org.transgalactica.data.rest.bo.HangarTo)
	 */
	@Override
	public void supprimerHangar(HangarTo hangar) {
		Assert.notNull(hangar);
		hangarDao.remove(hangar);
	}
}
