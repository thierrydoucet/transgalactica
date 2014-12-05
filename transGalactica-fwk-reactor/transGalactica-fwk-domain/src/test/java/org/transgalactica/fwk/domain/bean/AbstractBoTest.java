package org.transgalactica.fwk.domain.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.transgalactica.fwk.domain.bean.AbstractBo;

@RunWith(Parameterized.class)
public class AbstractBoTest {

	private AbstractBo b1;

	private AbstractBo b1Clone;

	private AbstractBo b2;

	private AbstractBo bIdNull;

	private AbstractBo bIdNullClone;

	public AbstractBoTest(AbstractBo b1, AbstractBo b1Clone, AbstractBo b2, AbstractBo bIdNull, AbstractBo bIdNullClone) {
		this.b1 = b1;
		this.b1Clone = b1Clone;
		this.b2 = b2;
		this.bIdNull = bIdNull;
		this.bIdNullClone = bIdNullClone;
	}

	@Parameters
	public static Collection<AbstractBo[]> getBeans() {
		Collection<AbstractBo[]> beans = new ArrayList<AbstractBo[]>();
		beans.add(new LongIdBo[] { new LongIdBo(1L), new LongIdBo(1L), new LongIdBo(2L), new LongIdBo(), new LongIdBo() });
		beans.add(new CompositeIdBo[] { new CompositeIdBo(new CompositeId("cle1", 5), "autre cle", "un champ"),
				new CompositeIdBo(new CompositeId("cle1", 5), "autre cle", "un champ"),
				new CompositeIdBo(new CompositeId("cle2", 10), "autre cle", "un champ"),
				new CompositeIdBo(null, null, null), new CompositeIdBo(null, null, null) });
		return beans;
	}

	@Test
	public void testCollection() {
		Set<AbstractBo> set = new HashSet<AbstractBo>();
		set.add(b1);
		assertTrue(set.contains(b1Clone));
	}

	@Test
	public void testEquals() {
		assertTrue(b1.equals(b1));
		assertTrue(b1.equals(b1Clone));
		assertTrue(b1Clone.equals(b1));
		assertFalse(b1.equals(b2));
		assertFalse(b2.equals(b1));
		assertFalse(b1.equals(null));
	}

	@Test
	public void testEqualsBoIdNull() {
		assertTrue(bIdNull.equals(bIdNull));
		assertTrue(bIdNull.equals(bIdNullClone));
		assertTrue(bIdNullClone.equals(bIdNull));
		assertFalse(bIdNull.equals(b2));
		assertFalse(b2.equals(bIdNull));
		assertFalse(b1.equals(null));
	}

	@Test
	public void testSerializable() throws IOException, ClassNotFoundException {
		ByteArrayOutputStream bin = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bin);

		out.writeObject(b1);
		out.flush();
		out.close();

		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bin.toByteArray()));
		assertEquals(b1Clone, in.readObject());
	}
}
