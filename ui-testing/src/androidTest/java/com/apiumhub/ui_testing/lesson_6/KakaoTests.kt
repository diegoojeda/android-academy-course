package com.apiumhub.ui_testing.lesson_6

import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.apiumhub.ui_testing.R
import com.apiumhub.ui_testing.lesson_6.ui.LoggedInActivity
import org.junit.Rule
import org.junit.Test

class KakaoTests {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    private val homeScreen = HomeScreen()
    private val loggedInScreen = LoggedInScreen()

    @Test
    fun shouldClickHomeNavButtonAndAssertText() {
        homeScreen.navigateToHome {
            it.assertHomeText("This is home Fragment")
        }
    }

    @Test
    fun shouldClickDashboardNavButtonAndAssertText() {
        homeScreen.navigateToDashboard {
            it.assertDashboardText("This is dashboard Fragment")
        }
    }

    @Test
    fun submit_button_should_be_disabled_when_email_is_invalid() {
        homeScreen.navigateToLogin {
            it.typeEmail("someInvalidEmail")
            it.checkSubmitButtonDisabled()
        }
    }

    @Test
    fun should_navigate_when_email_and_password_are_valid_and_submit_is_clicked() {
        onScreen<HomeScreen> {
            navigateToLogin {
                typeEmail("someValidEmail@domain.com")
                typePassword("123456789")
                clickSubmit()
            }
        }
        waitUntilActivityVisible<LoggedInActivity>()
        onScreen<LoggedInScreen> {
            checkTvContains("User someValidEmail@domain.com is logged in")
        }
    }

    //region solutions
//
//    @Test
//    fun navigateToLoginTypeInvalidEmailAndCheckButtonIsDisabled() {
//        onScreen<HomeScreen> {
//            navigateToLogin {
//                typeUsername("invalid username")
//                submitBtn.isDisabled()
//            }
//        }
//    }
//
//    @Test
//    fun navigateToLoginTypeInvalidPasswordAndCheckButtonIsDisabled() {
//        onScreen<HomeScreen> {
//            navigateToLogin {
//                typePassword("1234")
//                submitBtn.isDisabled()
//            }
//        }
//    }
//
//    @Test
//    fun navigateToLoginTypeValidCredentialsAndNavigateToNextScreen() {
//        onScreen<HomeScreen> {
//            navigateToLogin {
//                typeUsername("someEmail@someDomain.com")
//                typePassword("123456")
//                submitBtn.isEnabled()
//                submitBtn.click()
//            }
//        }
//        waitUntilActivityVisible<LoggedInActivity>()
//        onScreen<LoggedInScreen> {
//            assertTvShowsUsername("someEmail@someDomain.com")
//        }
//
//    }
    //endregion
}

class HomeScreen : Screen<HomeScreen>() {
    val homeIcon = KView { withId(R.id.navigation_home) }
    val dashboardIcon = KView { withId(R.id.navigation_dashboard) }
    val loginIcon = KView { withId(R.id.navigation_login) }

    val homeTextView = KTextView { withId(R.id.text_home) }
    val dashboardTextView = KTextView { withId(R.id.text_dashboard) }

    val emailEditText = KEditText { withId(R.id.login_username_et) }
    val passwordEditText = KEditText { withId(R.id.login_password_et) }

    val submitBtn = KButton { withId(R.id.login_submit_btn) }

    fun navigateToHome(finishWith: (HomeScreen) -> Unit) {
        homeIcon { click() }
        finishWith(this)
    }

    fun navigateToDashboard(finishWith: (HomeScreen) -> Unit) {
        dashboardIcon { click() }
        finishWith(this)
    }

    fun navigateToLogin(finishWith: (HomeScreen) -> Unit) {
        loginIcon { click() }
        finishWith(this)
    }

    fun typeEmail(email: String) {
        emailEditText.typeText(email)
    }

    fun typePassword(password: String) {
        passwordEditText.typeText(password)
    }

    fun checkSubmitButtonDisabled() {
        submitBtn.isDisabled()
    }

    fun clickSubmit() {
        submitBtn.click()
    }

    fun assertHomeText(text: String) {
        homeTextView {
            hasText(text)
        }
    }

    fun assertDashboardText(text: String) {
        dashboardTextView {
            hasText(text)
        }
    }
}

class LoggedInScreen : Screen<LoggedInScreen>() {
    val userTextView = KTextView { withId(R.id.logged_in_tv) }

    fun checkTvContains(text: String) {
        userTextView.containsText(text)
    }

}