package com.ecse428.cucumber;

import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.List;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;

import static java.awt.event.KeyEvent.*;


public class StepDefinitions {

    private boolean waitRobot = false;

    private WebDriver driver;
    private WebDriverWait driverWait;

    private final String RECIPIENT_TEXT_BOX = "to";
    private final String SUBJECT_TEXT_BOX = "subjectbox";
    private final String SENT_EMAIL_SUBJECT = "ImageEmail";

    /**
    * BACKGROUND SECTION
    * */

    @Given("^I am on a Gmail page$")
    public void givenOnGmailPage() throws Throwable{
        setupSeleniumWebDrivers();
        String EMAIL_URL = "https://accounts.google.com/signin/v2/sl/pwd?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin&cid=1&navigationDirection=forward";
        goTo(EMAIL_URL);
    }

    @And("^I am logged in$")
    public void iAmLoggedIn() throws Throwable{
        String EMAIL_ADDRESS = "dreamteamlite@gmail.com";
        driver.findElement(By.id("identifierId")).sendKeys(EMAIL_ADDRESS);
        driver.findElement(By.id("identifierNext")).click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        String EMAIL_PASSWORD = "Spaget1!";
        driver.findElement(By.name("password")).sendKeys(EMAIL_PASSWORD);
        driver.findElement(By.id("passwordNext")).click();
    }

    @When("^I click on the Compose button")
    public void iClickComposeButton() throws Throwable{
        System.out.println("Checking to see if logged in by attempting to find Compose button...");
        String COMPOSE_BTN = "//div[contains(text(),'Compose')]";
        WebElement btn = driverWait
            .until(ExpectedConditions.elementToBeClickable(By.xpath(COMPOSE_BTN)));
        System.out.println("Found!");
        btn.click();
        System.out.println("Clicking Compose button");
    }


    /**
     * E-MAIL / SUBJECT SPECIFICATION
     * */

    @And("^I specify a valid email address as \"([^\"]*)\" with subject \"([^\"]*)\"$")
    public void iSpecifyValidEmail(String validEmail, String subjectString) throws Throwable{
        System.out.println("Attempting to find recipient textbox...");
        WebElement recipient = driverWait
                .until(ExpectedConditions.elementToBeClickable(By.name(RECIPIENT_TEXT_BOX)));
        WebElement subject = driverWait
                .until(ExpectedConditions.elementToBeClickable(By.name(SUBJECT_TEXT_BOX)));
        System.out.println("Found!");
        recipient.clear();
        subject.clear();
        recipient.sendKeys(validEmail);
        subject.sendKeys(subjectString);
    }

    @And("^I specify an invalid email address as \"([^\"]*)\"$")
    public void iSpecifyInvalidEmail(String invalidEmail) throws Throwable{
        System.out.println("Attempting to find recipient textbox...");
        WebElement invalidRecipient = driverWait
                .until(ExpectedConditions.elementToBeClickable(By.name(RECIPIENT_TEXT_BOX)));
        WebElement subject = driverWait
                .until(ExpectedConditions.elementToBeClickable(By.name(SUBJECT_TEXT_BOX)));
        System.out.println("Found!");
        invalidRecipient.clear();
        subject.clear();
        invalidRecipient.sendKeys(invalidEmail);
        subject.sendKeys(SENT_EMAIL_SUBJECT);
    }

    /**
     * ATTACH FILE / INSERT PHOTO
     * */

    @And("^I click on the Attach File button$")
    public void iClickAttachFile() throws Throwable{
        System.out.println("Attempting to find Attach File button...");
        String ATTACH_FILE_BTN = "//div[contains(@aria-label,'Attach files')]";
        WebElement btn = driverWait
            .until(ExpectedConditions.elementToBeClickable(By.xpath(ATTACH_FILE_BTN)));
        System.out.println("Found!");

        btn.click();

        System.out.println("Clicking Attach File button");
    }

    @And("^I click on the Insert Photo button")
    public void iClickInsertPhoto() throws Throwable{
        System.out.println("Attempting to find Insert Photo button...");
        String INSERT_PHOTO_BTN = "//div[contains(@aria-label, 'Insert photo')]";
        WebElement btn = driverWait
                .until(ExpectedConditions.elementToBeClickable(By.xpath(INSERT_PHOTO_BTN)));
        System.out.println("Found!");
        btn.click();
        System.out.println("Clicking Insert Photo button");
    }

    @And("^I click the As Attachment button")
    public void iClickAsAttachment() throws Throwable{
        System.out.println("Attempting to find As Attachment button...");
        String AS_ATTACHMENT_BTN = "//div[contains(text(),'As attachment')]";
        List<WebElement> iframe_element = driver.findElements(By.tagName("iframe"));
//        int i = 0;
//        for(WebElement w : iframe_element){
//            System.out.println(i);
//            System.out.println(w.getAttribute("name"));
//            i++;
//        }

        WebElement lastIFrame = iframe_element.get(iframe_element.size() - 1);
        driver.switchTo().frame(lastIFrame);
        WebElement btn = driverWait
                .until(ExpectedConditions.elementToBeClickable(By.xpath(AS_ATTACHMENT_BTN)));
        System.out.println("Found!");
        btn.click();
        System.out.println("Clicking As Attachment button");
    }


    /**
     * IMAGE SELECTION
     */

    @And("^I select the desired image as \"([^\"]*)\"$")
    public void iSelectValidImage(String filePath) throws Throwable{
        System.out.println("Attempting to type file path...");
        type(filePath);
        System.out.println("Done!");
    }

