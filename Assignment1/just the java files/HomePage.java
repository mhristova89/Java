package pages.components;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	@FindBy(how = How.CSS, using = ".header-login-button")
	private WebElement loginButton;
	@FindBy(how = How.ID, using = "login_form[username]")
	private WebElement usernameField;
	@FindBy(how = How.ID, using = "login-modal-password-input")
	private WebElement passwordField;
	@FindBy(how = How.CSS, using = "div.modal-action-bar .modal-submit-button")
	private WebElement loginFormSubmitButton;
	@FindBy(how = How.CSS, using = ".header-deposit-button")
	public WebElement depositButton;
	@FindBy(how = How.CSS, using = "#inputBetStake")
	private WebElement betStake;
	@FindBy(how = How.CSS, using = ".odd-container")
	private List<WebElement> elements;
	@FindBy(how = How.CSS, using = ".btnPlaceBet")
	private WebElement placeBet;
	@FindBy(how = How.CSS, using = ".status-label-running")
	private WebElement betSlipRunningLabel;
	@FindBy(how = How.CSS, using = ".user-balance-item:first-of-type .user-balance-item-amount")
	private WebElement customerBalance;
	

	private WebDriver driver;
	private String url = "https://www.1000000bet.com/sports";

	public HomePage() {
		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		PageFactory.initElements(driver, this);
		driver.manage().window().maximize();
	}

	/**
	 * A method which logs in the user
	 * 
	 * @author Mariya H.
	 * @param username the username you would like to log in with
	 * @param password is the password you'd like to log in with
	 *
	 */
	public void logIn(String username, String password) {
		loginButton.click();
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginFormSubmitButton.click();
	}
	
	/**
	 * A method which checks the presence of an element and return true or false
	 * 
	 * @author Mariya H.
	 * @param WebElement is the element you want to see if present on the page
	 *
	 */
	public boolean presence(WebElement el) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return el.isDisplayed();
	}
	
	/**
	 * A method which places a bet
	 * 
	 * @author Mariya H.
	 * @param amount is the Stake amount
	 *
	 */
	public void placeBet(double amount) {
		elements.get(3).click();
		betStake.sendKeys(String.valueOf(amount));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		placeBet.click();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(betSlipRunningLabel));		
	}
	
	public String balanceUpdate() {
		String balance = customerBalance.getText();
		return balance;
	}

	public void quit() {
		this.driver.quit();
	}

	public void goTo() {
		this.driver.get(this.url);
	}
}
