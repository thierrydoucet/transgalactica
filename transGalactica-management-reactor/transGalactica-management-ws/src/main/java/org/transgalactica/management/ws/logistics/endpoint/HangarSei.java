package org.transgalactica.management.ws.logistics.endpoint;

import javax.jws.WebService;

import org.transgalactica.fwk.remote.exception.RemoteBusinessException;
import org.transgalactica.management.ws.logistics.data.BasicHangarDto;

@WebService
public interface HangarSei {

	BasicHangarDto[] rechercherHangars(String localisationHangar) throws RemoteBusinessException;
}
