import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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

    public static String generateRndLtr(int length) {
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

    private static eLENTA_registrationTest PasswordUtils;

    public static final String threeUnitPass = PasswordUtils.generateRndStr(3);

    public static final String manyUnitPass = PasswordUtils.generateRndStr(128);

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
    }

    public void fillForm(String username, String email, String password, String password2){
        driver.findElement(By.id("UserName")).sendKeys(username);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("Password2")).sendKeys(password2);
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }

    @Test//+++
    public void registrationPositiveTest(){
        fillForm("rookas", "rookas@gmail.com", "rookas123", "rookas123");
        String expected = "Jūs sėkmingai prisiregistravote!";
        String actual = "";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationSameUsername(){
        fillForm("rookas", "rookas@gmail.com", "rookas123", "rookas123");
        String expected = "Vartotojas tokiu vardu jau įregistruotas. Bandykite pasirinkti kitą.";
        String actual = "";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationSameEmail(){
        fillForm("rookas" + (int)Math.round(Math.random() * 888) + 888, "rookas@gmail.com", "rookas123", "rookas123");
        String expected = "Toks el. pašto adresas jau įregistruotas.";
        String actual = "";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationOneLetterUsername(){
        fillForm(generateRndLtr(1), (int)Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", "rookas123", "rookas123");
        String actual = "";
        String expected = "Vartotojo vardas turi būti bent iš trijų simbolių.";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registration256LettersUsername(){
        fillForm(generateRndLtr(256), (int)Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", "rookas123", "rookas123");
        String actual = "";
        String expected = "Jūs sėkmingai prisiregistravote!";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registration257LettersUsername(){
//        fillForm(generateRndLtr(257), (int)Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", "rookas123", "rookas123");
        String actual = "";
        String expected = "Vartotojo vardas negali viršyti 256 simbolių.";

        driver.findElement(By.id("UserName")).sendKeys(generateRndLtr(257));
        driver.findElement(By.id("Email")).sendKeys((int)Math.round(Math.random() * 888) + 888 + "rookas@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("rookas123");
        driver.findElement(By.id("Password2")).sendKeys("rookas123");

        String usernameInputValue = driver.findElement(By.id("UserName")).getAttribute("value");
        System.out.println(usernameInputValue.length());
        Assert.assertTrue(usernameInputValue.length() == 257);

        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationNumberAsUsername(){
        fillForm(Math.round(Math.random() * 888) + 888 + "", Math.round(Math.random() * 888) + 888 + "lookas@gmail.com", "lookas123", "lookas123");
        String actual = "";
        String expected = "Vartotojo vardas turi turėti bent vieną raidę.";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationNoUsername(){
        fillForm("", Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", "rookas123", "rookas123");
        String actual = "";
        String expected = "Įveskite vartotojo vardą.";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationSymbolAsUsername(){
        fillForm("^", Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", "rookas123", "rookas123");
        String actual = "";
        String expected = "Netinkamas vartotojo vardas.";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationMultipleSymbolsAsUsername(){
        fillForm("(*'; I; '*)", Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", "rookas123", "rookas123");
        String actual = "";
        String expected = "Netinkamas vartotojo vardas.";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationNoEmail(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, "", "rookas123", "rookas123");
        String actual = "";
        String expected = "Įveskite el. pašto adresą.";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationInvalidEmail(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, generateRndStr(8), "rookas123", "rookas123");
        String actual = "";
        String expected = "El. pašto adresas nėra tinkamas.";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationInvalidEmail2(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, "rookas@g.com", "rookas123", "rookas123");
        String actual = "";
        String expected = "El. pašto adresas nėra tinkamas.";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationInvalidEmail3(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, "rookas@", "rookas123", "rookas123");
        String actual = "";
        String expected = "El. pašto adresas nėra tinkamas.";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationInvalidEmail4(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, "rookas@.com", "rookas123", "rookas123");
        String actual = "";
        String expected = "El. pašto adresas nėra tinkamas.";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationInvalidEmail5(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, "rookas@google.mail.com", "rookas123", "rookas123");
        String actual = "";
        String expected = "Jūs sėkmingai prisiregistravote!";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationInvalidEmail6(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, "r^^ka *s@gmail.com", "rookas123", "rookas123");
        String actual = "";
        String expected = "El. pašto adresas nėra tinkamas.";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationInvalidEmail7(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, "rookas@mail.ru", "rookas123", "rookas123");
        String actual = "";
        String expected = "Jūs sėkmingai prisiregistravote!";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationInvalidEmail8(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, "山田太郎@gmail.com", "rookas123", "rookas123");
        String actual = "";
        String expected = "Jūs sėkmingai prisiregistravote!";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationInvalidEmail9(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, "иван.петров@пример.рy", "rookas123", "rookas123");
        String actual = "";
        String expected = "Jūs sėkmingai prisiregistravote!";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationInvalidEmail10(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, "иван.петров@gmail.com", "rookas123", "rookas123");
        String actual = "";
        String expected = "Jūs sėkmingai prisiregistravote!";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationNoPassword(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, (int)Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", "", "");
        String actual = "";
        String expected = "Įveskite slaptažodį.";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationThreeUnitPassword(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, (int)Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", threeUnitPass, threeUnitPass);
        String actual = "";
        String expected = "Įvestas slaptažodis per trumpas.";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
        System.out.println(threeUnitPass);
    }

    @Test//+++
    public void registrationInvalidPassword(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, (int)Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", "------", "------");
        String actual = "";
        String expected = "Slaptažodyje turi būti bent vienas skaičius ir raidė!";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationManyUnitsPassword(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, (int)Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", manyUnitPass, manyUnitPass);
        String actual = "";
        String expected = "Jūs sėkmingai prisiregistravote!";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationInvalidPassword2(){
//        fillForm("rookas" + Math.round(Math.random() * 888) + 888, (int)Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", manyUnitPass + "A", manyUnitPass + "A");
        String actual = "";
        String expected = "Slaptažodis negali viršyti 128 simbolių!";

        driver.findElement(By.id("UserName")).sendKeys("rookas" + Math.round(Math.random() * 888) + 888);
        driver.findElement(By.id("Email")).sendKeys((int)Math.round(Math.random() * 888) + 888 + "rookas@gmail.com");
        driver.findElement(By.id("Password")).sendKeys(manyUnitPass + "A");
        driver.findElement(By.id("Password2")).sendKeys(manyUnitPass + "A");

        String passwordInputValue = driver.findElement(By.id("Password")).getAttribute("value");
        System.out.println(passwordInputValue.length());
        Assert.assertTrue(passwordInputValue.length() == 129);

        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationInvalidPassword3(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, (int)Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", "(*'; I7; '*)", "(*'; I7; '*)");
        String actual = "";
        String expected = "Slaptažodyje negali būti tarpų!";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationCyrillicPassword(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, (int)Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", "иванпетров", "иванпетров");
        String actual = "";
        String expected = "Jūs sėkmingai prisiregistravote!";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationJapanesePassword(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, (int)Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", "山田太郎山田太郎", "山田太郎山田太郎");
        String actual = "";
        String expected = "Jūs sėkmingai prisiregistravote!";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @Test//+++
    public void registrationMismatchedPassword(){
        fillForm("rookas" + Math.round(Math.random() * 888) + 888, Math.round(Math.random() * 888) + 888 + "rookas@gmail.com", generateRndStr(6), generateRndStr(7));
        String actual = "";
        String expected = "Nesutampa slaptažodžiai. Pakartokite.";
        try{
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[8]/td[2]/span")).getText();
        }
        catch (Exception e){}
        Assert.assertEquals(actual, expected);
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }


























}
