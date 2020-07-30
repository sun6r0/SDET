import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
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
    static String username;
    static String password;
    WebDriver driver;
    MailPage MailPage;
    LoginPage LoginPage;

    @BeforeClass
    public static void getTokens() throws IOException {
        username = "tu928823@gmail.com";
        password = "Test12345!";
        //final Path tokenfile  = Paths.get(System.getProperty("user.home")).resolve("token.txt");
        //username = Files.readAllLines(tokenfile).get(0).trim();
        //password = Files.readAllLines(tokenfile).get(1).trim();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://gmail.com");
        MailPage = PageFactory.initElements(driver, MailPage.class);
        LoginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    @Test
    public void test() throws InterruptedException {
        LoginPage.login(username, password);
        long beforeSendingEmail = MailPage.getMailCountBySubject("Simbirsoft theme");
        MailPage.sendEmail(username, "Simbirsoft theme");
        long afterSendingEmail = MailPage.getMailCountBySubject("Simbirsoft theme");
        Assert.assertTrue(afterSendingEmail>beforeSendingEmail);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

}
