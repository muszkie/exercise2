package wdsr.exercise2.counter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Marek on 05.03.2016.
 * 
 * Task: use {@see java.util.concurrent.atomic.AtomicInteger} to make CountingFacadeWithAtomicTest pass. 
 */
public class CountingFacadeWithAtomic implements CountingFacade {
	private final BusinessService businessService;
	
	private int invocationCounter;
	AtomicInteger tmp = new AtomicInteger(invocationCounter);
	
	public CountingFacadeWithAtomic(BusinessService businessService) {
		this.businessService = businessService;
	}
		
	public void countAndInvoke() {
		
		invocationCounter = tmp.incrementAndGet();
		businessService.executeAction();
	}
	
	public int getCount() {
		return invocationCounter;
	}
}
