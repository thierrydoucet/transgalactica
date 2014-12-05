package org.transgalactica.management.ws.logistics.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.transgalactica.fwk.test.bean.AbstractSerializableGenericTest;
import org.transgalactica.management.ws.logistics.data.BasicHangarDto;

@RunWith(Parameterized.class)
public class DtoSerializableTest extends AbstractSerializableGenericTest {

	public DtoSerializableTest(Class<? extends Serializable> beanClazz) {
		super(beanClazz);
	}

	@Parameters
	public static Collection<Class<?>[]> getBeansClazz() {
		Collection<Class<?>[]> beansClazz = new ArrayList<Class<?>[]>();

		beansClazz.add(new Class[] { BasicHangarDto.class });

		return beansClazz;
	}
}
