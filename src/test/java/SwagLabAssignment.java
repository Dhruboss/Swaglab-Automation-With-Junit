import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class SwagLabAssignment {

    String link = "https://www.saucedemo.com/";
    WebDriver driver;

    @Before
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void A_UserLoginFail() throws InterruptedException, IOException {
        driver.get(link);
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("[type=submit]")).click();
        Thread.sleep(2000);
        WebElement messages = driver.findElement(By.className("error-button"));
        String  messageExpected = messages.getText();
        String messageActual = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertTrue(messageActual.contains(messageExpected));


        String time = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
        File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.copy(f.toPath(), new File("D:\\Assesments\\Assesment 4\\Assignment4\\src\\test\\resources\\screenshot"+ time + ".jpg").toPath());
    }
    @Test
    public void B_UserLoginSuccess() throws InterruptedException {
        driver.get(link);
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("[type=submit]")).click();
        Thread.sleep(2000);
        WebElement image = driver.findElement(By.className("app_logo"));
        boolean status = image.isDisplayed();
        Assert.assertEquals(status,true);
    }
    @Test
    public void C_OrderProcess() throws InterruptedException {
        driver.get(link);
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("[type=submit]")).click();
        Thread.sleep(2000);
        WebElement image = driver.findElement(By.className("app_logo"));
        boolean status = image.isDisplayed();
        Assert.assertEquals(status,true);
        Thread.sleep(2000);
        driver.findElement(By.className("inventory_item_name")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("checkout")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("first-name")).sendKeys("Salman");
        driver.findElement(By.id("last-name")).sendKeys("Dhrubo");
        driver.findElement(By.name("postalCode")).sendKeys("1216");
        Thread.sleep(2000);
        driver.findElement(By.name("continue")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("finish")).click();
        Thread.sleep(2000);

        WebElement messages = driver.findElement(By.className("complete-header"));
        String  messageExpected = messages.getText();
        String messageActual = "THANK YOU FOR YOUR ORDER";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }

    @Test
    public void D_Logout() throws InterruptedException {
        driver.get(link);
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("[type=submit]")).click();
        Thread.sleep(2000);
        WebElement image = driver.findElement(By.className("app_logo"));
        boolean status = image.isDisplayed();
        Assert.assertEquals(status,true);

        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    @After
    public void quit(){
        driver.quit();
    }
}
