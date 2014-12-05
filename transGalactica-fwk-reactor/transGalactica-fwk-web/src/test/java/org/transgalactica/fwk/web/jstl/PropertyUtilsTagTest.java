package org.transgalactica.fwk.web.jstl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.transgalactica.fwk.web.jstl.PropertyUtilTag;

public class PropertyUtilsTagTest {

	@Test
	public void testHasPropertySimpleOk() {
		assertTrue(PropertyUtilTag.hasProperty(new Bean2(), "string"));
	}

	@Test
	public void testHasPropertySimpleKo() {
		assertFalse(PropertyUtilTag.hasProperty(new Bean2(), "none"));
	}

	@Test
	public void testHasPropertyNestedPathOk() {
		assertTrue(PropertyUtilTag.hasProperty(new Bean1(), "bean2.string"));
	}

	@Test
	public void testHasPropertyNestedPathKo() {
		assertFalse(PropertyUtilTag.hasProperty(new Bean1(), "bean2.none"));
	}

	@Test
	public void testHasPropertyIndexedOk() {
		assertTrue(PropertyUtilTag.hasProperty(new Bean2(), "strings[1]"));
	}

	@Test
	public void testHasPropertyIndexedKo() {
		assertFalse(PropertyUtilTag.hasProperty(new Bean2(), "strings[10]"));
	}

	public static class Bean1 {
		private Bean2 bean2 = new Bean2();

		public Bean1() {
		}

		public Bean2 getBean2() {
			return bean2;
		}

		public void setBean2(Bean2 bean2) {
			this.bean2 = bean2;
		}
	}

	public static class Bean2 {
		private String string;

		private String[] strings = new String[2];

		public Bean2() {
		}

		public String getString() {
			return string;
		}

		public void setString(String string) {
			this.string = string;
		}

		public String[] getStrings() {
			return strings;
		}

		public void setStrings(String[] strings) {
			this.strings = strings;
		}
	}
}
