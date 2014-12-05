package org.transgalactica.management.rest.hr.data;

import java.io.Serializable;
import java.util.List;

public interface EmployeDtos extends Serializable {

	List<EmployeDto> getEmployes();
}