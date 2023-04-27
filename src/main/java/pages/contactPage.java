
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class contactPage {

    private  WebDriver driver;
    private  By usernameInput = By.xpath("//input[@id='first-name']");
    private  By lastnameInput = By.xpath("//input[@id='last-name']");
    private  By companyInput = By.xpath("//input[@id='company']");
    private  By phoneInput = By.xpath("//input[@id='phone']");
    private By titleInput = By.xpath("//input[@id='message-title']");
    private By messageInput = By.xpath("//*[@id='message']");
    private By submitButton = By.xpath("//*[@id='submit-button']");
    private By genreSelect = By.xpath("//*[@id='gender']");
    private By contactForm = By.xpath("//*[@id='contact-form']");
    private By closeError = By.xpath("//*[@class='popin-close']");



    // Constructor
    public contactPage(WebDriver driver) {

        this.driver = driver;
    }
    // Methods
    public void usernameInput(String name) {
        WebElement emailInput = driver.findElement(usernameInput);
        emailInput.sendKeys(name);
    }

    public void lastnameInput(String lastname) {
        WebElement passwordInput = driver.findElement(lastnameInput);
        passwordInput.sendKeys(lastname);
    }

    public void companyInput(String company) {
        WebElement passwordInput = driver.findElement(companyInput);
        passwordInput.sendKeys(company);
    }

    public void mobileInput(String mobile) {
        WebElement passwordInput = driver.findElement(phoneInput);
        passwordInput.sendKeys(mobile);
    }

    public void titleInput(String title) {
        WebElement passwordInput = driver.findElement(titleInput);
        passwordInput.sendKeys(title);
    }

    public void MessageInput(String msg) {
        WebElement passwordInput = driver.findElement(messageInput);
        passwordInput.sendKeys(msg);
    }

    public void sendBtn() {
        WebElement loginButton = driver.findElement(submitButton);
        loginButton.click();
    }
    public void errorBtn() {
        WebElement closeErrorbtn = driver.findElement(closeError);
        closeErrorbtn.click();
    }

    public void selectGenre(String genre) {
        Select select = new Select(driver.findElement(genreSelect));
        if (genre == "mr") {
            select.selectByValue("male");
        } else if (genre == "ms") {
            select.selectByValue("female");
        } else {
            select.selectByValue("other");
        }
    }

}
