package org.transgalactica.swing.logistics.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.transgalactica.data.rest.bo.VaisseauSearchCriteria;
import org.transgalactica.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.data.rest.bo.VaisseauTo;
import org.transgalactica.data.rest.dao.VaisseauDao;
import org.transgalactica.swing.logistics.service.VaisseauService;

@Service
public class DaoVaisseauService implements VaisseauService {

	@Inject
	private VaisseauDao vaisseauDao;

	protected DaoVaisseauService() {
	}

	/**
	 * @see org.transgalactica.swing.logistics.service.impl.DaoVaisseauService#chargerVaisseau(java.lang.String)
	 */
	@Override
	public VaisseauTo chargerVaisseau(String immatriculation) {
		Assert.notNull(immatriculation);
		return vaisseauDao.getByImmatriculation(immatriculation);
	}

	/**
	 * @see org.transgalactica.swing.logistics.service.impl.DaoVaisseauService#enregistrerVaisseau(org.transgalactica.data.rest.bo.VaisseauTo)
	 */
	@Override
	public void enregistrerVaisseau(VaisseauTo vaisseau) {
		Assert.notNull(vaisseau);
		vaisseauDao.persist(vaisseau);
	}

	/**
	 * @see org.transgalactica.swing.logistics.service.impl.DaoVaisseauService#rechercherVaisseaux(org.transgalactica.data.rest.bo.VaisseauSearchCriteria)
	 */
	@Override
	public List<VaisseauSummaryTo> rechercherVaisseaux(VaisseauSearchCriteria criteres) {
		Assert.notNull(criteres);
		return vaisseauDao.searchByCriteria(criteres);
	}

	/**
	 * @see org.transgalactica.swing.logistics.service.impl.DaoVaisseauService#supprimerVaisseau(org.transgalactica.data.rest.bo.VaisseauTo)
	 */
	@Override
	public void supprimerVaisseau(VaisseauTo vaisseau) {
		Assert.notNull(vaisseau);
		vaisseauDao.remove(vaisseau);
	}
}
