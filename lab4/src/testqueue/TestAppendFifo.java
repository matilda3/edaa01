package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Queue;

import queue_singlelinkedlist.FifoQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAppendFifo {
	
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;

	@BeforeEach
	void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}

	@Test
	void test2Empty() {
		assertTrue(q1.isEmpty());
		assertTrue(q2.isEmpty());
		q1.append(q2);
		assertTrue(q1.isEmpty());
	}
	
	@Test
	void testEmptyToNon() {
		q1.offer(1);
		q1.offer(2);
		q1.append(q2);
		assertTrue(q2.isEmpty());
		assertEquals(2, q1.size());
		assertEquals(1, q1.poll());
		assertEquals(2, q1.poll());
	}
	@Test
	void testNonToEmpty() {
		q2.offer(1);
		q2.offer(2);
		q1.append(q2);
		assertEquals(2, q1.size());
		assertEquals(1, q1.poll());
		assertEquals(2, q1.poll());
		assertTrue(q2.isEmpty());
	}
	
	@Test
	void testFullConcat() {
		q1.offer(1);
		q1.offer(2);
		q2.offer(3);
		q1.append(q2);
		assertEquals(3, q1.size());
		assertEquals(1, q1.poll());
		assertEquals(2, q1.poll());
		assertEquals(3, q1.poll());
		assertTrue(q2.isEmpty());
	}
	@Test
	void testSelfConcat() {
		assertThrows(IllegalArgumentException.class, () -> q1.append(q1));
	}

}
