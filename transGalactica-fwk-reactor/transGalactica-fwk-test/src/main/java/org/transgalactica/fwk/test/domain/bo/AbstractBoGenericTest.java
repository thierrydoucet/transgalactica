package org.transgalactica.fwk.test.domain.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.BeanUtils;

/**
 * 
 * Classe de test générique permettant de tester les methodes equals/hashcode et
 * la serialisation des BOs. <br>
 * Elle doit être sous classée puis fournir une méthode statique taggée avec
 * <code>@org.junit.Parameters</code>, retournant la liste des classes de BO a
 * tester. <br>
 * 
 * <pre>
 * public static Collection&lt;Object[]&gt; getBeans() {
 * 	Collection&lt;Object[]&gt; beans = new ArrayList&lt;Object[]&gt;();
 * 	beans.add(new Object[] { DummyBo.class, new BusinessIdentifierSetterVisitor&lt;DummyBo&gt;() {
 * 		public void setBusinessIdentifierFirstValue(DummyBo bo) {
 * 			bo.setBusinessIdentifier(1L);
 * 		}
 * 
 * 		public void setBusinessIdentifierSecondValue(DummyBo bo) {
 * 			bo.setBusinessIdentifier(2L);
 * 		}
 * 	} });
 * 	return beans;
 * }
 * </pre>
 * 
 * <br>
 * Un exemple se trouve dans la classe de test unitaire :
 * org.transgalactica.fwk.test.domaine.bo.AbstractBoGenericTestTest
 * 
 */
@RunWith(Parameterized.class)
public abstract class AbstractBoGenericTest {

	public abstract static class BusinessIdentifierSetterVisitor<T extends Object> {

		public BusinessIdentifierSetterVisitor() {
		}

		public abstract void setBusinessIdentifierFirstValue(T bo);

		public abstract void setBusinessIdentifierSecondValue(T bo);

		public void setBusinessIdentifierNullValue(T bo) {
			// Default to NOP
		}
	}

	private Object boId1;

	private Object boId1Clone;

	private Object boId2;

	private Object boIdNull;

	private Object boIdNullClone;

	public AbstractBoGenericTest(Class<Object> clazzOfBo, BusinessIdentifierSetterVisitor<Object> visitor) {
		boId1 = BeanUtils.instantiateClass(clazzOfBo);
		visitor.setBusinessIdentifierFirstValue(boId1);
		boId1Clone = BeanUtils.instantiateClass(clazzOfBo);
		visitor.setBusinessIdentifierFirstValue(boId1Clone);
		boId2 = BeanUtils.instantiateClass(clazzOfBo);
		visitor.setBusinessIdentifierSecondValue(boId2);
		boIdNull = BeanUtils.instantiateClass(clazzOfBo);
		visitor.setBusinessIdentifierNullValue(boIdNull);
		boIdNullClone = BeanUtils.instantiateClass(clazzOfBo);
		visitor.setBusinessIdentifierNullValue(boIdNullClone);
	}

	@Test
	public void testCollection() {
		Set<Object> set = new HashSet<Object>();
		set.add(boId1);
		assertTrue(set.contains(boId1Clone));
	}

	@Test
	public void testEquals() {
		assertTrue(boId1.equals(boId1));
		assertTrue(boId1.equals(boId1Clone));
		assertTrue(boId1Clone.equals(boId1));
		assertFalse(boId1.equals(boId2));
		assertFalse(boId2.equals(boId1));
		assertFalse(boId1.equals(null));
	}

	@Test
	public void testEqualsBoWithIdentifierNull() {
		assertTrue(boIdNull.equals(boIdNull));
		assertTrue(boIdNull.equals(boIdNullClone));
		assertTrue(boIdNullClone.equals(boIdNull));
		assertFalse(boIdNull.equals(null));
	}

	@Test
	public void testSerializable() throws IOException, ClassNotFoundException {
		ByteArrayOutputStream bin = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bin);

		out.writeObject(boId1);
		out.flush();
		out.close();

		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bin.toByteArray()));
		assertEquals(boId1Clone, in.readObject());
	}
}
