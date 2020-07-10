package com.Cucumber.AutomationPractice;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class TestOutput {

    /*public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("C:\\Users\\mahesh.thuma\\Music\\Cucumber-Selenium-master\\Mani-automation\\src\\main\\java\\com\\Cucumber\\AutomationPractice\\outputfiles\\car_output.txt"));
        input.useDelimiter(",|\n");

        Vehicle[] vehicles = new Vehicle[0];
        while(input.hasNext()) {
            String registration = input.next();
            String make = input.next();
            String model = input.next();
            String colour = input.next();
            String year = input.next();

            Vehicle newVehicle = new Vehicle(registration, make, model, colour, year);
            vehicles = addProduct(vehicles, newVehicle);
        }

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
            System.out.println(vehicles.length);
            System.out.println("RegNum--->>"+vehicle.registrationNumber);
        }
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

        private static NumberFormat formatter = new DecimalFormat("#0.00");

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
    }*/

    public void vehicleInfo() throws FileNotFoundException {
        Scanner input = new Scanner(new File("C:\\Users\\mahesh.thuma\\Music\\Cucumber-Selenium-master\\Mani-automation\\src\\main\\java\\com\\Cucumber\\AutomationPractice\\outputfiles\\car_output.txt"));
        input.useDelimiter(",|\n");

        Vehicle[] vehicles = new Vehicle[0];
        while(input.hasNext()) {
            String registration = input.next();
            String make = input.next();
            String model = input.next();
            String colour = input.next();
            String year = input.next();

            Vehicle newVehicle = new Vehicle(registration, make, model, colour, year);
            vehicles = addProduct(vehicles, newVehicle);
        }

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
            System.out.println(vehicles.length);
            System.out.println("RegNum--->>"+vehicle.registrationNumber);
        }
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

        private static NumberFormat formatter = new DecimalFormat("#0.00");

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
