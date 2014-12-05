package org.transgalactica.management.data.people.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.transgalactica.fwk.test.bean.AbstractSerializableGenericTest;
import org.transgalactica.management.data.people.bo.BasicEmployeSummary;
import org.transgalactica.management.data.people.bo.impl.BasicEmployeSearchCriteria;

@RunWith(Parameterized.class)
public class SerializableBeanTest extends AbstractSerializableGenericTest {

	public SerializableBeanTest(Class<? extends Serializable> beanClazz) {
		super(beanClazz);
	}

	@Parameters
	public static Collection<Class<?>[]> getBeansClazz() {
		Collection<Class<?>[]> beansClazz = new ArrayList<Class<?>[]>();
		beansClazz.add(new Class[] { BasicEmployeSearchCriteria.class });
		beansClazz.add(new Class[] { BasicEmployeSummary.class });
		return beansClazz;
	}
}
