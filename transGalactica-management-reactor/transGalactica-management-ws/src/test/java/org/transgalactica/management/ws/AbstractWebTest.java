package org.transgalactica.management.ws;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(name = "app", locations = "file:src/main/webapp/WEB-INF/applicationContext.xml"),
		@ContextConfiguration(name = "test", classes = TestConfig.class) })
@TransactionConfiguration
@Transactional(readOnly = true)
@ActiveProfiles("test")
public abstract class AbstractWebTest {

}