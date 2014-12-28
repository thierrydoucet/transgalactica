package org.transgalactica.fwk.test.bean;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.BeanUtils;

/**
 * Classe de test générique permettant de tester la serialisation des TOs. <br>
 * Elle doit être sous classée puis fournir une méthode statique taggée avec
 * <code>@org.junit.Parameters</code>, retournant la liste des classes de TO a
 * tester. <br>
 * 
 * <pre>
 * public static Collection&lt;Class[]&gt; getBeansClazz() {
 * 	Collection&lt;Class[]&gt; beansClazz = new ArrayList&lt;Class[]&gt;();
 * 	beansClazz.add(new Class[] { DummyTo.class });
 * 	return beansClazz;
 * }
 * </pre>
 * 
 * <br>
 * Un exemple se trouve dans la classe de test unitaire :
 * org.transgalactica.fwk.test.domaine.bo.AbstractToGenericTestTest
 */
@RunWith(Parameterized.class)
public abstract class AbstractSerializableGenericTest {

	private Class<? extends Serializable> beanClazz;

	public AbstractSerializableGenericTest(Class<? extends Serializable> beanClazz) {
		this.beanClazz = beanClazz;
	}

	@Test
	public void testSerializable() throws IOException, ClassNotFoundException {
		Object t1 = BeanUtils.instantiateClass(beanClazz);

		ByteArrayOutputStream bin = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bin);

		out.writeObject(t1);
		out.flush();
		out.close();

		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bin.toByteArray()));
		Object ts = in.readObject();
		assertNotNull(ts);
	}
}
