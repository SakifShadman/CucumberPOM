package com.qa.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    public WebDriver driver;

    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * This method is used to initialize the threadLocal driver on the basis of given browser
     * @param browser chrome
     * @return This will return driverThreadLocal
     */
    public WebDriver init_Driver(String browser) {
        System.out.println("Browser value is: " + browser);

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driverThreadLocal.set(new ChromeDriver());
        }
        else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driverThreadLocal.set(new FirefoxDriver());
        }
        else if (browser.equals("safari")) {
            WebDriverManager.safaridriver().setup();
            driverThreadLocal.set(new SafariDriver());
        }
        else {
            System.out.println("Please pass the correct browser value: " + browser);
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();

        return getDriver();
    }

    /**
     * This is used to get the driver with ThreadLocal
     * @return driver
     */
    public static synchronized WebDriver getDriver() {
        return driverThreadLocal.get();
    }
}
