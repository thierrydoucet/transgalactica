package org.transgalactica.fwk.test.jndi;

import static org.junit.Assert.assertEquals;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:org/transgalactica/fwk/test/jndi/JndiMockLoaderTest.spring.xml" })
public class JndiMockLoaderTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void testJndiBindingAvecPlusieursJndiMockLoader() throws NamingException {
		Object s = InitialContext.doLookup("string/1");
		assertEquals("ma/string/1", s);
		Object i = InitialContext.doLookup("Integer/1");
		assertEquals(new Integer(1), i);
		Object s2 = InitialContext.doLookup("string/2");
		assertEquals("ma/string/2", s2);
	}

	@Test(expected = NamingException.class)
	public void testUnresolvableJndiBinding() throws NamingException {
		InitialContext.doLookup("test/unresolvable");
	}
}
