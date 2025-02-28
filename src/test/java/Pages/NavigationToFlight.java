package Pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationToFlight {
	private WebDriver driver;
	private WebDriverWait wait;

	private By flightsTab = By.xpath("//div[@data-testid='product-pill-Flights']"); // Update this if needed

	public NavigationToFlight(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void ClickOnFlight() {
		By flightsTab = By.xpath("//div[@data-testid='product-pill-Flights']"); // Updated XPath
		WebElement flights = wait.until(ExpectedConditions.elementToBeClickable(flightsTab));

		try {
			flights.click(); 
		} catch (Exception e) {
			System.out.println("Normal click failed, trying JavaScript click...");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", flights);
		}
	}

}
