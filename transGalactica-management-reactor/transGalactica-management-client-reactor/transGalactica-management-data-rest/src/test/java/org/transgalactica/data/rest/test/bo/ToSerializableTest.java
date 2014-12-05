package org.transgalactica.data.rest.test.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.transgalactica.data.rest.bo.impl.AbstractEmployeTo;
import org.transgalactica.data.rest.bo.impl.BasicEmployeSearchCriteria;
import org.transgalactica.data.rest.bo.impl.BasicEmployeSummaryTo;
import org.transgalactica.data.rest.bo.impl.BasicHangarSearchCriteria;
import org.transgalactica.data.rest.bo.impl.BasicHangarSummaryTo;
import org.transgalactica.data.rest.bo.impl.BasicHangarTo;
import org.transgalactica.data.rest.bo.impl.BasicMecanicienTo;
import org.transgalactica.data.rest.bo.impl.BasicPiloteTo;
import org.transgalactica.data.rest.bo.impl.BasicVaisseauSearchCriteria;
import org.transgalactica.data.rest.bo.impl.BasicVaisseauSummaryTo;
import org.transgalactica.data.rest.bo.impl.BasicVaisseauTo;
import org.transgalactica.fwk.test.bean.AbstractSerializableGenericTest;

@RunWith(Parameterized.class)
public class ToSerializableTest extends AbstractSerializableGenericTest {

	public ToSerializableTest(Class<? extends Serializable> beanClazz) {
		super(beanClazz);
	}

	@Parameters
	public static Collection<Class<?>[]> getBeansClazz() {
		Collection<Class<?>[]> beansClazz = new ArrayList<Class<?>[]>();

		beansClazz.add(new Class[] { BasicHangarTo.class });
		beansClazz.add(new Class[] { BasicHangarTo.BasicVaisseauTo.class });
		beansClazz.add(new Class[] { BasicHangarSummaryTo.class });
		beansClazz.add(new Class[] { BasicHangarSearchCriteria.class });

		beansClazz.add(new Class[] { BasicVaisseauTo.class });
		beansClazz.add(new Class[] { BasicVaisseauSummaryTo.class });
		beansClazz.add(new Class[] { BasicVaisseauSearchCriteria.class });

		beansClazz.add(new Class[] { BasicMecanicienTo.class });
		beansClazz.add(new Class[] { BasicPiloteTo.class });
		beansClazz.add(new Class[] { AbstractEmployeTo.BasicVaisseauTo.class });
		beansClazz.add(new Class[] { BasicEmployeSummaryTo.class });
		beansClazz.add(new Class[] { BasicEmployeSearchCriteria.class });

		return beansClazz;
	}
}
