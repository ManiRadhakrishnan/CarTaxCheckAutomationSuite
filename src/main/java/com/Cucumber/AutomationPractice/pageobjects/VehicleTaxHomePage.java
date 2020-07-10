package com.Cucumber.AutomationPractice.pageobjects;

import com.Cucumber.AutomationPractice.testbase.Testbase;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleTaxHomePage extends Testbase {

	private final Logger log = Logger.getLogger(VehicleTaxHomePage.class);

	@FindBy(id = "vrm-input")
	WebElement vehicleRegistrationTextField;

	@FindBy(xpath = "//button[text()='Free Car Check']")
	WebElement freeCarCheckButton;

	@FindBy(xpath = "//dt[text()='Registration']/ancestor::dl/dd")
	WebElement registration;

	@FindBy(xpath = "//dt[text()='Make']/ancestor::dl/dd")
	WebElement make;

	@FindBy(xpath = "//dt[text()='Model']/ancestor::dl/dd")
	WebElement model;

	@FindBy(xpath = "//dt[text()='Colour']/ancestor::dl/dd")
	WebElement colour;

	@FindBy(xpath = "//dt[text()='Year']/ancestor::dl/dd")
	WebElement year;

	public VehicleTaxHomePage() throws IOException {
		PageFactory.initElements(driver, this);
	}


	Vehicle[] vehicles = vehicleInfo();
	Boolean searchComplete = false;

	public void searchVehicleDetails() throws IOException, InterruptedException {
		ArrayList<String> nums = getRegistrationNumbers();
		for (String s : nums) {
			runTest(s, vehicles);
		}

	}

	public boolean validateVehicleDetails() {
	return searchComplete;

	}

	public void runTest(String s, Vehicle[] vehicles) throws InterruptedException {
		//vehicleRegistrationTextField.clear();
		PageFactory.initElements(driver, this);
		vehicleRegistrationTextField.sendKeys(s);
		freeCarCheckButton.click();

		String regFromUI = registration.getText().trim();
		String makeFromUI = make.getText().trim();
		String modelFromUI = model.getText().trim();
		String colourFromUI = colour.getText().trim();
		String yearFRomUI = year.getText().trim();

		for (Vehicle vehicle : vehicles) {
			if (regFromUI.contentEquals(vehicle.registrationNumber)) {
				System.out.println("<--Inside matching loop-->");
				Assert.assertEquals("Value not matching for" + vehicle.registrationNumber, vehicle.registrationNumber, regFromUI);
				Assert.assertEquals("Value not matching for" + vehicle.make, vehicle.make, makeFromUI);
				Assert.assertEquals("Value not matching for" + vehicle.model, vehicle.model, modelFromUI);
				Assert.assertEquals("Value not matching for" + vehicle.colour, vehicle.colour, colourFromUI);
				Assert.assertEquals("Value not matching for" + vehicle.year, vehicle.year.trim(), yearFRomUI);
				searchComplete = true;
				break;
			}
		}

		driver.get("https://cartaxcheck.co.uk/");
	}

	public static ArrayList<String> getRegistrationNumbers() throws IOException {
		ArrayList<String> s = new ArrayList<String>();
		File folder = new File("src\\main\\java\\com\\Cucumber\\AutomationPractice\\inputfiles");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			if (file.isFile() && file.getName().endsWith(".txt")) {
				String content = FileUtils.readFileToString(file);
				s = getRegNums(content);
			}
		}
		return s;
	}

	static ArrayList<String> getRegNums(String content) {
		ArrayList<String> nums = new ArrayList<String>();
		Pattern patter1 = Pattern.compile("(?=.{1,7})(([a-zA-Z]){1,3}(\\d){1,3}(\\s?)([a-zA-Z]){1,3})");

		Matcher m1 = patter1.matcher(content);
		System.out.println(m1.toString());
		while (m1.find()) {
			String s = m1.group(1);
			System.out.println(s);
			nums.add(s);
		}
		return nums;
	}

	static Vehicle[] vehicleInfo() throws FileNotFoundException {
		Scanner input = new Scanner(new File("src\\main\\java\\com\\Cucumber\\AutomationPractice\\outputfiles\\car_output.txt"));
		input.useDelimiter(",|\n");

		Vehicle[] vehicles = new Vehicle[0];
		while (input.hasNext()) {
			String registration = input.next();
			String make = input.next();
			String model = input.next();
			String colour = input.next();
			String year = input.next();

			Vehicle newVehicle = new Vehicle(registration, make, model, colour, year);
			vehicles = addProduct(vehicles, newVehicle);
		}

        return vehicles;
	}

	private static Vehicle[] addProduct(Vehicle[] vehicles, Vehicle vehicleToAdd) {
		Vehicle[] newVehicles = new Vehicle[vehicles.length + 1];
		System.arraycopy(vehicles, 0, newVehicles, 0, vehicles.length);
		newVehicles[newVehicles.length - 1] = vehicleToAdd;

		return newVehicles;
	}

	public static class Vehicle {
		protected String registrationNumber;
		protected String make;
		protected String model;
		protected String colour;
		protected String year;

		//private static NumberFormat formatter = new DecimalFormat("#0.00");

		public Vehicle(String r, String mk, String md, String c, String y) {
			registrationNumber = r;
			make = mk;
			model = md;
			colour = c;
			year = y;
		}

		@Override
		public String toString() {
			return String.format("registrationNumber: %s\r\nmake: %s\r\nmodel: %s\r\ncolour: %s\r\nyear: %s\r\n",
					registrationNumber, make, model, colour, year);
		}

	}
}
