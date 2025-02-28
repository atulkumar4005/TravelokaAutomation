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
    }
