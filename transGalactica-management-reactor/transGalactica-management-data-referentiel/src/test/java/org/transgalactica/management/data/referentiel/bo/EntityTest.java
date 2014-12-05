package org.transgalactica.management.data.referentiel.bo;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.test.util.ReflectionTestUtils;
import org.transgalactica.fwk.test.domain.bo.AbstractBoGenericTest;
import org.transgalactica.management.data.referentiel.bo.impl.JpaMecanicienSpecialiteEntity;

@RunWith(Parameterized.class)
public class EntityTest extends AbstractBoGenericTest {

	public EntityTest(Class<Object> clazzOfBo, BusinessIdentifierSetterVisitor<Object> visitor) {
		super(clazzOfBo, visitor);
	}

	@Parameters
	public static Collection<Object[]> getBeansClazz() {
		Collection<Object[]> beans = new ArrayList<Object[]>();
		beans.add(new Object[] { JpaMecanicienSpecialiteEntity.class,
				new BusinessIdentifierSetterVisitor<JpaMecanicienSpecialiteEntity>() {
					public void setBusinessIdentifierFirstValue(JpaMecanicienSpecialiteEntity bo) {
						ReflectionTestUtils.invokeSetterMethod(bo, "nomSpecialite", "nomSpecialite 1");
					}

					public void setBusinessIdentifierSecondValue(JpaMecanicienSpecialiteEntity bo) {
						ReflectionTestUtils.invokeSetterMethod(bo, "nomSpecialite", "nomSpecialite 2");
					}
				} });
		return beans;
	}
}
