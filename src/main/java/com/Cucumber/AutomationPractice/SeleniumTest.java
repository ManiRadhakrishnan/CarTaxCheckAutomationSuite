package com.Cucumber.AutomationPractice;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeleniumTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        ArrayList<String> nums = test();
        Vehicle[] vehicles = vehicleInfo();

        for(String s :nums){
            runTest(s, vehicles);
        }

    }

    public static void runTest(String s, Vehicle[] vehicles) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "src\\main\\java\\com\\Cucumber\\AutomationPractice\\config\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://cartaxcheck.co.uk/");
        driver.findElement(By.id("vrm-input")).clear();
        driver.findElement(By.id("vrm-input")).sendKeys(s);
        driver.findElement(By.xpath("//button[text()='Free Car Check']")).click();
        Thread.sleep(2000);
        String regFromUI = driver.findElement(By.xpath("//dt[text()='Registration']/ancestor::dl/dd")).getText().trim();
        String makeFromUI = driver.findElement(By.xpath("//dt[text()='Make']/ancestor::dl/dd")).getText().trim();
        String modelFromUI = driver.findElement(By.xpath("//dt[text()='Model']/ancestor::dl/dd")).getText().trim();
        String colourFromUI = driver.findElement(By.xpath("//dt[text()='Colour']/ancestor::dl/dd")).getText().trim();
        String yearFRomUI = driver.findElement(By.xpath("//dt[text()='Year']/ancestor::dl/dd")).getText().trim();

        for (Vehicle vehicle : vehicles) {
            if(regFromUI.contentEquals(vehicle.registrationNumber)){
                System.out.println("<--Inside matching loop-->");
                System.out.println(vehicle.toString());
                System.out.println("Year from UI-> "+yearFRomUI+"X");
                System.out.println("Year from UI-> "+vehicle.make+"X");
                System.out.println("-"+yearFRomUI+""+vehicle.year+"-");
                Assert.assertEquals("Value not matching for"+vehicle.registrationNumber,vehicle.registrationNumber, regFromUI);
                Assert.assertEquals("Value not matching for"+vehicle.make,vehicle.make, makeFromUI);
                Assert.assertEquals("Value not matching for"+vehicle.model, vehicle.model, modelFromUI);
                Assert.assertEquals("Value not matching for"+vehicle.colour, vehicle.colour, colourFromUI);
                Assert.assertEquals("Value not matching for"+vehicle.year, vehicle.year, yearFRomUI);

                break;
            }
        }

        driver.close();
    }

    public String getData(WebDriver driver, String xpath) {
        return driver.findElement(By.xpath("//dt[text()='${xpath}']/ancestor::dl/dd")).getText();
    }

    public static ArrayList<String> test() throws IOException {
        ArrayList<String> s = new ArrayList<String>();
        File folder = new File("C:\\Users\\mahesh.thuma\\Music\\Cucumber-Selenium-master\\Mani-automation\\src\\main\\java\\com\\Cucumber\\AutomationPractice\\inputfiles");
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
        Scanner input = new Scanner(new File("C:\\Users\\mahesh.thuma\\Music\\Cucumber-Selenium-master\\Mani-automation\\src\\main\\java\\com\\Cucumber\\AutomationPractice\\outputfiles\\car_output.txt"));
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

        /*for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
            System.out.println(vehicles.length);
            System.out.println("RegNum--->>" + vehicle.registrationNumber);
        }*/
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