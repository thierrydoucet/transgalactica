package org.transgalactica.management.data.materiel.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.transgalactica.fwk.test.bean.AbstractSerializableGenericTest;
import org.transgalactica.management.data.materiel.bo.BasicHangarSummary;
import org.transgalactica.management.data.materiel.bo.BasicVaisseauSummary;
import org.transgalactica.management.data.materiel.bo.impl.BasicHangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.impl.BasicVaisseauSearchCriteria;

@RunWith(Parameterized.class)
public class SerializableBeanTest extends AbstractSerializableGenericTest {

	public SerializableBeanTest(Class<? extends Serializable> beanClazz) {
		super(beanClazz);
	}

	@Parameters
	public static Collection<Class<?>[]> getBeansClazz() {
		Collection<Class<?>[]> beansClazz = new ArrayList<Class<?>[]>();

		beansClazz.add(new Class[] { BasicHangarSearchCriteria.class });
		beansClazz.add(new Class[] { BasicVaisseauSearchCriteria.class });

		beansClazz.add(new Class[] { BasicHangarSummary.class });
		beansClazz.add(new Class[] { BasicVaisseauSummary.class });

		return beansClazz;
	}
}
