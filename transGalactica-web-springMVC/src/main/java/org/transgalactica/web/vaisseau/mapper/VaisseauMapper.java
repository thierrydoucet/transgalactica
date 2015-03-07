package org.transgalactica.web.vaisseau.mapper;

import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.web.vaisseau.model.VaisseauCommand;

public interface VaisseauMapper {

	VaisseauCommand mapToVaisseauCommand(VaisseauEntity vaisseau);

	void mapVaisseauCommandToEntity(VaisseauCommand command,
			VaisseauEntity entity);
}