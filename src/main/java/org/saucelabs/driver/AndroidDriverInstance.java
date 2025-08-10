package org.saucelabs.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Platform;
import org.saucelabs.constants.Constants;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidDriverInstance {

    public AndroidDriver initialize() throws IOException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(Platform.ANDROID.toString())
                .setDeviceName("Android")
                .setUdid("emulator-5554")
                .setApp(Constants.APK)
                .setAutomationName("UIAutomator2")
                .setNewCommandTimeout(Duration.ofSeconds(120))
                .autoGrantPermissions()
                .setAppPackage("com.saucelabs.mydemoapp.android")
                .setAppWaitPackage("com.saucelabs.mydemoapp.android")
                .setAppWaitActivity("com.*");

        AndroidDriver androidDriver = fillAndroidDriver(options);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return androidDriver;
    }

    private AndroidDriver fillAndroidDriver(UiAutomator2Options caps) throws MalformedURLException {
        AndroidDriver androidDriver;
        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
        return androidDriver;
    }
}
