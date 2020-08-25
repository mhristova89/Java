package positive.tests;

import org.junit.Assert;
import org.junit.Test;

import pages.components.HomePage;

public class HomePageTests {
	

	@Test
	public void placingABet() {
		HomePage homePage = new HomePage();
		homePage.goTo();
		
		homePage.logIn("tu_assessment3", "Assessment@123");			
		Assert.assertTrue(homePage.presence(homePage.depositButton));		
		Assert.assertEquals("215.05 ˆ", homePage.balanceUpdate());		
		homePage.placeBet(5.00);		
		Assert.assertEquals("210.05 ˆ", homePage.balanceUpdate());
		
		homePage.quit();
	}
}
