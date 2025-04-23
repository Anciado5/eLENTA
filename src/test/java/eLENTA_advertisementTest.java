import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    public static String generateRndLetters(int length) {
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String text = "";
        for (int i = 0; i < length; i++) {
            text += symbols.charAt((int) (Math.random()*symbols.length()));
        }
        return text;
    }

    public static String generateRndNmbr(int length) {
        String symbols = "1234567890";
        String text = "";
        for (int i = 0; i < length; i++) {
            text += symbols.charAt((int) (Math.random()*symbols.length()));
        }
        return text;
    }

    public static String generateRndSymbols(int length) {
        String symbols = "`~!@#$%^&*()_+-=[]{}|;:',<.>/?";
        String text = "";
        for (int i = 0; i < length; i++) {
            text += symbols.charAt((int) (Math.random()*symbols.length()));
        }
        return text;
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
    }

    private static void positiveTestInput() {
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdPositiveTest(){
        positiveTestInput();
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike_jpg1.jpg");
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"promotead-form\"]/table/tbody/tr[12]/td[2]/a")).click();
        driver.findElement(By.className("delete")).click();
        driver.switchTo().alert().accept();
    }

    @Test
    public void regAdWithSameInformation(){
        positiveTestInput();
    }

    @Test
    public void regAdWithNoTitle(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithSymbolAsTitle(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys(".");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWith170LettersAsTitle(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys(generateRndLetters(170));
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithNoDescription(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithOneSymbolDescription(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("/");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithThreeLettersDescription(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys(generateRndLetters(3));
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWith70000LettersDescription(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys(generateRndLetters(70000));
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithNoPrice(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithZeroAsPrice(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("0");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithTenNumberLengtPrice(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys(generateRndNmbr(10));
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithLetterAsPrice(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys(generateRndLetters(1));
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithDifferentCity(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Cekoniskes");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithNoCity(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithOneLetterCity(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys(generateRndLetters(1));
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithNoPhoneNumber(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithNoCodePhoneNumber(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("061234567");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithPhoneNumberWithSpaces(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("0 61 23 45 67");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithOneNumberPhoneNumber(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys(generateRndNmbr(1));
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithIncorrectPhoneNumber(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+1-800-123-4567");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithIncorrectPhoneNumber2(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+18001234567");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithCountryCodeAsPhoneNumber(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+370");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithLettersAsPhoneNumber(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys(generateRndLetters(5));
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithSymbolsAsPhoneNumber(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys(generateRndSymbols(3));
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithLatvianPhoneNumber(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37122389321");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithPolishPhoneNumber(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+48512345678");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithUKPhoneNumber(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+447700123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithNoEmail(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithInvalidEmail(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("user@");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithInvalidEmail1(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("email.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithInvalidEmail2(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("user@com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithInvalidEmail3(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("1@gmil.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithInvalidEmail4(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("1@1@.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithInvalidEmail5(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("@.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    @Test
    public void regAdWithNoPhoto(){
        positiveTestInput();
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"promotead-form\"]/table/tbody/tr[12]/td[2]/a")).click();
        driver.findElement(By.className("delete")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void regAdWith10JpgPhotos(){
        positiveTestInput();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("inputfile")).sendKeys(
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike_jpg1.jpg\n" +
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike 2.jpg\n" +
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike 3.jpg\n" +
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike 4.jpg\n" +
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike 5.jpg\n" +
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike 6.jpg\n" +
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike 7.jpg\n" +
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike 8.jpg\n" +
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike 9.jpg\n" +
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike 10.jpg");
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"promotead-form\"]/table/tbody/tr[12]/td[2]/a")).click();
        driver.findElement(By.className("delete")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void regAdWithPngPhoto(){
        positiveTestInput();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike_png.png");
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"promotead-form\"]/table/tbody/tr[12]/td[2]/a")).click();
        driver.findElement(By.className("delete")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void regAdWithWebpPhoto(){
        positiveTestInput();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike_webp.webp");
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"promotead-form\"]/table/tbody/tr[12]/td[2]/a")).click();
        driver.findElement(By.className("delete")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void regAdWithGifPhoto(){
        positiveTestInput();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\m_gif.gif");
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"promotead-form\"]/table/tbody/tr[12]/td[2]/a")).click();
        driver.findElement(By.className("delete")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void regAdWithBigSizePhoto(){
        positiveTestInput();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\big.jpg");
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"promotead-form\"]/table/tbody/tr[12]/td[2]/a")).click();
        driver.findElement(By.className("delete")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void regAdWithVideo(){
        positiveTestInput();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\sample-5s.mp4");
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"promotead-form\"]/table/tbody/tr[12]/td[2]/a")).click();
        driver.findElement(By.className("delete")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void regAdWithWordDocument(){
        positiveTestInput();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\dviraciu prasymo_blankas.doc");
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"promotead-form\"]/table/tbody/tr[12]/td[2]/a")).click();
        driver.findElement(By.className("delete")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }




































}
