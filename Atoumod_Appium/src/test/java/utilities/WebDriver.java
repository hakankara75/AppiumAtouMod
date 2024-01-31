package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.time.Duration;

public class WebDriver {

    private WebDriver() {
    }

    private static UiAutomator2Options options;
    private static XCUITestOptions iosOptions;

    private static AndroidDriver driver;


    public static AndroidDriver webDriver() {

        if (driver == null) {
            switch (ConfigReader.getProperty("platformName")) {
                case "Android":
                    options = new UiAutomator2Options()
  .withBrowserName("chrome");  //web testleri yaparken bu acik olacak

                    try {
                        driver = new AndroidDriver(
                                new URL("http://127.0.0.1:4723"), options
                        );
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }

                    break;

                case "IOS":
                    iosOptions = new XCUITestOptions();
                    iosOptions.XCODE_ORG_ID_OPTION.contains("hakan.tetik@......com");
                    iosOptions.setBundleId("com.atoumod")
                            .setDeviceName("iPhone SE")
                            .setPlatformVersion("16.6")
                            .setUdid("U99693797933")
                            .setAutomationName("XCUITest")
                            .setNoReset(false)
                            .setNewCommandTimeout(Duration.ofMinutes(10));


                    try {
                        driver = new AndroidDriver(
                                new URL("http://127.0.0.1:4723"), iosOptions
                        );
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;


            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
        //pressKey() kullanabilmek icin Driver'da kullanilan class AndroidDriver olmali. AppiumDriver'da kullanilamaz.
        //Bunun icin Driver da return driver kismi cast ile      return (AndroidDriver) driver; yaptim.
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;

        }
    }

    public static boolean isAppiumServerRunning(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
