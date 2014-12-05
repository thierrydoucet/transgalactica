package org.transgalactica.fwk.domain.stereotype;

import static org.junit.Assert.assertNotSame;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:org/transgalactica/fwk/domain/stereotype/DataBeanTest.spring.xml" })
public class DataBeanTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void testPrototype() {
		EmptyDataBean db1 = applicationContext.getBean(EmptyDataBean.class);
		EmptyDataBean db2 = applicationContext.getBean(EmptyDataBean.class);

		assertNotSame(db1, db2);
	}
}
