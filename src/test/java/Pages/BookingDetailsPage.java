package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BookingDetailsPage {
    private WebDriver driver;

    private By firstNameInput = By.xpath("(//label[@id='name.first']/following-sibling::div//input)[1]");
    private By lastNameInput = By.xpath("(//label[@id='name.last']/following-sibling::div//input)[1]");
    private By countryCodeDropdown = By.xpath("//select[@aria-label='Country Number']");
    private By phoneNumberInput = By.xpath("//input[@aria-label='Phone Number']");
    private By emailInput = By.xpath("//input[@aria-labelledby='emailAddress']");
    private By titleDropdown = By.xpath("//div[@aria-labelledby='title']");
    
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
        driver.findElement(emailInput).sendKeys(email);
    }
    
    public void selectTitleUsingKeys() {
        WebElement dropdown = driver.findElement(titleDropdown);
        dropdown.click();

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

        System.out.println("'Mr.' selected using Keyboard!");
    }
    public void enterTravelDetails() throws InterruptedException {
    	//Enter first Name
        By firstNameXpath = By.xpath("(//input[@aria-labelledby='name.first'])[2]");
        WebElement firstName = driver.findElement(firstNameXpath);
        firstName.sendKeys("Atul");
        
        //Enter last name
        By lastNameXpath = By.xpath("(//input[@aria-labelledby='name.last'])[2]");
        WebElement lastName = driver.findElement(lastNameXpath);
        lastName.sendKeys("Mishra");
        
        //Select Day
        WebElement day = driver.findElement(By.xpath("(//select[@data-testid='day-datepicker'])[1]"));
        Select selectDay = new Select(day);
        selectDay.selectByVisibleText("5"); 
        
        //Select Month
        WebElement month = driver.findElement(By.xpath("(//select[@data-testid='month-datepicker'])[1]"));
        Select selectMonth = new Select(month);
        selectMonth.selectByVisibleText("January"); 
        
        //Select Year
        WebElement year = driver.findElement(By.xpath("(//select[@data-testid='year-datepicker'])[1]"));
        Select selectYear = new Select(year);
        selectYear.selectByVisibleText("1999"); 
        
        //Select Nationality
        WebElement nationality = driver.findElement(By.xpath("//div[contains(@aria-labelledby,'nationality')]//select"));
        Select selectNationality = new Select(nationality);
        selectNationality.selectByVisibleText("India"); 
        
        
    }
    public void enterPertionalDetails() throws InterruptedException {
    	Thread.sleep(3000);
    	  //Scroll
    	  WebElement scroll = driver.findElement(By.xpath("//div[contains(text(),'Passport Information')]"));
    	  Actions actions = new Actions(driver);
    	  actions.moveToElement(scroll).perform();
    	    
    	 By xpathLocator = By.xpath("//div[contains(text(),'Passport Information')]");
         WebElement element = driver.findElement(xpathLocator);
         element.isDisplayed();
         
         
         //Enter Passport Number
         By passportXpath = By.xpath("//input[@aria-labelledby='travelerID.number']");
         WebElement passportNumber = driver.findElement(passportXpath);
         passportNumber.sendKeys("A1234567");
         
         //Select Country of issue
         WebElement issueCountry = driver.findElement(By.xpath("//div[@aria-labelledby='travelerID.countryOfIssuance']//select"));
         Select selectIssueCountry = new Select(issueCountry);
         selectIssueCountry.selectByVisibleText("India"); 
         
         //Select Day
         WebElement day = driver.findElement(By.xpath("(//select[@data-testid='day-datepicker'])[2]"));
         Select selectDay = new Select(day);
         selectDay.selectByVisibleText("10"); 
         
         //Select Month
         WebElement month = driver.findElement(By.xpath("(//select[@data-testid='month-datepicker'])[2]"));
         Select selectMonth = new Select(month);
         selectMonth.selectByVisibleText("January"); 
         
         //Select Year
         WebElement year = driver.findElement(By.xpath("(//select[@data-testid='year-datepicker'])[2]"));
         Select selectYear = new Select(year);
         selectYear.selectByVisibleText("2033"); 
         System.out.println("year is selected");
         
    }
    }
