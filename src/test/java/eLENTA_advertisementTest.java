import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class eLENTA_advertisementTest {
    public static WebDriver driver;

    public void acceptCookies(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.className("fc-button-label")).click();
    }
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        acceptCookies();
    }
    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("text")).clear();
        driver.findElement(By.id("price")).clear();
        driver.findElement(By.id("location-search-box")).clear();
        driver.findElement(By.id("phone")).clear();
        driver.findElement(By.id("email")).clear();
    }
    @Test
    public void regAdPositiveTest(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
//        driver.findElement(By.xpath("//*[@id=\"fileinput-label\"]")).click();
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike_jpg.jpg");
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"promotead-form\"]/table/tbody/tr[12]/td[2]/a")).click();
    }

























}
