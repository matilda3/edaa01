package queue_singlelinkedlist;
import java.util.*;


public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	
	
	public static void  main(String[] args) {
		FifoQueue<Integer> q = new FifoQueue<>();
		FifoQueue<Integer> q2 = new FifoQueue<>();
		q.offer(1);
		q.offer(2);
		q2.offer(3);
		q.append(q2);
		System.out.println(q);
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.peek());
		
	}
	
	
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> temp = new QueueNode<>(e);
		if(size == 0) {
			temp.next = temp;
		}else {
			temp.next = last.next;
			last.next = temp;
		}
		//list.offer(e);
		last = temp;
		size++;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(size == 0) {
			return null;
		}
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if(size == 0) {
			return null;
		}
		QueueNode<E> temp = last.next;
		last.next = last.next.next;
		size--;
		if(size == 0) {
			last = null;
		}
		return temp.element;
	}
	
	/**
	 * Appends the spcified queue to this queue
	 * post: all elements from the specified queue are appended
	 * to this queue. the specified queue (q) is empty after the 
	 * call
	 * @param q the queue to append
	 * @throws illegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q) {
		if(this.equals(q)) {
			throw new IllegalArgumentException();
		}
		if(last == null) {//append q to empty
			last = q.last;
			size = q.size();//size is size of q2
		}else if( q.last != null){
			QueueNode<E> temp = null;
			temp = q.last.next; //head of q2
			q.last.next = last.next;//replace q2 head with head of q1
			last.next = temp;
			last = q.last;
			size += q.size();//update size
		}
		//empty q2
		q.last = null;
		q.size = 0;
		
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}
	
	public class QueueIterator implements Iterator<E>{
		private QueueNode<E> pos;
		private int ctr;
		
		private QueueIterator() {
			pos = last;
			ctr = 0;
		}
		public boolean hasNext() {
			if(ctr >= size || size == 0) {
				return false;
			}else {
				return true;
			}
		}
		public E next() {
			//testern kollar efter throwing an exception
			if( !hasNext()) {
				throw new NoSuchElementException();
			}
			E elem = pos.next.element;
			pos = pos.next;
			ctr++;
			return elem;
		}
	}

}
