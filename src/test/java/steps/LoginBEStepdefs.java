package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.LoginRequestBody;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static util.Endpoints.API_PATH;

public class LoginBEStepdefs {
    public Response response;
    public static String token;

    LoginRequestBody lrb = new LoginRequestBody();

    @Given("^I perform authentication operation for \"([^\"]*)\" with body$")
    public void ıPerformAuthenticationOperationForWithBody(String path, DataTable dt) throws Throwable {
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);

        lrb.setEmail(list.get(0).get("email"));
        lrb.setPassword(MainServices.dataConversion(list.get(0).get("password")));

        response =
                given().
                        contentType(ContentType.JSON).
                        with().
                        body(lrb).
                        when().
                        post(API_PATH + "/auth");
        token = response.jsonPath().getString("token");
        MainServices.response=response;
        System.out.println(response.asString());
    }
}
