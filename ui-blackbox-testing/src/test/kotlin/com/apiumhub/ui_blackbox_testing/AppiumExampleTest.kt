package com.apiumhub.ui_blackbox_testing

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.TimeUnit

class AppiumExampleTest {
    private lateinit var driver: AndroidDriver<AndroidElement>

    @Before
    fun setup() {
        val capabilities = DesiredCapabilities()
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "android-emulator")
        capabilities.setCapability("appPackage", "com.apiumhub.ui_testing")
        capabilities.setCapability("appActivity", ".MainActivity")

        try {
            driver = AndroidDriver(URL("http://127.0.0.1:4723/wd/hub"), capabilities)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS)
    }

    @Test
    fun `Navigate to home and assert text is displayed in home fragment`() {
        val homeIcon = driver.findElementById("navigation_home")
        homeIcon.click()
        val textView = driver.findElementById("text_home")
        Assert.assertEquals("This is home Fragment", textView.text)
    }

    @Test
    fun `Navigate to dashboard and assert text is displayed`() {
        val dashboardIcon = driver.findElementById("navigation_dashboard")
        dashboardIcon.click()
        val textView = driver.findElementById("text_dashboard")
        Assert.assertEquals("This is dashboard Fragment", textView.text)
    }

    @After
    fun tearDown() {
        driver.quit()
    }

}