package org.transgalactica.info.data.motd.bo.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.transgalactica.fwk.test.bean.AbstractSerializableGenericTest;
import org.transgalactica.info.data.motd.bo.impl.BasicImageTo;
import org.transgalactica.info.data.motd.bo.impl.BasicMessageTo;

@RunWith(Parameterized.class)
public class SerializableBeanTest extends AbstractSerializableGenericTest {

	public SerializableBeanTest(Class<? extends Serializable> beanClazz) {
		super(beanClazz);
	}

	@Parameters
	public static Collection<Class<?>[]> getBeansClazz() {
		Collection<Class<?>[]> beansClazz = new ArrayList<Class<?>[]>();
		beansClazz.add(new Class[] { BasicMessageTo.class });
		beansClazz.add(new Class[] { BasicImageTo.class });
		return beansClazz;
	}
}
