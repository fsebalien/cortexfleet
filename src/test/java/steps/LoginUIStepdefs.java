package steps;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginUIStepdefs {
    @Before
    public void beforeTest() {
        Base.beforeTest("Chrome");
    }

    @Given("^Enter the page \"([^\"]*)\"$")
    public void enterThePage(String url) throws Throwable {
        Base.driver.get(url);
        Assert.assertEquals(Base.driver.getTitle(), "Cortex Fleet - Operating system for internet connected screens");
        Thread.sleep(2000);
    }

    @After
    public void afterTest(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                Base.takeScreenShotForFailure();
                System.out.println("Screenshot taken");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
        Base.afterTest();
    }

    @When("^Login with \"([^\"]*)\" username and \"([^\"]*)\" password$")
    public void loginWithUsernameAndPassword(String username, String password) throws Throwable {
        String loginURL = Base.driver.getCurrentUrl();
        Assert.assertEquals(loginURL,"https://fleet.cortexpowered.com/login");
        Base.driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(username);
        Base.driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
        Base.driver.findElement(By.cssSelector(".btn-flat.btn.btn-primary")).click();
        Thread.sleep(2000);
    }

    @Then("^See logged in successfully$")
    public void seeLoggedInSuccessfully() throws InterruptedException {
        Thread.sleep(2000);
        Base.driver.findElement(By.cssSelector(".pull-left.info")).isDisplayed();
    }

    @Then("^See invalid credentials message$")
    public void seeInvalidCredentialsMessage() throws InterruptedException {
        //String InvalidCredentialMsg = Base.driver.findElement(By.cssSelector(".message-message")).getText();
        String InvalidCredentialMsg = Base.driver.findElement(By.xpath("//*[@id=\"cortex-fleet\"]/div/div[1]/div[2]/div/div/div/div[1]/span")).getText();
        Assert.assertEquals(InvalidCredentialMsg,"Invalid email or password.");
        Thread.sleep(3000);
    }

    @When("^Enter email address of the forgotten account \"([^\"]*)\"$")
    public void enterEmailAddressOfTheForgottenAccount(String email) throws Throwable {
        Base.driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);
        Base.driver.findElement(By.cssSelector(".btn-flat.btn.btn-primary")).isDisplayed();
        Base.driver.findElement(By.cssSelector(".btn-flat.btn.btn-primary")).click();
        Thread.sleep(3000);
    }

    @Then("^See email is sent$")
    public void seeEmailIsSent() {
        String InvalidEmailMsg = Base.driver.findElement(By.cssSelector("#cortex-fleet > div > div > div.login-box > div > p")).getText();
        Assert.assertEquals(InvalidEmailMsg,"Password reset email is sent. Please check your inbox.");
    }
}
