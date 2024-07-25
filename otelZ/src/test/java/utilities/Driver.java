package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static utilities.ConfigReader.getProperty;

public class Driver {

    private Driver() {
    }

        public static AndroidDriver driver;

        public static AndroidDriver getDriver() {
            if (driver == null) {
                String appUrl = System.getProperty("user.dir")
                        + File.separator + "src"
                        + File.separator + "test"
                        + File.separator + "resources"
                        + File.separator + ConfigReader.getProperty("apkName");

                UiAutomator2Options options = new UiAutomator2Options()
                        .setApp(appUrl)
                        .setDeviceName(ConfigReader.getProperty("deviceName"))
                        .setNoReset(true)
                        .setNewCommandTimeout(Duration.ofMinutes(10));

                try {
                    driver = new AndroidDriver(
                            new URL("http://127.0.0.1:4723"), options
                    );
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }


                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            }
            return driver;
        }

    }

