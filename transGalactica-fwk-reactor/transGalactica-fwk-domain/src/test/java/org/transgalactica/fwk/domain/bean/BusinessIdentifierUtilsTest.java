package org.transgalactica.fwk.domain.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.Map;

import org.junit.Test;
import org.transgalactica.fwk.domain.bean.BusinessIdentifier;
import org.transgalactica.fwk.domain.bean.BusinessIdentifierUtils;

public class BusinessIdentifierUtilsTest {

	@Test
	public void getBusinessIdentifiersValues_null() {
		assertNull(BusinessIdentifierUtils.getBusinessIdentifiersValues(null));
	}

	@Test
	public void getBusinessIdentifiersValues_noBI() {
		Map<String, Serializable> bi = BusinessIdentifierUtils.getBusinessIdentifiersValues(new CompositeId("test", 2));
		assertNotNull(bi);
		assertEquals(0, bi.size());
	}

	@Test
	public void getBusinessIdentifiersValues_simpleBI() {
		Map<String, Serializable> bi = BusinessIdentifierUtils.getBusinessIdentifiersValues(new LongIdBo(3L));
		assertNotNull(bi);
		assertEquals(1, bi.size());
		assertTrue(bi.containsKey("longId"));
		assertEquals(3L, bi.get("longId"));
	}

	@Test
	public void getBusinessIdentifiersValues_compositeBI() {
		Map<String, Serializable> bi = BusinessIdentifierUtils.getBusinessIdentifiersValues(new CompositeIdBo(
				new CompositeId("cle1", 5), "autre cle", "un champ"));
		assertNotNull(bi);
		assertEquals(3, bi.size());
		assertTrue(bi.containsKey("id_1"));
		assertEquals(new CompositeId("cle1", 5), bi.get("id_1"));
		assertTrue(bi.containsKey("id_2"));
		assertEquals("autre cle", bi.get("id_2"));
		assertTrue(bi.containsKey("getId3"));
		assertEquals("valeur id 3", bi.get("getId3"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getBusinessIdentifiersValues_methodWithParams() {
		BusinessIdentifierUtils.getBusinessIdentifiersValues(new LongIdBo() {
			private static final long serialVersionUID = 1L;

			@BusinessIdentifier
			public String invalidMethod(String param) {
				return "";
			}
		});
	}

	@Test(expected = IllegalArgumentException.class)
	public void getBusinessIdentifiersValues_methodWithNoReturn() {
		BusinessIdentifierUtils.getBusinessIdentifiersValues(new LongIdBo() {
			private static final long serialVersionUID = 1L;

			@BusinessIdentifier
			public void invalidMethod() {
			}
		});
	}
}
