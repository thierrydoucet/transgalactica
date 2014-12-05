package org.transgalactica.fwk.test.domaine.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;
import org.transgalactica.fwk.test.bean.AbstractSerializableGenericTest;

public class AbstractToGenericTestTest extends AbstractSerializableGenericTest {

	public AbstractToGenericTestTest(Class<? extends Serializable> beanClazz) {
		super(beanClazz);
	}

	/**
	 * @return the BOs list to test
	 */
	@Parameters
	public static Collection<Class<?>[]> getBeansClazz() {
		Collection<Class<?>[]> beansClazz = new ArrayList<Class<?>[]>();
		beansClazz.add(new Class[] { DummyTo.class });
		return beansClazz;
	}
}
