package Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v129.page.Page;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


import Base.Base;
import Pages.NavigationToFlight;
import Pages.SearchFlightPage;
import Pages.BookingDetailsPage;

public class DashboardTest extends Base {

    @Test(priority=1)
    public void verifyDashboardTitle() {
        Pages.DashboardPage dashboard = new Pages.DashboardPage(getDriver());
        String actualTitle = dashboard.getDashboardTitle();
        String expectedTitle = "Traveloka - Get Up to 30% Off for Flights, Hotels and Activities!"; 

        System.out.println("Actual Title: " + actualTitle);
        System.out.println("Expected Title: " + expectedTitle);

        Assert.assertEquals(actualTitle, expectedTitle, "Dashboard title does not match!");
    }
    
    @Test(priority=2)
    public void navigateToFlightBookingSection() {

    	NavigationToFlight navigationToFlight = new NavigationToFlight(getDriver());
    	
    	
       navigationToFlight.ClickOnFlight();
       WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));


      By tripOption = By.xpath("//h4[normalize-space()='One-way / Round-trip']");

       WebElement tripElement = wait.until(ExpectedConditions.visibilityOfElementLocated(tripOption));
       Assert.assertTrue(tripElement.isDisplayed(), "Failed to navigate to Flight Booking Section!");
    }
    @Test(priority=3)
    public void testSearchFlight() throws InterruptedException {
        WebDriver driver = getDriver();
        SearchFlightPage searchFlightPage = new SearchFlightPage(driver);  
        searchFlightPage.enterFromLocation("singapore");
        searchFlightPage.enterToLocation("Kuala Lumpur");
        searchFlightPage.clickSearchButton();
        By searchResultLocator = By.xpath("(//h3[@role='heading'])[1]");
        searchFlightPage.clickSearchButton();
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))  
                .pollingEvery(Duration.ofSeconds(1))  
                .ignoring(NoSuchElementException.class);    
        WebElement searchResult = wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultLocator));   
        Assert.assertNotNull(searchResult, "Flight search results are not displayed!");
        Assert.assertTrue(searchResult.isDisplayed(), "Flight search results are not visible!"); 
    
}
    
    @Test(priority = 4)
    public void testFilterAndVerifySearchResults() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By noOfTransitFilter = By.xpath("//div[contains(text(),'No. of Transit')]");
        WebElement filterElement = driver.findElement(noOfTransitFilter);
        filterElement.click();
        Assert.assertTrue(filterElement.isDisplayed(), "No. of Transit filter was not clicked successfully!");
        By resultsContainer = By.xpath("//h4[normalize-space()='All flights']");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(resultsContainer));
        String actualText = element.getText();
        String expectedText = "All flights";
        System.out.println("Actual Text: " + actualText);
        System.out.println("Expected Text: " + expectedText);
        Assert.assertEquals(actualText, expectedText, "Text does not match!");
    }
    @Test(priority = 5)
    public void testChooseAndSelectFlight() throws InterruptedException {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By chooseFlightButtons = By.xpath("//div[text()='Choose']");
        List<WebElement> chooseButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(chooseFlightButtons));
        for (int i = 0; i < chooseButtons.size(); i++) {
            if (chooseButtons.get(i).isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", chooseButtons.get(i));
                Thread.sleep(1000); 
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chooseButtons.get(i));

                System.out.println("Clicked on 'Choose' button at index: " + i);
                break;
            }
        }

        By selectFlightButtons = By.xpath("//div[text()='Select']");
        List<WebElement> selectButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(selectFlightButtons));

        for (int i = 0; i < selectButtons.size(); i++) {
            if (selectButtons.get(i).isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectButtons.get(i));
                Thread.sleep(1000);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectButtons.get(i));

                System.out.println("Clicked on 'Select' button at index: " + i);
                break;
            }
        }

        System.out.println("Successfully chose and selected the flight!");
    }
   
    @Test(priority = 6)
    public void testEnterBookingDetails() {
        BookingDetailsPage bookingPage = new BookingDetailsPage(getDriver());
        bookingPage.enterFirstName("Atul");
        bookingPage.enterLastName("Kumar");
        bookingPage.selectCountryCode("India (+91)");
        bookingPage.enterPhoneNumber("10000000000");
        bookingPage.enterEmail("atul1000@gmail.com");
       // bookingPage.selectTitle("mr");

        System.out.println("✅ Booking details entered successfully!");
    }

}
