package com.ecse428.cucumber;

import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;


public class StepDefinitions {

    private WebDriver driver;
    private final String PATH_TO_CHROME_DRIVER = "D:/UbuntuShared/chromedriver";
    private final String EMAIL_URL = "https://mail.google.com/mail/u/0/#inbox";
//    private final String EMAIL_SUBJECT = "Monoprice 115365 Monoprice Select Mini 3D Printer";
//    private final String DELETE_BTN_NAME = "submit.delete.C3NLW69582M4B4";
//    private final String CART_URL = "https://www.amazon.ca/gp/cart/view.html/ref=nav_cart";
//    private final String ADD_TO_CART_BTN = "add-to-cart-button";
//    private final String ACTIVE_CART = "sc-active-cart";
//    private final String CHECKOUT_BTN = "sc-proceed-to-checkout";


    @Given("^I am on a Gmail page$")
    public void givenOnGmailPage() throws Throwable{
        setupSeleniumWebDrivers();
        goTo(EMAIL_URL);
    }

    @And("^I am logged in$")
    public void iAmLoggedIn() throws Throwable{
        System.out.println("Checking to see if logged in by attempting to find Compose button...");
        WebElement btn = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Compose')]")));
        System.out.println("Found!");
    }

    @When("^I click on the Compose button")
    public void iClickComposeButton() throws Throwable{
        System.out.println("Checking to see if logged in by attempting to find Compose button...");
        WebElement btn = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Compose')]")));
        System.out.println("Found!");
        btn.click();
        System.out.println("Clicking Compose button");
    }    

    private void setupSeleniumWebDrivers() throws MalformedURLException {
        if (driver == null) {
            System.out.println("Setting up Selenium ChromeDriver...");
            System.setProperty("webdriver.chrome.driver", PATH_TO_CHROME_DRIVER);
            driver = new ChromeDriver();
            System.out.print("Done!\n");
        }
    }

    private void goTo(String url) {
        if (driver != null) {
            System.out.println("Going to " + url);
            driver.get(url);
        }
    }

}
