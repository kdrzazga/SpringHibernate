package org.kd.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        dryRun = true,
        features = "src/test/resources/features",
        glue = "src/test/java/org/kd/stepdefs"
)

public class TestRunner {

}
