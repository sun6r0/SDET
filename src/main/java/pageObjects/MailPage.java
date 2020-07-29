package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MailPage {
    final WebDriver driver;

    @FindBy(className="bog")
    public List<WebElement> listOfThemesElements;

    @FindBy(how =How.XPATH, using = "//div[@class='aic']/div/div")
    public WebElement btn_Write;

    @FindBy(how =How.XPATH, using = "//textarea[@name = 'to']")
    public WebElement txtbx_To;

    @FindBy(how =How.XPATH, using = "//input[@name = 'subjectbox']")
    public WebElement txtbx_Subject;

    @FindBy(how =How.XPATH, using = "//div[@role = 'textbox']")
    public WebElement txtbx_Text;

    @FindBy(how =How.XPATH, using = "//div[@class = 'dC']/div[1]")
    public WebElement btn_Send;

    public MailPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public long Mail_Count(String subject) {
        return listOfThemesElements
                .stream()
                .filter(c -> c.getText().startsWith(subject))
                .count();
    }

    public void Mail_Action(String email, String subject) throws InterruptedException {
        long mail_count = Mail_Count(subject);
        btn_Write.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(txtbx_To));
        txtbx_To.sendKeys(email);
        txtbx_To.sendKeys(Keys.ENTER);
        txtbx_Subject.sendKeys(subject);
        txtbx_Text.sendKeys("Найдено " + mail_count + " писем(ма).");
        btn_Send.click();
        Thread.sleep(5000);
    }
}
