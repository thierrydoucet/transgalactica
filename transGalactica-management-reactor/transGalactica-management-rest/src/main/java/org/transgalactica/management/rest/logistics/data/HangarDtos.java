package org.transgalactica.management.rest.logistics.data;

import java.io.Serializable;
import java.util.List;

public interface HangarDtos extends Serializable {

	List<HangarDto> getHangars();
}