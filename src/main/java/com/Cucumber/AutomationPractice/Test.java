package com.Cucumber.AutomationPractice;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String args[]) throws IOException {
        String mydata = "some string with 'the data i want' inside";
        File file = new File("C:\\Users\\mahesh.thuma\\Music\\Cucumber-Selenium-master\\Mani-automation\\src\\main\\java\\com\\Cucumber\\AutomationPractice\\inputfiles\\car_input.txt");
        if (file.isFile() && file.getName().endsWith(".txt")) {
            String content = FileUtils.readFileToString(file);
            Pattern pattern = Pattern.compile("^(?=.{1,7})(([a-zA-Z]?){1,3}(\\d){1,3}(\\s)([a-zA-Z]?){1,3})$");
            Matcher matcher = pattern.matcher("BW57 BOW");
            if (matcher.find())
            {
                System.out.println(matcher.group(1));
            }

        }

    }
}
