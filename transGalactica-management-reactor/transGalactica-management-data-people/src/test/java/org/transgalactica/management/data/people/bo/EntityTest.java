package org.transgalactica.management.data.people.bo;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.transgalactica.fwk.test.domain.bo.AbstractBoGenericTest;
import org.transgalactica.management.data.people.bo.impl.AbstractJpaEmployeEntity;
import org.transgalactica.management.data.people.bo.impl.JpaMecanicienEntity;
import org.transgalactica.management.data.people.bo.impl.JpaPiloteEntity;

@RunWith(Parameterized.class)
public class EntityTest extends AbstractBoGenericTest {

	public EntityTest(Class<Object> clazzOfBo, BusinessIdentifierSetterVisitor<Object> visitor) {
		super(clazzOfBo, visitor);
	}

	@Parameters
	public static Collection<Object[]> getBeansClazz() {
		BusinessIdentifierSetterVisitor<AbstractJpaEmployeEntity> employeVisitor = new BusinessIdentifierSetterVisitor<AbstractJpaEmployeEntity>() {
			public void setBusinessIdentifierFirstValue(AbstractJpaEmployeEntity bo) {
				bo.setMatricule(1L);
			}

			public void setBusinessIdentifierSecondValue(AbstractJpaEmployeEntity bo) {
				bo.setMatricule(2L);
			}
		};

		Collection<Object[]> beans = new ArrayList<Object[]>();
		beans.add(new Object[] { JpaMecanicienEntity.class, employeVisitor });
		beans.add(new Object[] { JpaPiloteEntity.class, employeVisitor });
		return beans;
	}
}
