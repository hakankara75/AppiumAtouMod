package stepdef;

import org.testng.annotations.Test;
import pages.OtelZ_Page;
import utilities.ReusableMethods;

import java.util.Random;

import static utilities.Driver.getDriver;

public class OtelZ_StepDef {
    OtelZ_Page page=new OtelZ_Page();


    @Test
    public void otelZ() {

//tanıtım sayfasında 2 kere sağa kaydırılır
        for (int i = 0; i <=1 ; i++) {
            ReusableMethods.swipe(getDriver(),page.tanitimSayfasi,"left", 1,1000);

        }

//otel sekmesine Ankara girilir
        page.otel.click();
        page.otelText.sendKeys("Ankara");

//ilk seçenek seçilir
        page.otelSecenekler.get(0).click();

//gidiş tarihi 2 gün sonra olarak belirlenir
        //startdate ten text alınır
        String date = page.startDate.getText();

        //textteki rakam dışındaki her şey silinir ve kalan sayı int olarak atanır
        int firstDate = Integer.parseInt(date.replaceAll("[^0-9]", ""));

        //startdate kutusu kliklenir
        page.startDate.click();

        //o günün tarihine 2 eklenir
        int startDate = firstDate+2;

        //ay görünümünden tarihler liste olarak alınır ve startDate'e eşit olan tıklanır
        for (int i = 0; i <  page.startDateNumber.size(); i++) {

             Integer selectDate= Integer.valueOf(page.startDateNumber.get(i).getText());
            System.out.println("selectDate = " + selectDate);
            if (selectDate.equals(startDate)){
                page.startDateNumber.get(i).click();

            }
            break;
        }

        //checkout kutusu tıklanır
        page.checkoutNumber.click();

        //seçilen startdate' 2 gün eklenir
        int endDate= startDate+2;
        //ay görünümünden tarihler liste olarak alınır ve startDate'e eşit olan tıklanır
        for (int i = 0; i <  page.startDateNumber.size(); i++) {

            Integer selectDate= Integer.valueOf(page.startDateNumber.get(i).getText());
            System.out.println("selectDate = " + selectDate);
            if (selectDate.equals(endDate)){
                page.startDateNumber.get(i).click();

            }
            break;
        }

        page.apply.click();
//arama butonu tıklanır
       page.arama.click();

//Arama sonuç sayfasında üste yer alan "Harita" butonuna tıklanır
        page.map.click();

        //1 ile 3 arasında bir random sayı belirle
        Random random = new Random();

        // 0,1 ve 2 arasında rastgele bir sayı üret
        int randomNumber = random.nextInt(3) ;

        // Üretilen sayıyı yazdır
        System.out.println(randomNumber);

//rastgele bir pine tıklanıp tesis kartının açılması sağlanır
        page.otels.get(randomNumber).click();








    }
}
