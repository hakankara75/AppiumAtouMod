package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.Driver;

import java.net.MalformedURLException;
import java.util.*;

import static utilities.ReusableMethods.getApkForTags;
import static utils.Driver.quitDriver;


public class Hooks {




    @Before()
    public static String setUpMobile() throws MalformedURLException, InterruptedException {
        Scenario scenario = null;
        List<String> tags = (List<String>) scenario.getSourceTagNames();
        String driver="";

        List<String> exeptList = new ArrayList<>();
        exeptList.add("@webdriver");
        exeptList.add("@appiumdriver");

        boolean conclusionBoolean= getApkForTags(tags, exeptList);
        if (conclusionBoolean==true){

        }

        return driver;
    }

    @After()
    public void tearDownMobile(Scenario scenario) throws MalformedURLException, InterruptedException {
        if (scenario.isFailed()) {
            byte[] screenshot;
            WebDriver driver = Driver.getAppiumDriver();
            if (driver != null && driver instanceof TakesScreenshot) {
                screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } else {
                screenshot = new byte[0];
            }
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        quitDriver();
    }
}