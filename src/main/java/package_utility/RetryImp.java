package package_utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryImp implements IRetryAnalyzer{
     int count = 0;
     int retryLimit = 1;
	@Override
	public boolean retry(ITestResult result) 
	{
  if(count<retryLimit)
  {   
	  count++;
	  return true;
  }

		return false;
	}

}
