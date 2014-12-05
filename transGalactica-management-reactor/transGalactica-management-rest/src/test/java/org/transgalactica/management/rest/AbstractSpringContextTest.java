package org.transgalactica.management.rest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.transgalactica.management.rest.context.AppConfig;
import org.transgalactica.management.rest.context.mvc.WebMvcConfig;
import org.transgalactica.test.context.TestConfig;

@WebAppConfiguration
// TODO : spring 3.2.2 @ContextHierarchy({ @ContextConfiguration(name =
// "root", classes = AppConfig.class),
// @ContextConfiguration(name = "mvc", classes = WebMvcConfig.class) })
@ContextConfiguration(classes = { AppConfig.class, WebMvcConfig.class, TestConfig.class })
@ActiveProfiles("test")
public abstract class AbstractSpringContextTest extends AbstractJUnit4SpringContextTests {

}
