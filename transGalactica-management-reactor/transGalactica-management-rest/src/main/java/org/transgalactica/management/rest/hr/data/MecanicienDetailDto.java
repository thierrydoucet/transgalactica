package org.transgalactica.management.rest.hr.data;

import java.io.Serializable;
import java.util.Set;

public interface MecanicienDetailDto extends EmployeDetailDto, Serializable {

	Set<String> getSpecialites();
}