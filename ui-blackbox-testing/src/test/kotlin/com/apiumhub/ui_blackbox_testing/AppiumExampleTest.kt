package com.apiumhub.ui_blackbox_testing

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.remote.MobileCapabilityType
import junit.framework.TestCase.*
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

    @Test
    fun `submit button is disabled when email is invalid`() {
        navigateToLogin()
        val emailInput = driver.findElementById("login_username_et")
        emailInput.replaceValue("invalidemail")
        val submitBtn = driver.findElementById("login_submit_btn")
        assertFalse(submitBtn.isEnabled)
    }

    @Test
    fun `submit button is enabled when email and password are both valid`() {
        navigateToLogin()
        val emailInput = driver.findElementById("login_username_et")
        emailInput.replaceValue("email@domain.com")
        val passwordInput = driver.findElementById("login_password_et")
        passwordInput.replaceValue("123456")
        val submitBtn = driver.findElementById("login_submit_btn")
        assertTrue(submitBtn.isEnabled)

        submitBtn.click()

        val loggedInTv = driver.findElementById("logged_in_tv")
        assertTrue(loggedInTv.text.contains("email@domain.com"))
    }



    //region solutions
    @Test
    fun `Navigate to login and check button is disabled`() {
        navigateToLogin()
        typeEmail("invalid email")
        assertSubmitButtonIsDisabled()
    }

    @Test
    fun `Navigate to login, type an invalid password and check button is disabled`() {
        navigateToLogin()
        typePassword("1234")
        assertSubmitButtonIsDisabled()
    }

    @Test
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