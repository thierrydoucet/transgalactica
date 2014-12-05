package org.transgalactica.web;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml",
		"file:src/main/webapp/WEB-INF/transGalactica-servlet.xml", "classpath*:/META-INF/beanRefFactory-test.xml" })
@ActiveProfiles("test")
public abstract class AbstractWebContextTest extends AbstractJUnit4SpringContextTests {

}
