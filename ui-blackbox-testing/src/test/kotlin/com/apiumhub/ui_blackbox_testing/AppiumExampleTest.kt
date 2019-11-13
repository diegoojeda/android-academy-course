package com.apiumhub.ui_blackbox_testing

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.remote.MobileCapabilityType
import junit.framework.TestCase.*
import org.junit.*
import org.openqa.selenium.By
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
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9")
        capabilities.setCapability("appPackage", "com.apiumhub.ui_testing")
        capabilities.setCapability("appActivity", ".lesson_6.MainActivity")
        capabilities.setCapability("automationName", "UiAutomator2")

        try {
            driver = AndroidDriver(URL("http://127.0.0.1:4723/wd/hub"), capabilities)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)
    }

    @Test
    fun `Navigate to home and assert text is displayed in home fragment`() {
        driver.findElement(By.id("navigation_home")).click()
        assertEquals("This is home Fragment", driver.findElement(By.id("text_home")).text)
    }

    @Test
    fun `Navigate to dashboard and assert text is displayed`() {
        driver.findElement(By.id("navigation_dashboard")).click()
        assertEquals("This is dashboard Fragment", driver.findElement(By.id("text_dashboard")).text)
    }

    @Test
    fun `submit button is disabled when email is invalid`() {
        driver.findElement(By.id("navigation_login")).click()
        driver.findElementById("login_username_et").replaceValue("someInvalidEmail")
        assert(!driver.findElementById("login_submit_btn").isEnabled)
    }

    @Test
    fun `submit button is enabled when email and password are both valid`() {
        driver.findElement(By.id("navigation_login")).click()
        driver.findElementById("login_username_et").replaceValue("someValidEmail@domain.com")
        driver.findElementById("login_password_et").replaceValue("123456789")
        val submitBtn = driver.findElementById("login_submit_btn")
        assert(submitBtn.isEnabled)

        submitBtn.click()

        assertEquals("User someValidEmail@domain.com is logged in", driver.findElementById("logged_in_tv").text)
    }

    //region solutions
    @Test
    @Ignore
    fun `Navigate to login and check button is disabled`() {
        navigateToLogin()
        typeEmail("invalid email")
        assertSubmitButtonIsDisabled()
    }

    @Test
    @Ignore
    fun `Navigate to login, type an invalid password and check button is disabled`() {
        navigateToLogin()
        typePassword("1234")
        assertSubmitButtonIsDisabled()
    }

    @Test
    @Ignore
    fun `Navigate to login, type valid username and password, and navigate to next screen`() {
        navigateToLogin()
        typeEmail("someEmail@someDomain.com")
        typePassword("123456")
        val submitButton = driver.findElementById("login_submit_btn")
        Assert.assertTrue(submitButton.isEnabled)
        submitButton.click()
        val usernameLoggedIn = driver.findElementById("logged_in_tv")
        Assert.assertEquals("User someEmail@someDomain.com is logged in", usernameLoggedIn.text)
    }

    private fun typePassword(password: String) {
        val passwordEditText = driver.findElementById("login_password_et")
        passwordEditText.setValue(password)
    }

    private fun typeEmail(email: String) {
        val usernameEditText = driver.findElementById("login_username_et")
        usernameEditText.setValue(email)
    }

    private fun assertSubmitButtonIsDisabled() {
        val submitButton = driver.findElementById("login_submit_btn")
        Assert.assertFalse(submitButton.isEnabled)
    }

    //endregion

    private fun navigateToLogin() {
        val loginIcon = driver.findElementById("navigation_login")
        loginIcon.click()
    }

    @After
    fun tearDown() {
        driver.quit()
    }

}