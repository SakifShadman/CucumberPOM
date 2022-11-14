package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountPage {

    private WebDriver driver;

    private By accountSections = By.cssSelector("div#center_column span");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getAccountPageTitle() {
        return driver.getTitle();
    }

    public int getAccountSectionCount() {
        return driver.findElements(accountSections).size();
    }

    public List<String> getAccountSectionList() {
        List<WebElement> accountHeaderList = driver.findElements(accountSections);
        List<String> accountList = new ArrayList<>();

        for (WebElement element : accountHeaderList) {
            String text = element.getText();
            System.out.println(text);
            accountList.add(text);
        }

        return accountList;
    }
}
