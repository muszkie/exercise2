package wdsr.exercise2.startthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BusinessServiceWithExecutor {
	private NumericHelper helper;
	private ExecutorService executorService;	
	
	public BusinessServiceWithExecutor(NumericHelper helper) {
		this.helper = helper;
		
	}

	/**
	 * Calculates Fibonacci number asynchronously and invokes the callback when result is available.
	 * This method returns immediately. 
	 * @param n Which Fibonacci number should be computed.
	 * @param callback Callback to be invoked when Fibonacci number is found.
	 */
	public void computeFibonacci(int n, FibonacciCallback callback) {
		// TODO Task: execute the logic below using java.util.concurrent.ExecutorService
		// The ExecutorService should be declared as a field, not a local variable.
		executorService =  Executors.newFixedThreadPool(10);		
		class runnable implements Runnable{
			public long result;
			int n;
			public runnable(int n){
				this.n=n;
			}
			@Override
			public void run() {
				long result = helper.findFibonacciValue(n);
				callback.fibonacciComputed(result);

				
			}
			
		}
		executorService.execute(new runnable(n));
	}
}
