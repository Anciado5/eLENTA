import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class VYNOTEKA_papildomai {
    public static WebDriver driver;
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    public void acceptCookiesAgeVerification() {
        driver.get("https://vynoteka.lt/");
        driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();

        driver.findElement(By.xpath("//*[@id=\"app__inner\"]/div[2]/div/div/div/div/div[2]/div[3]/div/div[1]/button")).click();
        while (true){
            try{
                driver.findElement(By.id("omnisend-form-63ff1f31b40d6530aba59a6d-close-action")).click();
                break;
            }
            catch(Exception e){
                try { //kompiliatorius pasiule apvrepint
                    Thread.sleep(1000);
                } catch (InterruptedException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        }

    }
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        acceptCookiesAgeVerification();
    }
    @Test
    public void cookiesAndAge(){
        driver.get("https://vynoteka.lt/");
    }
    //sukurtas naujas projektas su WebDriver wait funkcija





}
