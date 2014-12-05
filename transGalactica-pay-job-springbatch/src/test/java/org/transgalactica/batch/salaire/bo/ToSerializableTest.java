package org.transgalactica.batch.salaire.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.transgalactica.batch.salaire.bo.impl.BasicSalaireTo;
import org.transgalactica.fwk.test.bean.AbstractSerializableGenericTest;

@RunWith(Parameterized.class)
public class ToSerializableTest extends AbstractSerializableGenericTest {

	public ToSerializableTest(Class<? extends Serializable> beanClazz) {
		super(beanClazz);
	}

	@Parameters
	public static Collection<Class<?>[]> getBeansClazz() {
		Collection<Class<?>[]> beansClazz = new ArrayList<Class<?>[]>();
		beansClazz.add(new Class[] { BasicSalaireTo.class });
		return beansClazz;
	}
}
