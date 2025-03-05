package Pages;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingDetailsPage {
    private WebDriver driver;

    private By firstNameInput = By.xpath("(//label[@id='name.first']/following-sibling::div//input)[1]");
    private By lastNameInput = By.xpath("(//label[@id='name.last']/following-sibling::div//input)[1]");
    private By countryCodeDropdown = By.xpath("//select[@aria-label='Country Number']");
    private By phoneNumberInput = By.xpath("//input[@aria-label='Phone Number']");
    private By emailInput = By.xpath("//input[@aria-labelledby='emailAddress']");
    private By titleDropdown = By.xpath("//div[@aria-labelledby='title']");
    //private By FirstName =By.xpath("//input[@aria-labelledby='name.first'])[2]");
    private By firstNameLocator = By.xpath("(//input[@aria-labelledby='name.first'])[2]");
    private By LastNameLocator = By.xpath("(//input[@aria-labelledby='name.last'])[2]");
    private By dayLocator = By.xpath("(//select[@data-testid='day-datepicker'])[1]");
    private By monthLocator = By.xpath("(//select[@data-testid='month-datepicker'])[1]");
    private By yearLocator = By.xpath("(//select[@data-testid='year-datepicker'])[1]");
    private By NationalityLocator = By.xpath("//div[contains(@aria-labelledby,'nationality')]//select");
    private By Scroll =By.xpath("//div[contains(text(),'Passport Information");
    private By PassportInformation = By.xpath("//div[contains(text(),'Passport Information')]");
    private By PassportNumber =By.xpath("//input[@aria-labelledby='travelerID.number']");
    private By issueCountry = By.xpath("//div[@aria-labelledby='travelerID.countryOfIssuance']//select");
    private By SelectDay = By.xpath("(//select[@data-testid='day-datepicker'])[2]");
    private By SelectMonth = By.xpath("(//select[@data-testid='month-datepicker'])[2]");
    private By Selectyear = By.xpath("(//select[@data-testid='year-datepicker'])[2]");
    private By continueButton =By.xpath("//div[@class='css-901oao css-bfa6kz r-jwli3a r-a5wbuh r-cygvgh r-b88u0q r-1iukymi r-q4m81j']");
    
    public BookingDetailsPage(WebDriver driver) {
        this.driver = driver;
    }
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void selectCountryCode(String countryText) {
        WebElement dropdown = driver.findElement(countryCodeDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(countryText);
    }

    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public void enterEmail(String email) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
    	emailElement.sendKeys(email);

    }
    
    public void selectTitleUsingKeys() {
        try {
            System.out.println("Method execution started");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for the dropdown to be visible
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(titleDropdown));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
            Thread.sleep(1000);  // Wait for the dropdown to open

            // Use keyboard keys to select option
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ARROW_DOWN).pause(500).sendKeys(Keys.ENTER).perform();

            System.out.println("'Mr.' selected using keyboard!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void enterTravelDetails() throws InterruptedException {
    	//Enter first Name
        
        WebElement firstName = driver.findElement(firstNameLocator);
        firstName.sendKeys("Atul");

        
        //Enter last name
        WebElement lastName = driver.findElement(LastNameLocator);
        lastName.sendKeys("Singh");
        
        //Select Day
        WebElement day = driver.findElement(dayLocator);
        Select selectDay = new Select(day);
        selectDay.selectByVisibleText("5"); 
        
        //Select Month
        WebElement month = driver.findElement(monthLocator);
        Select selectMonth = new Select(month);
        selectMonth.selectByVisibleText("January"); 
        
        //Select Year
        WebElement year = driver.findElement(yearLocator);
        Select selectYear = new Select(year);
        selectYear.selectByVisibleText("1999"); 
        
        //Select Nationality
        WebElement nationality = driver.findElement(NationalityLocator);
        Select selectNationality = new Select(nationality);
        selectNationality.selectByVisibleText("India"); 
    	Thread.sleep(3000);
 
    }
    public void enterPertionalDetails() throws InterruptedException {
    	Thread.sleep(3000);
    	  //Scroll
    	  
         WebElement element = driver.findElement(PassportInformation);
         element.isDisplayed();
         
         //Enter Passport Number
         WebElement passportNumber = driver.findElement(PassportNumber);
         passportNumber.sendKeys("A1234567");
         
         
      // Find the select dropdown element
         WebElement issueCountryDropdown = driver.findElement(issueCountry);

         Select selectIssueCountry = new Select(issueCountryDropdown);

         selectIssueCountry.selectByVisibleText("India");


         WebElement day = driver.findElement(SelectDay);
         Select selectDayDropdown = new Select(day);
         selectDayDropdown.selectByVisibleText("10");

         // Select Month
         WebElement month = driver.findElement(SelectMonth);
         Select selectMonthDropdown = new Select(month);
         selectMonthDropdown.selectByVisibleText("January");

         // Select Year
         WebElement year = driver.findElement(Selectyear);
         Select selectYearDropdown = new Select(year);
         selectYearDropdown.selectByVisibleText("2033");         
    }
    public void clickContinueButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));

        // Click using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);
        
        System.out.println("Continue button clicked using JavaScript.");
    }


    }
