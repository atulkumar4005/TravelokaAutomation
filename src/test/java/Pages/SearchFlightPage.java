package Pages;
import Utils.capchaHandler;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.capchaHandler;

import java.time.Duration;
import java.util.List;

public class SearchFlightPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;

    private By fromInputField = By.xpath("//input[@placeholder='Origin']");
    private By toInputField = By.xpath("//input[@placeholder='Destination']");
    private By searchButton = By.xpath("//div[@class='css-901oao r-1awozwy r-jwli3a r-6koalj r-61z16t']//*[name()='svg']");

    private By dropdownOptionsSingapore = By.xpath("//span[contains(text(),'(All Airports)')]");
    private By dropdownOptionsKualaLumpur = By.xpath("//div[@data-testid='cell-item-container-Kuala Lumpur']//div[@class='css-901oao r-cwxd7f r-a5wbuh r-1b43r93 r-b88u0q r-rjixqe r-fdjqy7']");

    public SearchFlightPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
    }

    public void enterFromLocation(String fromLocation) throws InterruptedException {
        closeOverlayIfPresent();
       capchaHandler.waitForCaptcha();

        WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(fromInputField));
        clickElement(fromInput);
        clearInput(fromInput);
        fromInput.sendKeys(fromLocation);
        Thread.sleep(2000); // 2-second wait
        WebElement dropdownOption = wait.until(ExpectedConditions.presenceOfElementLocated(dropdownOptionsSingapore));
        dropdownOption.click();

        
        System.out.println("Selected From Location: " + fromLocation);
    }



    public void enterToLocation(String toLocation) {
        closeOverlayIfPresent();

        WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(toInputField));
        clickElement(toInput);
        clearInput(toInput);
        toInput.sendKeys(toLocation);

        WebElement dropdownOption1 = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownOptionsKualaLumpur));

        clickElement(dropdownOption1);
        System.out.println("Selected To Location: " + toLocation);
    }



    private void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Element click intercepted, trying JS click...");
            js.executeScript("arguments[0].click();", element);
        } catch (TimeoutException e) {
            System.out.println("Timeout while waiting for element: " + element);
        } catch (Exception e) {
            System.out.println("Error clicking element: " + e.getMessage());
        }
    }


    private void clearInput(WebElement element) {
        element.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
    }

    private void closeOverlayIfPresent() {
        try {
            WebElement overlay = driver.findElement(By.xpath("//div[contains(@class, 'overlay-class')]"));
            if (overlay.isDisplayed()) {
                js.executeScript("arguments[0].style.display='none';", overlay);
            }
        } catch (NoSuchElementException ignored) {
        }
    }
    public void clickSearchButton() {
        try {
            closeOverlayIfPresent(); 

            WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", searchBtn);
            Thread.sleep(1000); 

            if (searchBtn.isDisplayed() && searchBtn.isEnabled()) {
                try {
                    searchBtn.click(); // Normal click
                    System.out.println("Clicked search button normally.");
                } catch (ElementClickInterceptedException e) {
                    System.out.println("Normal click failed, trying JavaScript click...");
                    js.executeScript("arguments[0].dispatchEvent(new MouseEvent('click', {bubbles: true}));", searchBtn);
                }
            } else {
                System.out.println("Search button is not clickable! Check locator or page state.");
            }
        } catch (TimeoutException e) {
            System.out.println("Timeout: Search button not found within 15 seconds.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted while waiting to click Search button.");
        } catch (JavascriptException e) {
            System.out.println("JavaScript execution failed: " + e.getMessage());
        }
    }


}
