package org.transgalactica.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.transgalactica.fwk.test.bean.AbstractSerializableGenericTest;
import org.transgalactica.web.hangar.model.HangarCommand;
import org.transgalactica.web.vaisseau.model.VaisseauCommand;

@RunWith(Parameterized.class)
public class ModelSerializableTest extends AbstractSerializableGenericTest {

	public ModelSerializableTest(Class<? extends Serializable> beanClazz) {
		super(beanClazz);
	}

	@Parameters
	public static Collection<Class<?>[]> getBeansClazz() {
		Collection<Class<?>[]> beansClazz = new ArrayList<Class<?>[]>();

		beansClazz.add(new Class[] { HangarCommand.class });
		beansClazz.add(new Class[] { VaisseauCommand.class });

		return beansClazz;
	}
}
