package com.ecse428.cucumber;

import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static java.awt.event.KeyEvent.*;


public class StepDefinitions {

    boolean waitRobot = false;

    private WebDriver driver;
    private final String PATH_TO_CHROME_DRIVER = "D:/UbuntuShared/chromedriver/chromedriver.exe";
    private final String EMAIL_URL = "https://accounts.google.com/signin/v2/sl/pwd?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin&cid=1&navigationDirection=forward";
    private final String EMAIL_ADDRESS = "dreamteamlite@gmail.com";
    private final String EMAIL_PASSWORD = "Spaget1!";
    private final String RECIPIENT_TEXT_BOX = "to";
    private final String SUBJECT_TEXT_BOX = "subjectbox";
    private final String INSERT_PHOTO_BTN = ":1a5";
    private final String SEND_BTN = "//div[contains(text(),'Send')]";
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

    @And("^I specify a valid email address as \"([^\"]*)\"$")
    public void iSpecifyValidEmail(String arg1) throws Throwable{
        System.out.println("Attempting to find recipient textbox...");
        WebElement recipient = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.name(RECIPIENT_TEXT_BOX)));
        WebElement subject = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.name(SUBJECT_TEXT_BOX)));
        System.out.println("Found!");
        recipient.clear();
        subject.clear();
        String subjectString = "Subject";
        String email = "dreamteamlite@gmail.com"; //TODO: Need to take random email from bank of valid emails
        recipient.sendKeys(arg1);
        subject.sendKeys(subjectString);
    }

    @And("^I click on the Attach File button$")
    public void iClickAttachFile() throws Throwable{
        System.out.println("Attempting to find Attach File button...");
        WebElement btn = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath(ATTACH_FILE_BTN)));
        System.out.println("Found!");

        btn.click();

        System.out.println("Clicking Attach File button\n");
    }

    @And("^I select the desired image as \"([^\"]*)\"$")
    public void iSelectValidImage(String arg1) throws Throwable{
        System.out.println("Attempting to type file path...");
        type(arg1);


        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        System.out.println("Done!");
        waitRobot = true;
    }

    @And("^I click the Send button")
    public void iClickSend() throws Throwable{
        while(!waitRobot){
            System.out.println("wtf");
        }
        Thread.sleep(5000);
        System.out.print("Attempting to find Send button...");
        WebElement btn = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath(SEND_BTN)));
        System.out.println("Found!\n");
        btn.click();
        System.out.println("Clicking Send button");
        Thread.sleep(3000);
        driver.quit();
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



    public void type(CharSequence characters) throws AWTException{
        int length = characters.length();
        for (int i = 0; i < length; i++) {
            char character = characters.charAt(i);
            type(character);
        }
    }


    public void type(char character) throws AWTException{
        switch (character) {
            case 'a': doType(VK_A); break;
            case 'b': doType(VK_B); break;
            case 'c': doType(VK_C); break;
            case 'd': doType(VK_D); break;
            case 'e': doType(VK_E); break;
            case 'f': doType(VK_F); break;
            case 'g': doType(VK_G); break;
            case 'h': doType(VK_H); break;
            case 'i': doType(VK_I); break;
            case 'j': doType(VK_J); break;
            case 'k': doType(VK_K); break;
            case 'l': doType(VK_L); break;
            case 'm': doType(VK_M); break;
            case 'n': doType(VK_N); break;
            case 'o': doType(VK_O); break;
            case 'p': doType(VK_P); break;
            case 'q': doType(VK_Q); break;
            case 'r': doType(VK_R); break;
            case 's': doType(VK_S); break;
            case 't': doType(VK_T); break;
            case 'u': doType(VK_U); break;
            case 'v': doType(VK_V); break;
            case 'w': doType(VK_W); break;
            case 'x': doType(VK_X); break;
            case 'y': doType(VK_Y); break;
            case 'z': doType(VK_Z); break;
            case 'A': doType(VK_SHIFT, VK_A); break;
            case 'B': doType(VK_SHIFT, VK_B); break;
            case 'C': doType(VK_SHIFT, VK_C); break;
            case 'D': doType(VK_SHIFT, VK_D); break;
            case 'E': doType(VK_SHIFT, VK_E); break;
            case 'F': doType(VK_SHIFT, VK_F); break;
            case 'G': doType(VK_SHIFT, VK_G); break;
            case 'H': doType(VK_SHIFT, VK_H); break;
            case 'I': doType(VK_SHIFT, VK_I); break;
            case 'J': doType(VK_SHIFT, VK_J); break;
            case 'K': doType(VK_SHIFT, VK_K); break;
            case 'L': doType(VK_SHIFT, VK_L); break;
            case 'M': doType(VK_SHIFT, VK_M); break;
            case 'N': doType(VK_SHIFT, VK_N); break;
            case 'O': doType(VK_SHIFT, VK_O); break;
            case 'P': doType(VK_SHIFT, VK_P); break;
            case 'Q': doType(VK_SHIFT, VK_Q); break;
            case 'R': doType(VK_SHIFT, VK_R); break;
            case 'S': doType(VK_SHIFT, VK_S); break;
            case 'T': doType(VK_SHIFT, VK_T); break;
            case 'U': doType(VK_SHIFT, VK_U); break;
            case 'V': doType(VK_SHIFT, VK_V); break;
            case 'W': doType(VK_SHIFT, VK_W); break;
            case 'X': doType(VK_SHIFT, VK_X); break;
            case 'Y': doType(VK_SHIFT, VK_Y); break;
            case 'Z': doType(VK_SHIFT, VK_Z); break;
            case '`': doType(VK_BACK_QUOTE); break;
            case '0': doType(VK_0); break;
            case '1': doType(VK_1); break;
            case '2': doType(VK_2); break;
            case '3': doType(VK_3); break;
            case '4': doType(VK_4); break;
            case '5': doType(VK_5); break;
            case '6': doType(VK_6); break;
            case '7': doType(VK_7); break;
            case '8': doType(VK_8); break;
            case '9': doType(VK_9); break;
            case '-': doType(VK_MINUS); break;
            case '=': doType(VK_EQUALS); break;
            case '~': doType(VK_SHIFT, VK_BACK_QUOTE); break;
            case '!': doType(VK_EXCLAMATION_MARK); break;
            case '@': doType(VK_AT); break;
            case '#': doType(VK_NUMBER_SIGN); break;
            case '$': doType(VK_DOLLAR); break;
            case '%': doType(VK_SHIFT, VK_5); break;
            case '^': doType(VK_CIRCUMFLEX); break;
            case '&': doType(VK_AMPERSAND); break;
            case '*': doType(VK_ASTERISK); break;
            case '(': doType(VK_LEFT_PARENTHESIS); break;
            case ')': doType(VK_RIGHT_PARENTHESIS); break;
            case '_': doType(VK_UNDERSCORE); break;
            case '+': doType(VK_PLUS); break;
            case '\t': doType(VK_TAB); break;
            case '\n': doType(VK_ENTER); break;
            case '[': doType(VK_OPEN_BRACKET); break;
            case ']': doType(VK_CLOSE_BRACKET); break;
            case '\\': doType(VK_BACK_SLASH); break;
            case '{': doType(VK_SHIFT, VK_OPEN_BRACKET); break;
            case '}': doType(VK_SHIFT, VK_CLOSE_BRACKET); break;
            case '|': doType(VK_SHIFT, VK_BACK_SLASH); break;
            case ';': doType(VK_SEMICOLON); break;
            case ':': doType(VK_SHIFT, VK_SEMICOLON); break;
            case '\'': doType(VK_QUOTE); break;
            case '"': doType(VK_QUOTEDBL); break;
            case ',': doType(VK_COMMA); break;
            case '<': doType(VK_SHIFT, VK_COMMA); break;
            case '.': doType(VK_PERIOD); break;
            case '>': doType(VK_SHIFT, VK_PERIOD); break;
            case '/': doType(VK_SLASH); break;
            case '?': doType(VK_SHIFT, VK_SLASH); break;
            case ' ': doType(VK_SPACE); break;
            default:
                throw new IllegalArgumentException("Cannot type character " + character);
        }
    }


    private void doType(int... keyCodes) throws AWTException{
        doType(keyCodes, 0, keyCodes.length);
    }

    private void doType(int[] keyCodes, int offset, int length) throws AWTException{
        if (length == 0) {
            return;
        }

        Robot robot = new Robot();

        robot.keyPress(keyCodes[offset]);
        doType(keyCodes, offset + 1, length - 1);
        robot.keyRelease(keyCodes[offset]);
    }



}
