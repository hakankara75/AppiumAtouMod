package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;
import static utilities.Driver.getDriver;

public class Xpath_contains {

    @Test
    public void xpath() {

        getDriver().get("https://www.tema.org.tr/anasayfa");

        WebElement xpath =getDriver().findElement(By.xpath("(//div[@class='text font'])[1]"));


        assertTrue(xpath.isDisplayed());

        getDriver().quit();

    }

    @Test
    public void contains() {
        getDriver().get("https://www.tema.org.tr/anasayfa");

        WebElement xpath =getDriver().findElement(By.xpath("//a[contains(@href,'anasay')]"));

        assertTrue(xpath.isDisplayed());

        getDriver().quit();
    }
}
