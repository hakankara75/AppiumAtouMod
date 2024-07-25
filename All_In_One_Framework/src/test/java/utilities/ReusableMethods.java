package utilities;

import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static utilities.Driver.getDriver;

public class ReusableMethods {
    /**
     * Bu metot ile scenariolarin tagleri alinir. Bu tagler'e bakilarak kosulan scenario sonrasinda
     * driver kapatilmak istenmezse @After icinde if kurularak closeDriver() calistirilmaz.
     * @param tags terine @After anatosyonlu metodun scenario parametresi verilir
     * @param exeptList yerine driver kapatmak istemedigimiz scneario tagleri list halinde verilir.
     * @return boolean olarak doner
     */
    public static boolean getApkForTags(List<String> tags, List<String> exeptList) {
        boolean isTrue=true;
        List<String> apkList = new ArrayList<>();

        for (String tag : tags) {
            for (int i = 0; i < exeptList.size(); i++) {
                String str=exeptList.get(i);
                if (!str.equals(tag)) {
                    isTrue=false;
                }
            }
        }
        System.out.println("isTrue = " + isTrue);
        return isTrue;
    }
    /**
     * Bu metot Action class kullanarak bir webelementin ustune gidip bekler
     * @param element yerine webelement'in locate koyulmalidir
     */
    public static void moveToElementWithAction(WebElement element){
        Actions action = new Actions(getDriver());

        try {
            action.moveToElement(element).perform();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //HARD WAIT METHOD
    public static void bekle(int saniye) {
        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Alert ACCEPT
    public static void alertAccept() {
        getDriver().switchTo().alert().accept();
    }

    //Alert DISMISS
    public static void alertDismiss() {
        getDriver().switchTo().alert().dismiss();
    }

    //Alert getText()
    public static void alertText() {
        getDriver().switchTo().alert().getText();
    }

    //Alert promptBox
    public static void alertprompt(String text) {
        getDriver().switchTo().alert().sendKeys(text);
    }

    //DropDown VisibleText
    /*
        Select select2 = new Select(gun);
        select2.selectByVisibleText("7");

        //ddmVisibleText(gun,"7"); --> Yukarıdaki kullanım yerine sadece method ile handle edebilirim
     */
    public static void ddmVisibleText(WebElement ddm, String secenek) {
        Select select = new Select(ddm);
        select.selectByVisibleText(secenek);
    }

    //DropDown Index
    public static void ddmIndex(WebElement ddm, int index) {
        Select select = new Select(ddm);
        select.selectByIndex(index);
    }

    //DropDown Value
    public static void ddmValue(WebElement ddm, String secenek) {
        Select select = new Select(ddm);
        select.selectByValue(secenek);
    }

    //SwitchToWindow1
    public static void switchToWindow(int sayi) {
        List<String> tumWindowHandles = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tumWindowHandles.get(sayi));
    }

    //SwitchToWindow2
    public static void window(int sayi) {
        getDriver().switchTo().window(getDriver().getWindowHandles().toArray()[sayi].toString());
    }
    //EXPLICIT WAIT METHODS

    /**
     * element visible olana kadar bekler
     * @param element beklenecek element locate verilir
     * @param sayi beklenecek sure verilir
     */
    public static void visibleWait(WebElement element, int sayi) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    /**
     * element clickable olana kadar bekler
     * @param element beklenecek element locate verilir
     * @param sayi beklenecek sure verilir
     */
    public static void clickableWait(WebElement element, int sayi) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }
    //VisibleElementLocator Wait
    public static WebElement visibleLocateWait(By locator, int sayi) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(sayi));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    //Alert Wait
    public static void alertWait(int sayi) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.alertIsPresent());

    }

    //Tüm Sayfa ScreenShot parametreli olan içine resmin nereye ait olduğu yazılır
    public static void tumSayfaResmi(String name) {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/screenshot/screenshot" + tarih + name + ".png";
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        try {
            FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void tumSayfaResmi() {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/screenshot/screenshot" + tarih + ".png";
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        try {
            FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //WebElement ScreenShot
    public static void webElementResmi(WebElement element) {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/screenshot/webElementScreenshot" + tarih + ".png";

        try {
            FileUtils.copyFile(element.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //WebTable
    public static void printData(int satir, int sutun) {
        WebElement satirSutun = getDriver().findElement(By.xpath("(//tbody)[1]//tr[" + satir + "]//td[" + sutun + "]"));
        System.out.println(satirSutun.getText());
    }

    //Click Method
    public static void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    /**
     * bu metot ile bir webelement icindeki text temizlenir.
     * @param element icinden text silinecek olan webelement locate verilir
     */
    public static void clearTextBox(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].value = '';", element);
    }


    /**
     * bu metot ile locate'i verilen elemente kadar sayfa scroll yapılır.
     * @param element yerine üzerine scroll yapılacak webelelement locate'i verilir
     */
    public static void scrollToElementByJavaScript(WebElement element){
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    /**
     * bu metot ile javascript kullanarak sayfanın en altına scroll yapılır
     */
    public static void scrollEndByJavascript(){
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * bu metot ile javascript kullanarak sayfanın en üstüne scroll yapılır
     */
    public static void scrollTopByJavascript(){
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
    }
    /**
     * bu metot javascript kullanarak bir elementin value'sunu string olarak döndürür
     * @param id yerine elementin id değeri verilir
     * @param attributeName yerine elementin id'si yazılır
     * @return string return eder
     */

    /**
     * bu metot ile javascript kullanarak bir elemente sendKey yapılır
     * @param element sendKey yapılacak elementin locate verilmeli
     * @param text elemente gönderilecek değer verilmeli
     */
    public static void sendKeysJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].value='" + text + "'", element);

    }

    /**
     * bu metot ile bir elementin value'suna deger atanir.
     * @param element deger atanacak elementin locate verilmeli
     * @param text elemente gönderilecek value verilmeli
     */
    public static void sendAttributeJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);
    }

    /** bu metot ile girilen attribute degerleri ile texti alabilirim
     @param id girilmesi gereken id degeri
     @param attributeName gonderilmesi gereken attribute ismi
     */
    public static void getValueByJavaScript(String id, String attributeName) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String string = js.executeScript("return document.getElementById('" + id + "')." + attributeName).toString();
        System.out.println(string);
    }

    /**
     * Bu metot ile locate verilen elementin value değeri string olarak döndürülür
     * @param element yerine locate verilecek
     * @return
     */
    public static String getValueByJavaScript(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
        return (String) javascriptExecutor.executeScript("return arguments[0].value", element);
    }

    /**
     * elemente JavascriptExecutor ile string gonderir(java sendkey() ile ayni)
     *
     * @param string     webElemente sendKey ile gonderilecek text
     * @param webElement sendKey ile gonderilecek webelement
     */
    public static void sendKeyWithJavaScript(String string, WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();//Casting
        jse.executeScript("arguments[0].value = '" + string + "';", webElement);

    }

    /**
     * bu metot ile bir elemente sendKey gönderilir ardından da enter yapılır
     * @param string gönderilecek metin
     * @param webElement metin gönderilip enter yapılacak element locati
     */
    public static void sendKeyAndEnterWithJavaScript(String string, WebElement webElement) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();;

// JavaScript kodunu kullanarak sendKeys işlemi gerçekleştirin
        jsExecutor.executeScript("arguments[0].value='"+string+"';", webElement);

// Enter tuşunu göndermek için JavaScript kodunu kullanın
        try {
            jsExecutor.executeScript("arguments[0].dispatchEvent(new KeyboardEvent('keypress', { 'key': 'Enter' }));", webElement);
        }catch (Exception e) {}

    }

    /**
     * javascript ile webelemente sendkey yapma methodu.
     *
     * @param webelementXpathYolu webelement yolu string olarak xpath seklinde girilir
     * @param gonderilecekText    sendkey yapilacak text
     */
    public static void sendKeyWithJavaScriptWithXpath(String webelementXpathYolu, String gonderilecekText) {
        WebElement element = getDriver().findElement(By.xpath(webelementXpathYolu));
        sendKeyWithJavaScript(gonderilecekText, element);
    }

    /**
     * javascript ile webelemente sendkey yapma methodu.
     *
     * @param webelementCssYolu webelement yolu string olarak Css seklinde girilir
     * @param gonderilecekText  sendkey yapilacak text
     */
    public static void sendKeyWithJavaScriptWithCss(String webelementCssYolu, String gonderilecekText) {
        WebElement element = getDriver().findElement(By.cssSelector(webelementCssYolu));
        sendKeyWithJavaScript(gonderilecekText, element);
    }


    /**
     * bu metot ile herhangi bir webelemente xpath vererek JavascriptExecutor kullanarak tiklayabilirim
     *
     * @param string elementin xpath cinsinden yolu
     */
    public static void clickByJavaScriptWithXpath(String string) {
        WebElement element = getDriver().findElement(By.xpath(string));
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();

        jse.executeScript("arguments[0].click();", element);

    }


    /**
     * bu metot ile herhangi bir webelemente JavascriptExecutor kullanarak tiklayabilirim
     *
     * @param webElement click yapilacak webelement
     */
    public static void clickByJavaScript(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();

        jse.executeScript("arguments[0].click();", webElement);

    }


    /**
     * bu metot ile herhangi bir webelemente cssSelector vererek JavascriptExecutor kullanarak tiklayabilirim
     *
     * @param string elementin xpath cinsinden yolu
     */
    public static void clickByJavaScriptWithCss(String string) {
        WebElement element = getDriver().findElement(By.cssSelector(string));
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();

        jse.executeScript("arguments[0].click();", element);

    }

    /**
     * Bu metot ile elementin xpath değeri string olarak verilerek o classtaki text alinir.
     *
     * @param className text degeri alinmak istenen class ismi string olarak verilir
     * @return
     */
    public static String getTextWithJavaScript(String className) {
        WebElement element = getDriver().findElement(By.className(className));

        // JavaScriptExecutor kullanarak elementin içeriğini al
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        String text = (String) jsExecutor.executeScript("return arguments[0].textContent;", element);
        return text;
    }

    /**
     * Bu metot ile elementin className değeri string olarak verilerek o classtaki text alinir.
     *
     * @param  xpath text degeri alinmak istenen elementin xpathi string olarak verilir
     * @return
     */
    public static String getTextWithJavaScriptXpath(String xpath) {
        WebElement element = getDriver().findElement(By.xpath(xpath));

        // JavaScriptExecutor kullanarak elementin içeriğini al
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        String text = (String) jsExecutor.executeScript("return arguments[0].textContent;", element);
        return text;
    }

    /**
     * Bu metot string olarak verilen textteki rakamlar haric herseyi siler ve Integer'a donusturur.
     *
     * @param string icindeki harf, karakter ve bosluklar silinecek text
     * @return Integer dondurur
     */
    public static Integer stringConverToInteger(String string) {
        Integer integer = Integer.valueOf(string.replaceAll("[^0-9]", ""));
        return integer;
    }

    /**
     * sayfayi verilen pixel kadar asagi kaydirir
     *
     * @param scrollSize pixel degeridir. integer olarak yazilmali
     */
    public static void scroll(int scrollSize) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();

        // Örneğin, sayfayı 500 piksel aşağı kaydırmak için:
        jsExecutor.executeScript("window.scrollBy(0, " + scrollSize + ");");
    }

    /**
     * * Bu metot sayfadaki expected ve actual urun sayilari birbirine esit olana kadar sayfayi scroll yapar.
     * * Esit degilse sayfa altindaki sayfa numaralarina basarak son sayfaya ve son urune kadar ilerler.
     * sonunda da toplam urun sayisini dondurur
     *
     * @param first  sayfada goruntulenen urun sayisini int olarak verilmeli
     * @param second buraya baslangic degeri olarak 0 verilmeli
     * @return bu metot toplam urun sayisini verir
     */
    public static long ifNotEqualGoScroll(int first, int second) {
        Long urunSayisiniEkle = 0L;
        do {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
            long elementCountJavascript = (long) jsExecutor.executeScript(
                    "return document.querySelectorAll('.ProductItem__Wrapper').length;"
            );

            long elementCount = elementCountJavascript;

            if (first != elementCount) {
                urunSayisiniEkle = urunSayisiniEkle + elementCount;
                WebElement element = webelementJavaScript("document.querySelector(\"a[title='Sonraki Sayfa']\")");

                if (first != urunSayisiniEkle) {
                    webElementResmi(element);
                    clickByJavaScript(element);
                }
            }
        } while (first != urunSayisiniEkle);
        return urunSayisiniEkle;
    }

    /**
     * JavaScript ile webelement olusturma
     *
     * @param javascriptYolu internet sitesinden sag klik ile JS yolunu kopyala ile alınan metin olacak
     */
    public static WebElement webelementJavaScript(String javascriptYolu) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement webElement = (WebElement) js.executeScript("return " + javascriptYolu + "");
        return webElement;
    }

    /**
     * İşlem yapılacak olan webelementin arkaplanını renklendirir
     * @param element etrafi cizilecek element
     * @param driver driver
     */
    public static void flash(WebElement element,WebDriver driver){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        String elementColor=element.getCssValue("backgroundColor"); //locate alinan yerin  arka plan rengini alir
        for (int i = 0; i < 10; i++) {
            changeColor("rgb(0,0,0)", element, driver); //elemente siyah renk verir rgb kizmi rengi belirtir
            //changeColor("rgb(255,0,0)", element, driver); //kirmizi renk
            //changeColor("rgb(0,255,0)", element, driver); //yesil renk
            changeColor(elementColor, element, driver);
        }
    }

    /**
     * flash metoduna renk degistirme islemini yaptirir. Elementin arka plan renginin parametre olarak atanacagini bildirir.
     * @param color arka plan rengi
     * @param element   arka plan rengi degisecek element
     * @param driver
     */
    public static void changeColor(String color, WebElement element, WebDriver driver){
        JavascriptExecutor js= (JavascriptExecutor) driver; //javascript kodlarini calistirir
        js.executeScript("arguments[0].style.backgroundColor='"+color+"'", element); //elementin renginin degismesini sağlar

        try{
            Thread.sleep(20);
        }catch (Exception e){

        }
    }
    public static void robotClassDosyaYukleme(String filePath){
        try{
            ReusableMethods.bekle(3);
            StringSelection stringSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);
            Robot robot = new Robot();
            //pressing ctrl+v
            robot.keyPress(KeyEvent.VK_CONTROL);
            ReusableMethods.bekle(3);
            robot.keyPress(KeyEvent.VK_V);
            ReusableMethods.bekle(3);
            //releasing ctrl+v
            robot.keyRelease(KeyEvent.VK_CONTROL);
            ReusableMethods.bekle(3);
            robot.keyRelease(KeyEvent.VK_V);
            ReusableMethods.bekle(3);
            System.out.println("PASSED");
            //pressing enter
            ReusableMethods.bekle(3);
            robot.keyPress(KeyEvent.VK_ENTER);
            ReusableMethods.bekle(3);
            //releasing enter
            robot.keyRelease(KeyEvent.VK_ENTER);
            ReusableMethods.bekle(3);
            System.out.println("ENTER");
        }catch (Exception e){
        }
    }

    /** Bu metot islem yapilacak elementin etrafina renkli cerceve cizerek belirgin hale getirir.
     *
     * @param locate islem yapilacak elementin cssSelector turunden locate string olarak girilmeli
     */
    public static void showElementWithFrame(String locate){
        WebElement element = getDriver().findElement(By.cssSelector(""+locate+""));
        String script = "arguments[0].style.border='3px solid red';";
//        String script = "arguments[0].style.border='3px solid white';";
//        String script = "arguments[0].style.border='3px solid yellow';";
//        String script = "arguments[0].style.border='3px solid green';";
        ((JavascriptExecutor) getDriver()).executeScript(script, element);

    }
    /**
     * bu metot ile verilen elemente Acrions class ile scroll yapilir
     * @param driver yerine driver verilir
     * @param element yerine uzerine scroll yapilacak elemetn locate verilir
     */
    public static void scroll(WebDriver driver, WebElement element){
        Actions actions= new Actions(driver);
        actions.scrollToElement(element).perform();
    }


    public static String addProductRandomly(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        List <WebElement> addElementTitle = getDriver().findElements(By.xpath("//h2[@class='ProductItem__Title Heading']/a"));
        List <WebElement> addElementPrice = getDriver().findElements(By.cssSelector(".ProductItem__PriceList .ProductItem__Price.Price--highlight"));
        List <WebElement> addElement = getDriver().findElements(By.cssSelector("button[class='ProductForm__AddToCart Button Button--secondary Button--full']"));

        int sayfadakiUrunSayisi=addElement.size();
        Faker faker= new Faker();
        int number= faker.number().numberBetween(1, sayfadakiUrunSayisi);
        String text="";
        for (int i = 1; i < sayfadakiUrunSayisi; i++) {
            if(i == number){
                if(! addElement.get(i).isDisplayed()){
                    ReusableMethods.scroll( getDriver(), addElement.get(i));
                }

                String elementTitleText=addElementTitle.get(i).getText();

                String elementPriceText=addElementPrice.get(i).getText();

                text=elementTitleText+" "+elementPriceText;

                ReusableMethods.clickByJavaScript(addElement.get(i));

            }
        }
        return text;
    }

    /**
     * bu metot ile JS yolu string olarak verilen elementi JavascriptExecutor kullanarak tiklayabilirim
     * @param javascriptYolu click yapilacak webelement yolu string olarak verilir
     */
    public static void clickJSElementWithJavaScript(String javascriptYolu) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        WebElement webElement = (WebElement) jse.executeScript("return " + javascriptYolu + "");
        jse.executeScript("arguments[0].click();", webElement);

    }
    /**
     * Performs double click action on an element
     * @param element
     */
    public static void doubleClick(WebElement element) {
        new Actions(getDriver()).doubleClick(element).build().perform();
    }

    /**
     * click yapilacak element clickible olana kadar bekler
     * @param element elementin locati
     * @param timeout beklenecek sure
     * @return
     */
    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /** bu metot gorunmez ozellikli elementin gorunur olmasini saglar
     * @param element locate alinip ustunde islem yapilmak istenen element
     */
    public static void setElementVisible(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("arguments[0].style.opacity='1';", element);
    }
    /**
     * javascript ile bir element üstünde sağ klik yapip acilan menüde "bağlantıyı yeni sekmede aç" seçeneğini seçer
     * @param element yerine üstünde sağ klik yapilacak elementin locate verilir
     */
    public static void contextClickByJavascript( WebElement element){
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) getDriver();
        String script= "var element = arguments[0];" +
                "var evt= new MouseEvent('contextmenu', { bubbles: true, cancelable: true, view:window});"+
                "element.dispatchEvent(evt);"+
                "window.open(element.href, '_blanck');";

        javascriptExecutor.executeScript(script, element );
    }

    /**
     * bu metot ile bir text kutusuna enter butonu gönderilir
     * @param element içinde enter yapilacak kutus locate verilmeli
     */
    public static void enterByJavascript( WebElement element){
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("arguments[0].dispatchEvent(new KeyboardEvent('keydown', {'key': 'Enter'}));", element);

    }
    /**
     *bu metot boot strap ile gömülü gelen mesajdaki texti alarak return yapar
     * @param field prompt mesajı çıkacak olan elementin locati verilir
     */
    public static String assertPromptMessageWithBootStrap(WebElement field) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();

        if (!(Boolean) jsExecutor.executeScript("return arguments[0].validity.valid;", field)) {
            return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", field);
        }

        return "Field is valid"; // Geçerliyse boş bir mesaj ya da istediğiniz bir başka mesaj
    }

    /**
     * Bu metot ile txt dosyasına istenen bilgileri alt alta yazdırıp kaydedersiniz
     * @param fileName üztüne veri yazılacak txt dosyasının yolu
     * @param list dosyaya yazdırılacak veri
     */
    public static void saveDataInTxtFile(String fileName, List<String> list)  {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            for (String str : list) {
                writer.append(str);
                writer.newLine(); // yeni satır ekleme
            }
            writer.close();
        } catch(Exception e){
            // hata durumunu işle
            e.printStackTrace();
        }
    }

    /**
     * dosyaya yazılan veriyi okur. Java 21 de calismaz. java 11 de calisir
     * @param fileName okunacak dosya yolu
     * @return
     */
    public static List<String> readDataFromTxtFile(String fileName) {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    /**
     * bu metot locate verilen elementin arka plan rengini String olarak verir
     * @param element arka plan rengi alinacak elementin locate verilir
     * @return
     */
    public static String  getBackgroundColor(WebElement element){
        String rgbColor=element.getCssValue("background-color");

        Color color= Color.fromString(rgbColor);
        String actualRgbColor= color.asHex();
        return actualRgbColor;

    }

    /**
     * Bu metot ile bir txt dosyasinda ; ile ayrilmis datalar istenen sarta gore okunarak kutuya gonderilir
     * @param filePath okunacak dosya yolu
     * @param row sart olusturacak durum int olarak verilir. array de ilk sırayı alır
     * @param number arrayde kacinci elemanin alinacagi int olarak verilir
     * @return
     */
    public static String readTxtDependOnRole(String filePath, int row, int number){
        File data = new File(filePath);
        ArrayList<String> dataList = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(data));
            String line;
            while ((line = br.readLine())!=null){
                  dataList.add(line);
            }
            return dataList.get(row-1).split(";")[number];
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Bu metot bir elementin üstüne mause gidince tıklanabilir durumda ise boolean olarak true döndürür
     * Mause element üstüne gidince arrow durumu (default) tıklama işlemi için pointer (el) şekline döner.
     * BU metot ile pointer haline gelen yani tıklanabilir durum için true döner.
     * @param element üstüne gidilecek elementin locate verilir
     */
    public void isPointerChanged(WebElement element){
        // Elementin üzerine gelindiğinde imlecin değiştiğini doğrula
        String expectedCursorStyle = "pointer";
        System.out.println("expectedCursorStyle = " + expectedCursorStyle);
        String actualCursorStyle = element.getCssValue("cursor");
        System.out.println("actualCursorStyle = " + actualCursorStyle);
        Boolean expectedBoolean=true;
        Boolean actualBoolean=null;

        if (expectedCursorStyle.equals(actualCursorStyle)){
            System.out.println("Element clickable ");
            actualBoolean= true;
        }else{
            System.out.println("Element clickable DEĞİL");
            actualBoolean=  false;
        }

        assertEquals(expectedBoolean,actualBoolean);
    }

    /**
     * Bu metot bir elemente tıklama işlemi gerçekleştirir. Ardından da true olarak boolean döndürür.
     * Bu metot ile elementin clickable olup olmadığında kullanmak üzere true olarak boolean döndürür.
     * @param element click yapılacak element locate verilir
     * @return
     */
    public boolean isClickable(WebElement element){
        try {
            // Elemente tıklama işlemini gerçekleştir
            element.click();
            System.out.println("Element tıklama işlemi başarılı oldu.");
            return true;
        } catch (Exception e) {
            System.out.println("Element tıklama işlemi başarısız oldu.");
            return false;
        }
    }
    public static boolean compareImage(BufferedImage image1, BufferedImage image2){
        if(image1.getWidth()!= image2.getWidth() || image1.getHeight()!= image2.getHeight()){
            System.out.println("İki resmin boyutları farklı");
            return false;
        }

        for (int y=0; y< image1.getHeight();y++){
            for (int x=0; x< image1.getWidth();x++){
                int pixel1= image1.getRGB(x,y);
                int pixel2= image2.getRGB(x,y);
                if (pixel1 != pixel2){
                    System.out.println("İki resmin pixelleri farklı");
                    return false;
                }
            }
        }

        return true;
    }
  }
