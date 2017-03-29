package wdsr.exercise2.procon;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Condition;

/**
 * Task: implement Buffer interface without using any *Queue classes from
 * java.util.concurrent package. Any combination of "synchronized", *Lock,
 * *Semaphore, *Condition, *Barrier, *Latch is allowed.
 */
public class BufferManualImpl implements Buffer {
	Queue<Order> orders = new LinkedList<Order>();
	Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();

	private void ifFull() throws InterruptedException {
		while (orders.size() == 1200)
			notFull.await();
	}

	private void ifEmpty() throws InterruptedException {
		while (orders.isEmpty())
			notEmpty.await();
	}

	public void submitOrder(Order order) throws InterruptedException {
		// TODO
		lock.lock();
		try {
			ifFull();
			orders.add(order);
			notEmpty.signal();
		} finally {
			lock.unlock();
		}

	}

	public Order consumeNextOrder() throws InterruptedException {
		Order tmp;
		lock.lock();
		try {
			ifEmpty();
			tmp = orders.poll();
			notFull.signal();
			return tmp;
		} finally {
			lock.unlock();
		}

	}
}