package org.transgalactica.web.hangar.mapper;

import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.web.hangar.model.impl.HangarCommand;

public interface HangarMapper {

	HangarCommand mapToHangarCommand(HangarEntity hangar);

	void mapHangarCommandToEntity(HangarCommand command, HangarEntity entity);
}