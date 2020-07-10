package com.Cucumber.AutomationPractice;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadInput {

    public static void main(String args[]) throws IOException {

        File folder = new File("C:\\Users\\mahesh.thuma\\Music\\Cucumber-Selenium-master\\Mani-automation\\src\\main\\java\\com\\Cucumber\\AutomationPractice\\inputfiles");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String content = FileUtils.readFileToString(file);
                getRegNums(content);
            }
        }
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
}
