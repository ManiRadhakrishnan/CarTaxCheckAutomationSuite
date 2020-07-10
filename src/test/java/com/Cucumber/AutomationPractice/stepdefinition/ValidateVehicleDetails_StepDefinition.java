package com.Cucumber.AutomationPractice.stepdefinition;

import com.Cucumber.AutomationPractice.pageobjects.VehicleTaxHomePage;
import com.Cucumber.AutomationPractice.testbase.Testbase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;

import java.io.IOException;

public class ValidateVehicleDetails_StepDefinition extends Testbase {


	Testbase testbase;
	VehicleTaxHomePage vehicleTaxCheck = new VehicleTaxHomePage();
	private static final Logger log = Logger.getLogger(ValidateVehicleDetails_StepDefinition.class.getName());

	public ValidateVehicleDetails_StepDefinition() throws IOException {
		super();
	}

	@Given("^I extracted vehicle registration numbers from input text file$")
	public void setupBrowser() throws Throwable {
		initialization();
	}

	@When("^I search a registration number on https://cartaxcheck.co.uk/$")
	public void searchVehicleOnUI() throws Throwable {
		vehicleTaxCheck.searchVehicleDetails();
	}


	@Then("^details on the website should match output text file$")
	public void validate() throws Throwable {
		Assert.assertTrue(vehicleTaxCheck.validateVehicleDetails());
	}

	@After
	public void teardown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

}
