package org.transgalactica.management.data.core.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class RequiredSystemPropertiesTest {

	@Test
	public void testPresenceUserHomeProperty() {
		String userHome = System.getProperty("user.home");
		assertNotNull(userHome);
		assertTrue(StringUtils.isNotBlank(userHome));
	}
}
