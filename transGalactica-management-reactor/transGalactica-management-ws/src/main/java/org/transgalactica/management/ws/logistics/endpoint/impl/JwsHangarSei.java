package org.transgalactica.management.ws.logistics.endpoint.impl;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.transgalactica.fwk.remote.exception.BusinessExceptionConverter;
import org.transgalactica.fwk.remote.exception.RemoteBusinessException;
import org.transgalactica.fwk.validation.exception.BusinessException;
import org.transgalactica.management.business.logistics.service.HangarService;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.ws.logistics.data.BasicHangarDto;
import org.transgalactica.management.ws.logistics.endpoint.HangarSei;
import org.transgalactica.management.ws.logistics.mapper.HangarMapper;

@WebService(serviceName = "HangarService", endpointInterface = "org.transgalactica.management.ws.logistics.endpoint.HangarSei")
public class JwsHangarSei implements HangarSei {

	@Inject
	private BusinessExceptionConverter businessExceptionConverter;

	@Inject
	private HangarService hangarService;

	@Inject
	private HangarMapper hangarMapper;

	protected JwsHangarSei() {
	}

	@Override
	@WebMethod
	@WebResult(name = "BasicHangarDto")
	public BasicHangarDto[] rechercherHangars(@WebParam(name = "localisationHangar") String localisationHangar)
			throws RemoteBusinessException {
		try {
			HangarSearchCriteria criteres = hangarMapper.mapToRechercheHangarCriteres(localisationHangar);
			List<HangarSummary> hangars = hangarService.rechercherHangars(criteres);
			return hangarMapper.mapToHangars(hangars);
		}
		catch (BusinessException e) {
			throw businessExceptionConverter.convert(e);
		}
	}
}
