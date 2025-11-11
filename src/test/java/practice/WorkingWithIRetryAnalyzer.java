package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkingWithIRetryAnalyzer {

	@Test(retryAnalyzer = genericutilities.IRetryImplementation.class)
	public void t1() {
		Assert.assertEquals("hdfc", "hfdc");
	}
}
