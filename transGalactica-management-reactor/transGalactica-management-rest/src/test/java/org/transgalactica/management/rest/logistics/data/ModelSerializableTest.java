package org.transgalactica.management.rest.logistics.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.transgalactica.fwk.test.bean.AbstractSerializableGenericTest;
import org.transgalactica.management.rest.logistics.data.impl.JaxbHangarCommand;
import org.transgalactica.management.rest.logistics.data.impl.JaxbHangarDetailDto;
import org.transgalactica.management.rest.logistics.data.impl.JaxbHangarDto;
import org.transgalactica.management.rest.logistics.data.impl.JaxbHangarDtos;
import org.transgalactica.management.rest.logistics.data.impl.JaxbVaisseauCommand;
import org.transgalactica.management.rest.logistics.data.impl.JaxbVaisseauDetailDto;
import org.transgalactica.management.rest.logistics.data.impl.JaxbVaisseauDto;
import org.transgalactica.management.rest.logistics.data.impl.JaxbVaisseauDtos;

@RunWith(Parameterized.class)
public class ModelSerializableTest extends AbstractSerializableGenericTest {

	public ModelSerializableTest(Class<? extends Serializable> beanClazz) {
		super(beanClazz);
	}

	@Parameters
	public static Collection<Class<?>[]> getBeansClazz() {
		Collection<Class<?>[]> beansClazz = new ArrayList<Class<?>[]>();

		beansClazz.add(new Class[] { JaxbHangarCommand.class });
		beansClazz.add(new Class[] { JaxbHangarDtos.class });
		beansClazz.add(new Class[] { JaxbHangarDto.class });
		beansClazz.add(new Class[] { JaxbHangarDetailDto.class });
		beansClazz.add(new Class[] { JaxbHangarDetailDto.JaxbVaisseauDto.class });

		beansClazz.add(new Class[] { JaxbVaisseauCommand.class });
		beansClazz.add(new Class[] { JaxbVaisseauDtos.class });
		beansClazz.add(new Class[] { JaxbVaisseauDto.class });
		beansClazz.add(new Class[] { JaxbVaisseauDetailDto.class });

		return beansClazz;
	}
}
