package org.transgalactica.test;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath*:META-INF/beanRefFactory.xml",
		"classpath*:META-INF/beanRefFactory-test.xml" })
@ActiveProfiles("test")
public abstract class AbstractContextTest extends AbstractJUnit4SpringContextTests {

}
