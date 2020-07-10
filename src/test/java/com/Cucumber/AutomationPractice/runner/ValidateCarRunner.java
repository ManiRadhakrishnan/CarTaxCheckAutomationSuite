package com.Cucumber.AutomationPractice.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
features= {"src\\test\\feature\\ValidateCarDetails.feature"},
glue = {"com.Cucumber.AutomationPractice.stepdefinition"},
monochrome = true,
dryRun=false,
plugin = {"pretty", "html:target/cucumber"})
//tags="@Sanity")

public class ValidateCarRunner {

}
