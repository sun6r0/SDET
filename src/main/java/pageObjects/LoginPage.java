package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    final WebDriver driver;

    @FindBy(how = How.XPATH, using = "//input[@id='identifierId']")
    public WebElement txtbx_UserName;

    @FindBy(how = How.ID, using = "identifierNext")
    public WebElement btn_UserName_Next;

    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    public WebElement txtbx_Password;

    @FindBy(how = How.ID, using = "passwordNext")
    public WebElement btn_Password_Next;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void LogIn_Action(String sUserName, String sPassword){
        txtbx_UserName.sendKeys(sUserName);
        btn_UserName_Next.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(txtbx_Password));
        txtbx_Password.sendKeys(sPassword);
        btn_Password_Next.click();
    }

}

