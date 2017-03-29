package wdsr.exercise2.procon;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
 * Task: implement Buffer interface using one of *Queue classes from java.util.concurrent package.
 */
public class BufferQueueImpl implements Buffer {
	BlockingQueue<Order> orders = new ArrayBlockingQueue<Order>(100000);
	public void submitOrder(Order order) throws InterruptedException {
		
		orders.put(order);
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		
		return orders.take();
	}
}
