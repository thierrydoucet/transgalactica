package org.transgalactica.fwk.test.domaine.bo;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;
import org.transgalactica.fwk.test.domain.bo.AbstractBoGenericTest;

public class AbstractBoGenericTestTest extends AbstractBoGenericTest {

	public AbstractBoGenericTestTest(Class<Object> clazzOfBo, BusinessIdentifierSetterVisitor<Object> visitor) {
		super(clazzOfBo, visitor);
	}

	/**
	 * @return the BOs list to test
	 */
	@Parameters
	public static Collection<Object[]> getBeans() {
		Collection<Object[]> beans = new ArrayList<Object[]>();
		beans.add(new Object[] { DummyBo.class, new BusinessIdentifierSetterVisitor<DummyBo>() {
			public void setBusinessIdentifierFirstValue(DummyBo bo) {
				bo.setBusinessIdentifier(1L);
			}

			public void setBusinessIdentifierSecondValue(DummyBo bo) {
				bo.setBusinessIdentifier(2L);
			}
		} });
		return beans;
	}
}
