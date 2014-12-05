package org.transgalactica.management.rest.hr.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.transgalactica.fwk.test.bean.AbstractSerializableGenericTest;
import org.transgalactica.management.rest.hr.data.impl.JaxbEmployeCommand;
import org.transgalactica.management.rest.hr.data.impl.JaxbEmployeDto;
import org.transgalactica.management.rest.hr.data.impl.JaxbEmployeDtos;
import org.transgalactica.management.rest.hr.data.impl.JaxbMecanicienDetailDto;
import org.transgalactica.management.rest.hr.data.impl.JaxbPiloteDetailDto;
import org.transgalactica.management.rest.logistics.data.impl.JaxbVaisseauDto;

@RunWith(Parameterized.class)
public class ModelSerializableTest extends AbstractSerializableGenericTest {

	public ModelSerializableTest(Class<? extends Serializable> beanClazz) {
		super(beanClazz);
	}

	@Parameters
	public static Collection<Class<?>[]> getBeansClazz() {
		Collection<Class<?>[]> beansClazz = new ArrayList<Class<?>[]>();

		beansClazz.add(new Class[] { JaxbEmployeCommand.class });
		beansClazz.add(new Class[] { JaxbEmployeDtos.class });
		beansClazz.add(new Class[] { JaxbEmployeDto.class });
		beansClazz.add(new Class[] { JaxbMecanicienDetailDto.class });
		beansClazz.add(new Class[] { JaxbPiloteDetailDto.class });
		beansClazz.add(new Class[] { JaxbVaisseauDto.class });

		return beansClazz;
	}
}
