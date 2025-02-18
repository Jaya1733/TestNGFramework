package qa.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
	
	
	
	private int count = 0;
    private int maxRetryCount = 3; //  set the maximum retry count = 3

	@Override
	public boolean retry(ITestResult result) {
		
		 if (count < maxRetryCount) {
	            count++;
	            return true; // Return true to retry the test
	        }
		return false;
	}
	
}
