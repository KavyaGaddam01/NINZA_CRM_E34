package practice;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WorkingWithAssertions {

	@Test
	public void test() {

		String s=null;
		System.out.println("start");
		Assert.assertNotNull(s);
		System.out.println("end");

	}
}


//System.out.println("start");
//SoftAssert soft=new SoftAssert();
//soft.assertNotEquals("hdfc", "hdfc");
//System.out.println("end");
//soft.assertAll();
