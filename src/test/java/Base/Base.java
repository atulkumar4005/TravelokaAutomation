package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class Base {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeSuite
    public void setupSuite() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @BeforeClass  
    public void setup() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        if (browser.equals("chrome")) {
            driver.set(new ChromeDriver());  
        } else if (browser.equals("edge")) {
            driver.set(new EdgeDriver());
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();  
        getDriver().get("https://www.traveloka.com/en-en");
    }

    @AfterClass  // ✅ Browser sirf ek baar close hoga
    public void teardown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    @AfterSuite
    public void cleanupSuite() {
        WebDriverManager.chromedriver().clearDriverCache();
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}
