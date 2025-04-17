import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class eLENTA_registrationTest {
    public static WebDriver driver;

    public void acceptCookies(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.className("fc-button-label")).click();
    }
    public static String generateRndStr(int length) {
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String text = "";
        for (int i = 0; i < length; i++) {
            text += symbols.charAt((int) (Math.random()*symbols.length()));
        }
        return text;
    }
    private static eLENTA_registrationTest PasswordUtils;
    public static final String threeUnitPass = PasswordUtils.generateRndStr(3);
    public static final String manyUnitPass = PasswordUtils.generateRndStr(100);

    @BeforeClass
        public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        acceptCookies();
    }
    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).clear();
        driver.findElement(By.id("Email")).clear();
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password2")).clear();
    }
    @Test//+++
    public void registrationPositiveTest(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys("lookas");
        driver.findElement(By.id("Email")).sendKeys("lookas@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("lookas123");
        driver.findElement(By.id("Password2")).sendKeys("lookas123");
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }
    @Test//+++
    public void registrationSameUsername(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys("lookas");
        driver.findElement(By.id("Email")).sendKeys(Math.round(Math.random() * 888) + 888 + "lookas@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("lookas123");
        driver.findElement(By.id("Password2")).sendKeys("lookas123");
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }
    @Test//+++
    public void registrationSameEmail(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys("lookas" + Math.round(Math.random() * 888) + 888);
        driver.findElement(By.id("Email")).sendKeys("lookas@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("lookas123");
        driver.findElement(By.id("Password2")).sendKeys("lookas123");
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }
    @Test//+++
    public void registrationOneLetterUsername(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys("Ž");
        driver.findElement(By.id("Email")).sendKeys(Math.round(Math.random() * 888) + 888 + "lookas@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("lookas123");
        driver.findElement(By.id("Password2")).sendKeys("lookas123");
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }
    @Test//+++
    public void registrationNumberAsUsername(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys(Math.round(Math.random() * 888) + 888 + "");
        driver.findElement(By.id("Email")).sendKeys(Math.round(Math.random() * 888) + 888 + "lookas@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("lookas123");
        driver.findElement(By.id("Password2")).sendKeys("lookas123");
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }
    @Test//+++
    public void registrationNoUsername(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys("");
        driver.findElement(By.id("Email")).sendKeys(Math.round(Math.random() * 888) + 888 + "lookas@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("lookas123");
        driver.findElement(By.id("Password2")).sendKeys("lookas123");
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }
    @Test//+
    public void registrationInvalidEmail(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys("lookas" + Math.round(Math.random() * 888) + 888);
        driver.findElement(By.id("Email")).sendKeys(generateRndStr(8));
        driver.findElement(By.id("Password")).sendKeys("lookas123");
        driver.findElement(By.id("Password2")).sendKeys("lookas123");
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }
    @Test//+
    public void registrationInvalidEmail2(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys("lookas" + Math.round(Math.random() * 888) + 888);
        driver.findElement(By.id("Email")).sendKeys("lookas@g.com");
        driver.findElement(By.id("Password")).sendKeys("lookas123");
        driver.findElement(By.id("Password2")).sendKeys("lookas123");
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }
    @Test//+
    public void registrationInvalidEmail3(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys("lookas" + Math.round(Math.random() * 888) + 888);
        driver.findElement(By.id("Email")).sendKeys("lookas@");
        driver.findElement(By.id("Password")).sendKeys("lookas123");
        driver.findElement(By.id("Password2")).sendKeys("lookas123");
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }
    @Test//+
    public void registrationInvalidEmail4(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys("lookas" + Math.round(Math.random() * 888) + 888);
        driver.findElement(By.id("Email")).sendKeys("lookas@.com");
        driver.findElement(By.id("Password")).sendKeys("lookas123");
        driver.findElement(By.id("Password2")).sendKeys("lookas123");
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }
    @Test//+
    public void registrationNoEmail(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys("lookas" + Math.round(Math.random() * 888) + 888);
        driver.findElement(By.id("Email")).sendKeys("");
        driver.findElement(By.id("Password")).sendKeys("lookas123");
        driver.findElement(By.id("Password2")).sendKeys("lookas123");
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }
    @Test//+
    public void registrationInvalidPassword(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys("lookas" + Math.round(Math.random() * 888) + 888);
        driver.findElement(By.id("Email")).sendKeys(Math.round(Math.random() * 888) + 888 + "lookas@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("------");
        driver.findElement(By.id("Password2")).sendKeys("------");
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }
    @Test//+
    public void registrationThreeUnitPassword(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys("lookas" + Math.round(Math.random() * 888) + 888);
        driver.findElement(By.id("Email")).sendKeys(Math.round(Math.random() * 888) + 888 + "lookas@gmail.com");
        driver.findElement(By.id("Password")).sendKeys(threeUnitPass);
        driver.findElement(By.id("Password2")).sendKeys(threeUnitPass);
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
        System.out.println(threeUnitPass);
    }
    @Test//+
    public void registrationManyUnitsPassword(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys("lookas" + Math.round(Math.random() * 888) + 888);
        driver.findElement(By.id("Email")).sendKeys(Math.round(Math.random() * 888) + 888 + "lookas@gmail.com");
        driver.findElement(By.id("Password")).sendKeys(manyUnitPass);
        driver.findElement(By.id("Password2")).sendKeys(manyUnitPass);
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }
    @Test
    public void registrationMissmatchedPassword(){
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.id("UserName")).sendKeys("lookas" + Math.round(Math.random() * 888) + 888);
        driver.findElement(By.id("Email")).sendKeys(Math.round(Math.random() * 888) + 888 + "lookas@gmail.com");
        driver.findElement(By.id("Password")).sendKeys(generateRndStr(6));
        driver.findElement(By.id("Password2")).sendKeys(generateRndStr(7));
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }
    @AfterClass
    public void tearDown(){
        driver.close();
    }


























}