    @And("^I select the desired large image as \"([^\"]*)\"$")
    public void iSelectLargeImage(String filePath) throws Throwable {
        System.out.println("Attempting to type file path...");
        type(filePath);
        System.out.println("Done!");
    }

    @Then("^a notification appears saying the large file is being sent as a Google Drive Link")
    public void largeImageNotification(){
        System.out.println("Waiting for file attachment message to disappear...");
        String LARGE_FILE_NOTIFICATION = "//span[contains(text(),'Attaching File')]";
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LARGE_FILE_NOTIFICATION)));
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LARGE_FILE_NOTIFICATION)));
        System.out.println("Done!");

    }

    @Then("^the Google Drive Link appears in email with name \"([^\"]*)\"$")
    public void googleDriveLinkAppears(String fileName){
        System.out.println("Checking for Drive link in email...");
        String driveLink = "//span[contains(text(),'" + fileName + "')]";
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(driveLink)));
        System.out.println("Found!");
    }

    @And("^I click the Select Files From Your Device button and a file path as \"([^\"]*)\"$")
    public void iClickSelectFilesFromYourDevice(String filePath) throws Throwable {
        System.out.println("Attempting to find Select Files From Your Device button...");
        String SELECT_FILES_DEVICE_BTN = "//div[contains(text(),'Select files from your device')]";
        WebElement btn = driverWait
                .until(ExpectedConditions.elementToBeClickable(By.xpath(SELECT_FILES_DEVICE_BTN)));
        System.out.println("Found!");
        btn.click();
        Thread.sleep(2000); // Clunky again, but not sure what else to do since we cant use selectors on Windows Explorer Dialog
        System.out.println("Attempting to type file path of desired file...");
        type(filePath);
        System.out.println("Done!");
    }


    /**
     * SENDING EMAIL / CHECKING FOR ERRORS / CHECKING FOR SENT EMAIL
     */

    @Then("^completed the upload of file named \"([^\"]*)\"$")
    public void fileUploadCompleted(String ariaLabel){
        System.out.println("Waiting on file upload to complete...");
        String xPathArg = "//div[contains(@aria-label, '" + ariaLabel + "')]";
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathArg)));
//        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPathArg)));
        System.out.println("File upload complete!");
    }

    @Then("a notification appears checking access permissions")
    public void accessPermissionsCheck(){

        List<WebElement> iframe_element = driver.findElements(By.tagName("iframe"));
        WebDriverWait accessDriverWait = new WebDriverWait(driver, 2);
        int i = 0;
        for(WebElement w : iframe_element){
            driver.switchTo().parentFrame();
            driver.switchTo().frame(w);
            System.out.println(i++);
            try {
                accessDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Share with 1 person:')]")));
                System.out.println("Attempting to find send file button in access window...");
                WebElement sendBtn = accessDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Send')]")));
                System.out.println("Clicking send file button in access window");
                sendBtn.click();

            } catch(NoSuchElementException e) {
                System.out.println("bruh");
                continue;
            }
            catch(Exception c) {
                System.out.println("bruh");
                continue;
            }

        }
        driver.switchTo().parentFrame();
    }

    @And("^I click the Send button")
    public void iClickSend() throws Throwable{
        while(!waitRobot); // TODO: Figure out why sometimes the Robot doesn't seem to type / doesn't wait for input
        System.out.println("Attempting to find Send button...");
        String SEND_BTN = "//div[contains(text(),'Send')]";
        WebElement btn = driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(SEND_BTN)));
        System.out.println("Found!");
        btn.click();
        System.out.println("Clicking Send button");
    }

    @Then("^the message sent notification appears")
    public void messageSentAppears() throws Throwable {
        String messageSent = "//span[contains(text(), 'Message sent.')]";
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(messageSent)));
        System.out.println("Message sent notification appeared.");
    }


    @And("^I click the Sent folder button")
    public void iClickSentFolderButton(){
        System.out.println("Attempting to find Sent Folder button...");
        String SENT_FOLDER_BTN = "//a[contains(@aria-label,'Sent')]";
        WebElement btn = driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(SENT_FOLDER_BTN)));
        System.out.println("Found!");
        btn.click();
    }

    @Then("^my email should be displayed in the sent folder with subject \"([^\"]*)\"$")
    public void emailShouldBeDisplayed(String subjectString) throws Throwable {
        System.out.println("Attempting to find sent email in list of sent emails...");
        String SENT_EMAIL = "//span[contains(text(),'" + subjectString + "')]";
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SENT_EMAIL)));
        System.out.println("Email was found in sent folder!");
        driver.quit();
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage(){
        System.out.println("Attempting to find error message...");
        String ERROR_MESSAGE = "//span[contains(text(),'Error')]";
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE)));
        System.out.println("Found!");
        driver.quit();
    }


    /**
     * SELENIUM SETUP
     */

    private void setupSeleniumWebDrivers() throws MalformedURLException {
        if (driver == null) {
            System.out.println("Setting up Selenium ChromeDriver...");
            String PATH_TO_CHROME_DRIVER = "D:/UbuntuShared/chromedriver/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", PATH_TO_CHROME_DRIVER);
            driver = new ChromeDriver();
            driverWait = new WebDriverWait(driver, 10);
            System.out.println("Done!");
        }
    }

    private void goTo(String url) {
        if (driver != null) {
            System.out.println("Going to " + url);
            driver.get(url);
        }
    }


    /**
     * ROBOT TYPING METHODS
     */

    public void type(CharSequence characters) throws AWTException{
        int length = characters.length();
        for (int i = 0; i < length; i++) {
            char character = characters.charAt(i);
            type(character);
        }
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        waitRobot = true;
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
