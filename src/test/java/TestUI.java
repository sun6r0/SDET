import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MailPage;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.concurrent.TimeUnit;


public class TestUI {
    static WebDriver driver;
    MailPage MailPage;
    LoginPage LoginPage;

   @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://accounts.google.com/signin");
        MailPage = PageFactory.initElements(driver, MailPage.class);
        LoginPage = PageFactory.initElements(driver, LoginPage.class);

    }
    @Test
    public void test() throws IOException, InterruptedException {
        final Path tokenfile  = Paths.get(System.getProperty("user.home")).resolve("token.txt");
        final String username = Files.readAllLines(tokenfile).get(0).trim();
        final String password = Files.readAllLines(tokenfile).get(1).trim();
        LoginPage.LogIn_Action(username, password);
        driver.get("https://mail.google.com/mail/u/0/#inbox");
        long beforeSendingEmail = MailPage.Mail_Count("Simbirsoft theme");
        MailPage.Mail_Action(username, "Simbirsoft theme");
        long afterSendingEmail = MailPage.Mail_Count("Simbirsoft theme");
        Assert.assertTrue(afterSendingEmail>beforeSendingEmail);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

}
