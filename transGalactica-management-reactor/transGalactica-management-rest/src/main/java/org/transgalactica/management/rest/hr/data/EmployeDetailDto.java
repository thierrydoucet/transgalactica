package org.transgalactica.management.rest.hr.data;

import java.io.Serializable;
import java.util.Set;

public interface EmployeDetailDto extends EmployeDto, Serializable {

	Set<VaisseauDto> getVaisseaux();
}