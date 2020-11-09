package steps;

import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

public class MainServices {
    public static Response response;

    @Then( "^I should see (\\d+) status code$" )
    public void iShouldSeeStatusCode(int code) {
        Assert.assertEquals(response.statusCode(), code);
    }

    @Then("^Expected to see \"([^\"]*)\" field of data are not null$")
    public void expectedToSeeFieldOfDataAreNotNull(String label) throws Throwable {
        Assert.assertNotNull(response.jsonPath().getString(label));
    }

    @Then("^Expected to see \"([^\"]*)\" message$")
    public void expectedToSeeMessage(String message) throws Throwable {
        String actual = response.jsonPath().getString("message");
        Assert.assertTrue(actual.contains(message));
    }

    public static String dataConversion(String s) {
        if (s.equalsIgnoreCase("null")) {
            return null;
        } else if (s.equalsIgnoreCase("whiteSpace")) {
            return "        ";
        } else
            return s;
    }
}
