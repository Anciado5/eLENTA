import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class eLENTA_advertisementTest {
    public static WebDriver driver;

    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

    public static String generateSpecialLtr(int length) {
        String symbols = "ÁÀÂÄÅĀĄÃÆÇĆČĐÉÈÊËĒĘĖƑĞĢĦÍÌÎÏĪĮĶĻŁŃŅÑÓÒÔÖÕØŌŒŔŘŚŠŞŤÞÚÙÛÜŪŲÝŹŻŽ";
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        acceptCookies();
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_KitasTransportas&actionId=Iesko&returnurl=%2F");
    }

    private void positiveTestInput(String title, String text, String price, String locationSearchBox, String phone, String email) {
        driver.findElement(By.id("title")).sendKeys(title);
        driver.findElement(By.id("text")).sendKeys(text);
        driver.findElement(By.id("price")).sendKeys(price);
        driver.findElement(By.id("location-search-box")).sendKeys(locationSearchBox);
        driver.findElement(By.id("phone")).sendKeys(phone);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    public void wait(int time){
        try{
            Thread.sleep(time);
        }
        catch (Exception e){}
    }

//    positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas", "+37067123456", "bike@gmail.com");

    private static void positiveTestFirstPage() {
        driver.findElement(By.id("title")).sendKeys("Used Mountain Bike for Sale");
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
    }

    private static void positiveTestNextPages(String x) {
        driver.findElement(By.id("inputfile")).sendKeys(x);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputfile")));
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"promotead-form\"]/table/tbody/tr[12]/td[2]/a")).click();
    }

    @AfterMethod
    public void afterMethod(){
        try {
            driver.findElement(By.className("delete")).click();
            driver.switchTo().alert().accept();
        } catch (Exception e) {}
    }

    @Test//+++
    public void regAdPositiveTest(){
        positiveTestFirstPage();
        positiveTestNextPages("C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike_jpg1.jpg");
        String expected = "SKELBIMAS RODOMAS";
        String actual = "";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/h4")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void regAdWithSameInformation(){
        positiveTestFirstPage();
        positiveTestNextPages("C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike_jpg1.jpg");
        String notExpected = "SKELBIMAS RODOMAS";
        String actual = "";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/h4")).getText();
        }
        catch (Exception e){}
        Assert.assertNotEquals(actual, notExpected);
    }

    @Test//+++
    public void regAdWithNoTitle(){
        positiveTestInput("", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas", "+37067123456", "bike@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("te")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithSymbolAsTitle(){
        positiveTestInput("^", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas", "+37067123456", "bike@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("te")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithOneLetterAsTitle(){
        positiveTestInput(generateRndLetters(1), "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas", "+37067123456", "bike@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("te")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithSpecialLettersAsTitle(){
        positiveTestInput(generateSpecialLtr(8), "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas", "+37067123456", "bike@gmail.com");
        String expected = "display:none";
        String actual = driver.findElement(By.id("te")).getDomAttribute("style");
        Assert.assertEquals(actual,expected);
    }

    @Test//+++
    public void regAdWith150LettersAsTitle(){
        positiveTestInput(generateRndLetters(150), "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas", "+37067123456", "bike@gmail.com");
        String expected = "display:none";
        String actual = driver.findElement(By.id("te")).getDomAttribute("style");
        Assert.assertEquals(actual,expected);
    }

    @Test//+++
    public void regAdWith151LetterAsTitle(){
        String actual = "";
        String notExpected = "display:none";

        driver.findElement(By.id("title")).sendKeys(generateRndLetters(151));
        driver.findElement(By.id("text")).sendKeys("Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.");
        driver.findElement(By.id("price")).sendKeys("150");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37067123456");
        driver.findElement(By.id("email")).sendKeys("bike@gmail.com");

        String usernameInputValue = driver.findElement(By.id("title")).getAttribute("value");
        System.out.println(usernameInputValue.length());
        Assert.assertTrue(usernameInputValue.length() == 151);

        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
        try{
            actual = driver.findElement(By.id("te")).getDomAttribute("style");
        }
        catch (Exception e){}
        Assert.assertNotEquals(actual, notExpected);
    }

    @Test//+++
    public void regAdWithCyrillicTitle(){
        positiveTestInput("иванпетров", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas", "+37067123456", "bike@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("te")).getDomAttribute("style");
        Assert.assertNotEquals(actual, notExpected);
    }

    @Test//+++
    public void regAdWithNoDescription(){
        positiveTestInput("Used Mountain Bike for Sale", "", "150", "Kaunas", "+37067123456", "bike@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("txte")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithOneSymbolDescription(){
        positiveTestInput("Used Mountain Bike for Sale", ".", "150", "Kaunas", "+37067123456", "bike@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("txte")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithTwoLettersDescription(){
        positiveTestInput("Used Mountain Bike for Sale", generateRndLetters(2), "150", "Kaunas", "+37067123456", "bike@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("txte")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithCyrillicDescription(){
        positiveTestInput("Used Mountain Bike for Sale", "иванпетров", "150", "Kaunas", "+37067123456", "bike@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("txte")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithJapaneseDescription(){
        positiveTestInput("Used Mountain Bike for Sale", "山田太郎山田太郎", "150", "Kaunas", "+37067123456", "bike@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("txte")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithNoPrice(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "", "Kaunas", "+37067123456", "bike@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("pre")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithZeroAsPrice(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "0", "Kaunas", "+37067123456", "bike@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("pre")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithTenNumberLengthPrice(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", generateRndNmbr(10), "Kaunas", "+37067123456", "bike@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("pre")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithLetterAsPrice(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", generateRndLetters(4), "Kaunas", "+37067123456", "bike@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("pre")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithNoCity(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "", "+37067123456", "bike@gmail.com");
        String expected = "Įveskite miestą.";
        String actual = "";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"location-container\"]/label/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void regAdWithOneLetterCity(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", generateRndLetters(1), "+37067123456", "bike@gmail.com");
        String expected = "Įveskite miestą.";
        String actual = "";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"location-container\"]/label/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void regAdWithSuburbCity(){
        String expected = "Avižieniai";
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", expected, "+37067123456", "bike@gmail.com");
        String actual = "";
        try{
            actual = driver.findElement(By.className("location-box")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void regAdWithJapaneseCity(){
        String notExpected = "山田太郎";
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", notExpected, "+37067123456", "bike@gmail.com");
        String actual = "";
        try{
            actual = driver.findElement(By.className("location-box")).getText();
        }
        catch (Exception e){}
        Assert.assertNotEquals(actual, notExpected);
    }

    @Test//+++
    public void regAdWithRussianCity(){
        String notExpected = "иванпетров";
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.",
                "150", notExpected, "+37067123456", "bike@gmail.com");
        String actual = "";
        try{
            actual = driver.findElement(By.className("location-box")).getText();
        }
        catch (Exception e){}
        Assert.assertNotEquals(actual, notExpected);
    }

    @Test//+++
    public void regAdWithNoPhoneNumber(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.",
                "150", "Kaunas", "", "bike@gmail.com");
        String notExpected = "display:none";
        String actualPe = driver.findElement(By.id("pe")).getDomAttribute("style");
        String actualCe = driver.findElement(By.id("ce")).getDomAttribute("style");
        Assert.assertEquals(actualPe,notExpected);
        Assert.assertNotEquals(actualCe,notExpected);
    }

    @Test//+++
    public void regAdWithNoCodePhoneNumber(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.",
                "150", "Kaunas",
                "061234567", "bike@gmail.com");
        String expected = "display:none";
        String actualPe = driver.findElement(By.id("pe")).getDomAttribute("style");
        String actualCe = driver.findElement(By.id("ce")).getDomAttribute("style");
        Assert.assertEquals(actualPe,expected);
        Assert.assertEquals(actualCe,expected);
    }

    @Test//+++
    public void regAdWithPhoneNumberWithSpaces(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas",
                "0 61 23 45 67", "bike@gmail.com");
        String notExpected = "display:none";
        String actualPe = driver.findElement(By.id("pe")).getDomAttribute("style");
        String actualCe = driver.findElement(By.id("ce")).getDomAttribute("style");
        Assert.assertNotEquals(actualPe,notExpected);//blogas
        Assert.assertEquals(actualCe,notExpected);//iveskite
    }

    @Test//+++
    public void regAdWithOneNumberPhoneNumber(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas",
                generateRndNmbr(1), "bike@gmail.com");
        String notExpected = "display:none";
        String actualPe = driver.findElement(By.id("pe")).getDomAttribute("style");
        String actualCe = driver.findElement(By.id("ce")).getDomAttribute("style");
        Assert.assertNotEquals(actualPe,notExpected);
        Assert.assertEquals(actualCe,notExpected);
    }

    @Test//+++alert
    public void regAdWithIncorrectPhoneNumber(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas",
                "+1-800-123-4567", "bike@gmail.com");
        String notExpected = "display:none";
        String actualPe = driver.findElement(By.id("pe")).getDomAttribute("style");
        Assert.assertNotEquals(actualPe,notExpected);
    }

    @Test//+++alert
    public void regAdWithUSAPhoneNumber2(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas",
                "+18001234567", "bike@gmail.com");
        WebElement nextPage = null;
        try{
            nextPage = driver.findElement(By.id("fileinput-label"));
        }
        catch (Exception e){}
        Assert.assertTrue(nextPage != null);
    }

    @Test//+++
    public void regAdWithCountryCodeAsPhoneNumber(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas",
                "+370", "bike@gmail.com");
        String notExpected = "display:none";
        String actualPe = driver.findElement(By.id("pe")).getDomAttribute("style");
        Assert.assertNotEquals(actualPe,notExpected);
    }

    @Test//+++
    public void regAdWithLettersAsPhoneNumber(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas",
                generateRndLetters(8), "bike@gmail.com");
        String notExpected = "display:none";
        String actualPe = driver.findElement(By.id("pe")).getDomAttribute("style");
        Assert.assertNotEquals(actualPe,notExpected);
    }

    @Test//+++
    public void regAdWithSymbolsAsPhoneNumber(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas",
                generateRndSymbols(8), "bike@gmail.com");
        String notExpected = "display:none";
        String actualPe = driver.findElement(By.id("pe")).getDomAttribute("style");
        Assert.assertNotEquals(actualPe,notExpected);
    }

    @Test//+++alert
    public void regAdWithLatvianPhoneNumber(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas",
                "+37122389321", "bike@gmail.com");
        String expected = "display:none";
        String actualPe = driver.findElement(By.id("pe")).getDomAttribute("style");
        Assert.assertNotEquals(actualPe,expected);
    }

    @Test//+++
    public void regAdWithPolishPhoneNumber(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas",
                "+48512345678", "bike@gmail.com");
        String expected = "display:none";
        String actualPe = driver.findElement(By.id("pe")).getDomAttribute("style");
        Assert.assertEquals(actualPe,expected);
    }

    @Test//+++
    public void regAdWithUKPhoneNumber(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas",
                "+447700123456", "bike@gmail.com");
        String expected = "display:none";
        String actualPe = driver.findElement(By.id("pe")).getDomAttribute("style");
        Assert.assertEquals(actualPe,expected);
    }

    @Test//+++
    public void regAdWithNoEmail(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150",
                "Kaunas", "+37067123456", "");
        String expected = "Įveskite el.paštą.";
        String actual = "";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"ee\"]")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void regAdWithInvalidEmail(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150",
                "Kaunas", "+37067123456", "user@");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("ee")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithInvalidEmail1(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.",
                "150", "Kaunas", "+37067123456", "@");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("ee")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithInvalidEmail2(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150", "Kaunas", "+37067123456", "email.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("ee")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithInvalidEmail3(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.",
                "150", "Kaunas", "+37067123456", "user@com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("ee")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithInvalidEmail4(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150",
                "Kaunas", "+37067123456", "1@gmil.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("ee")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithInvalidEmail5(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.", "150",
                "Kaunas", "+37067123456", "1@1@.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("ee")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithRndmNmbrAsEmail(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.",
                "150", "Kaunas", "+37067123456", generateRndNmbr(5));
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("ee")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithInvalidEmail6(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.",
                "150", "Kaunas", "+37067123456", "r^^ka *s@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("ee")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithInvalidEmail8(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.",
                "150", "Kaunas", "+37067123456", "rookas@google.mail.com");
        String expected = "display:none";
        String actual = driver.findElement(By.id("ee")).getDomAttribute("style");
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void regAdWithJapaneseEmail(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.",
                "150", "Kaunas", "+37067123456", "山田太郎@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("ee")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithRussianEmail(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.",
                "150", "Kaunas", "+37067123456", "rookas@mail.ru");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("ee")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithCyrillicEmail(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.",
                "150", "Kaunas", "+37067123456", "иван.петров@gmail.com");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("ee")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithCylrillicEmail2(){
        positiveTestInput("Used Mountain Bike for Sale", "Lightly used mountain bike in great condition. Size M, aluminum frame, 21-speed gearbox.",
                "150", "Kaunas", "+37067123456", "иван.петров@пример.рy");
        String notExpected = "display:none";
        String actual = driver.findElement(By.id("ee")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithNoPhoto(){
        positiveTestFirstPage();
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"forward-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"promotead-form\"]/table/tbody/tr[12]/td[2]/a")).click();
        String expected = "SKELBIMAS RODOMAS";
        String actual = "";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/h4")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void regAdWithOneJpgPhoto(){
        positiveTestFirstPage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("inputfile")).sendKeys(
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike_jpg1.jpg");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("photo-1")));
        boolean isDisplayed = driver.findElement(By.id("photo-1")).isDisplayed();
            Assert.assertTrue(isDisplayed);
    }

    @Test//+++
    public void regAdWith10JpgPhotos(){
        positiveTestFirstPage();
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
        wait(3000);
        String notExpected = "display: block;";
        String actual = driver.findElement(By.id("fileupload-message")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithPngPhoto(){
        positiveTestFirstPage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("inputfile")).sendKeys(
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike_png.png");
        wait(3000);
        String notExpected = "display: block;";
        String actual = driver.findElement(By.id("fileupload-message")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithWebpPhoto(){
        positiveTestFirstPage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("inputfile")).sendKeys(
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\mountain bike_webp.webp");
        wait(3000);
        String expected = "display: block;";
        String actual = driver.findElement(By.id("fileupload-message")).getDomAttribute("style");
        Assert.assertEquals(actual,expected);
    }

    @Test//+++
    public void regAdWithGifPhoto(){
        positiveTestFirstPage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("inputfile")).sendKeys(
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\m_gif.gif");
        wait(3000);
        String expected = "display: block;";
        String actual = driver.findElement(By.id("fileupload-message")).getDomAttribute("style");
        Assert.assertEquals(actual,expected);
    }

    @Test//+++
    public void regAdWithBigSizePhoto(){
        positiveTestFirstPage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("inputfile")).sendKeys(
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\big.jpg");
        wait(3000);
        String notExpected = "display: block;";
        String actual = driver.findElement(By.id("fileupload-message")).getDomAttribute("style");
        Assert.assertNotEquals(actual,notExpected);
    }

    @Test//+++
    public void regAdWithVideo(){
        positiveTestFirstPage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("inputfile")).sendKeys(
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\sample-5s.mp4");
        wait(3000);
        String expected = "display: block;";
        String actual = driver.findElement(By.id("fileupload-message")).getDomAttribute("style");
        Assert.assertEquals(actual,expected);
    }

    @Test//+++
    public void regAdWithWordDocument(){
        positiveTestFirstPage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("inputfile")).sendKeys(
                "C:\\Users\\Owner\\IdeaProjects\\eLENTA\\photos\\dviraciu prasymo_blankas.doc");
        wait(3000);
        String expected = "display: block;";
        String actual = driver.findElement(By.id("fileupload-message")).getDomAttribute("style");
        Assert.assertEquals(actual,expected);
    }




































}
