package com.ecse428.cucumber;

import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;


public class StepDefinitions {

    private WebDriver driver;
    private final String PATH_TO_CHROME_DRIVER = "D:/UbuntuShared/chromedriver/chromedriver.exe";
    private final String EMAIL_URL = "https://accounts.google.com/signin/v2/sl/pwd?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin&cid=1&navigationDirection=forward";
    private final String EMAIL_ADDRESS = "dreamteamlite@gmail.com";
    private final String EMAIL_PASSWORD = "Spaget1!";
    private final String RECIPIENT_TEXT_BOX = "to";
    private final String INSERT_PHOTO_BTN = ":1a5";
    private final String SEND_BTN = ":185";
    private final String ATTACH_FILE_BTN = "//div[contains(@aria-label,'Attach files')]";
    private final String ATTACHMENT_BTN = "//div[contains(text(),'As Attachment')]";
    private final String COMPOSE_BTN = "//div[contains(text(),'Compose')]";


    @Given("^I am on a Gmail page$")
    public void givenOnGmailPage() throws Throwable{
        setupSeleniumWebDrivers();
        goTo(EMAIL_URL);
    }

    @And("^I am logged in$")
    public void iAmLoggedIn() throws Throwable{
        driver.findElement(By.id("identifierId")).sendKeys(EMAIL_ADDRESS);
        driver.findElement(By.id("identifierNext")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.name("password")));
        driver.findElement(By.name("password")).sendKeys(EMAIL_PASSWORD);
        driver.findElement(By.id("passwordNext")).click();
        // System.out.println("Checking to see if logged in by attempting to find Compose button...");
        // WebElement btn = (new WebDriverWait(driver, 10))
        //     .until(ExpectedConditions.presenceOfElementLocated(By.xpath(COMPOSE_BTN)));
        // System.out.println("Found!");
    }

    @When("^I click on the Compose button")
    public void iClickComposeButton() throws Throwable{
        System.out.println("Checking to see if logged in by attempting to find Compose button...");
        WebElement btn = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath(COMPOSE_BTN)));
        System.out.println("Found!");
        btn.click();
        System.out.println("Clicking Compose button");
    }    

    @And("^I specify a valid email address$")
    public void iSpecifyValidEmail() throws Throwable{
        System.out.println("Attempting to find recipient textbox...");
        WebElement recipient = driver.findElement(By.name(RECIPIENT_TEXT_BOX));
        System.out.println("Found!");
        recipient.clear();
        String email = "ansldfjasdjfasdlf"; //TODO: Need to take random email from bank of valid emails
        recipient.sendKeys(email);
    }

    @And("^I click on the Attach File button$")
    public void iClickAttachFile() throws Throwable{
        System.out.println("Attempting to find Attach File button...");
        WebElement btn = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath(ATTACH_FILE_BTN)));
        System.out.println("Found!");
        btn.sendKeys("C:\\Users\\Evan\\Downloads\\Spicy.PNG");

//        btn.click();
        System.out.println("Clicking Attach File button");
    }

    @And("^I select the desired image")
    public void iSelectValidImage() throws Throwable{

    }

    @And("^I click the Send button")
    public void iClickSend() throws Throwable{
        System.out.println("Attempting to find Send button...");
        WebElement btn = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.elementToBeClickable(By.id(SEND_BTN)));
        System.out.println("Found!");
        btn.click();
        System.out.println("Clicking Send button");
    }

    @And("^I click on the Insert Photo button")
    public void iClickInsertPhoto() throws Throwable{
        System.out.println("Attempting to find Insert Photo button...");
        WebElement btn = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.elementToBeClickable(By.id(INSERT_PHOTO_BTN)));
        System.out.println("Found!");
        btn.click();
        System.out.println("Clicking Insert Photo button");
    }

    @And("^I click the As Attachment button")
    public void iClickAsAttachment() throws Throwable{
        System.out.println("Attempting to find As Attachment button...");
        WebElement btn = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath(ATTACHMENT_BTN)));
        System.out.println("Found!");
        btn.click();
        System.out.println("Clicking As Attachment button");
    }

    @And("^I specify an invalid email address")
    public void iSpecifyInvalidEmail() throws Throwable{
        System.out.println("Attempting to find recipient textbox...");
        WebElement recipient = driver.findElement(By.id(RECIPIENT_TEXT_BOX));
        System.out.println("Found!");
        recipient.clear();
        String email = ""; //TODO: Need to take random email from bank of invalid emails
        recipient.sendKeys(email);
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
