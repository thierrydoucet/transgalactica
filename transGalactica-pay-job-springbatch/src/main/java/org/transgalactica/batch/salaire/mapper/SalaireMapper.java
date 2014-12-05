package org.transgalactica.batch.salaire.mapper;

import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.management.data.people.bo.EmployeEntity;

public interface SalaireMapper {

	SalaireTo mapEmployeInformation(EmployeEntity employe);
}