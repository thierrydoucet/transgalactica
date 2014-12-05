package org.transgalactica.web.recent;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;
import org.transgalactica.web.recent.CyclicMostRecentQueue;
import org.transgalactica.web.recent.impl.AbstractRecentLabel;

public class CyclicMostRecentQueueTest {

	@Test
	public void addElement() {
		CyclicMostRecentQueue<MockRecentLabel> queue = new CyclicMostRecentQueue<>();
		assertThat(queue, hasSize(0));
		queue.add(new MockRecentLabel("1"));
		assertThat(queue, hasSize(1));
		assertThat(queue, contains(new MockRecentLabel("1")));
	}

	@Test
	public void addExistingElement() {
		CyclicMostRecentQueue<MockRecentLabel> queue = new CyclicMostRecentQueue<>();
		assertThat(queue, hasSize(0));
		queue.add(new MockRecentLabel("1"));
		queue.add(new MockRecentLabel("1"));
		assertThat(queue, hasSize(1));
		assertThat(queue, contains(new MockRecentLabel("1")));
	}

	@Test
	public void addElementQueueFull() {
		CyclicMostRecentQueue<MockRecentLabel> queue = new CyclicMostRecentQueue<>(3);
		assertThat(queue, hasSize(0));
		queue.add(new MockRecentLabel("1"));
		queue.add(new MockRecentLabel("2"));
		queue.add(new MockRecentLabel("3"));
		queue.add(new MockRecentLabel("4"));
		assertThat(queue, hasSize(3));
		assertThat(queue, contains(new MockRecentLabel("4"), new MockRecentLabel("3"), new MockRecentLabel("2")));
	}

	@Test
	public void serializable() throws IOException, ClassNotFoundException {
		CyclicMostRecentQueue<MockRecentLabel> queue = new CyclicMostRecentQueue<>();
		queue.add(new MockRecentLabel("1"));

		ByteArrayOutputStream bin = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bin);

		out.writeObject(queue);
		out.flush();
		out.close();

		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bin.toByteArray()));
		Object ts = in.readObject();
		assertNotNull(ts);
	}

	private static class MockRecentLabel extends AbstractRecentLabel<String> {
		private static final long serialVersionUID = 1L;

		public MockRecentLabel(String key) {
			super(key);
		}
	}

}
