package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CucumberOptions(features = {"src/test/java/features"},
        glue = {"steps"},
        plugin = {"json:target/cucumber.json","html:target/site/cucumber-pretty"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
