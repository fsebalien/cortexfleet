package steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class Base {
    public static WebDriver driver;

    public static void beforeTest(String browser) {
        try {
            switch (browser) {
                case "Chrome":
                    System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver.exe");
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    break;
                case "Firefox":
                    System.setProperty("webdriver.gecko.driver", "src/test/java/resources/geckodriver.exe");
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;
                case "IExplorer":
                    System.setProperty("webdriver.ie.driver", "src/test/java/resources/IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    driver.manage().window().maximize();
                    break;
            }
        } catch (Exception ignored) {
        }
    }
    public static void afterTest() {
        driver.quit();
    }

    public static void CheckPageTitle(String value) throws Throwable {
        Assert.assertEquals(Base.driver.getTitle(), value);
    }

    public static void takeScreenShotForFailure() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        FileUtils.copyFile(source, new File("src/test/screenshots/" + String.valueOf(timestamp.getTime()) + ".png"));
    }


    @When("^Click the link \"([^\"]*)\"$")
    public void clickTheLink(String link) throws Throwable {
        driver.findElement(By.linkText(link)).click();
    }


    @Then("^Check the page \"([^\"]*)\"$")
    public void checkThePage(String url) throws Throwable {
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }
}
