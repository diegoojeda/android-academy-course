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

    @Test
    fun shouldClickHomeNavButtonAndAssertText() {
        onScreen<HomeScreen> {
            navigateToHome {
                assertHomeText("This is home Fragment")
            }
        }
    }

    @Test
    fun shouldClickDashboardNavButtonAndAssertText() {
        onScreen<HomeScreen> {
            navigateToDashboard {
                assertDashboardText("This is dashboard Fragment")
            }
        }
    }


    //region solutions

    @Test
    fun navigateToLoginTypeInvalidEmailAndCheckButtonIsDisabled() {
        onScreen<HomeScreen> {
            navigateToLogin {
                typeUsername("invalid username")
                submitBtn.isDisabled()
            }
        }
    }

    @Test
    fun navigateToLoginTypeInvalidPasswordAndCheckButtonIsDisabled() {
        onScreen<HomeScreen> {
            navigateToLogin {
                typePassword("1234")
                submitBtn.isDisabled()
            }
        }
    }

    @Test
    fun navigateToLoginTypeValidCredentialsAndNavigateToNextScreen() {
        onScreen<HomeScreen> {
            navigateToLogin {
                typeUsername("someEmail@someDomain.com")
                typePassword("123456")
                submitBtn.isEnabled()
                submitBtn.click()
            }
        }
        waitUntilActivityVisible<LoggedInActivity>()
        onScreen<LoggedInScreen> {
            assertTvShowsUsername("someEmail@someDomain.com")
        }

    }
    //endregion
}

class HomeScreen : Screen<HomeScreen>() {
    val homeIcon = KView { withId(R.id.navigation_home) }
    val dashboardIcon = KView { withId(R.id.navigation_dashboard) }
    val loginIcon = KView {withId(R.id.navigation_login)}

    val homeTextView = KTextView { withId(R.id.text_home) }
    val dashboardTextView = KTextView { withId(R.id.text_dashboard) }

    private val usernameField = KEditText {withId(R.id.login_username_et)}
    private val passwordField = KEditText {withId(R.id.login_password_et)}
    val submitBtn = KButton {withId(R.id.login_submit_btn)}

    fun navigateToHome(finishWith: (HomeScreen) -> Unit) {
        homeIcon { click() }
        finishWith(this)
    }

    fun navigateToDashboard(finishWith: (HomeScreen) -> Unit) {
        dashboardIcon { click() }
        finishWith(this)
    }

    fun navigateToLogin(finishWith: (HomeScreen) -> Unit) {
        loginIcon {click()}
        finishWith(this)
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

    fun typeUsername(username: String) {
        usernameField.replaceText(username)
    }

    fun typePassword(password: String) {
        passwordField.replaceText(password)
    }

}

class LoggedInScreen: Screen<LoggedInScreen>() {
    val infoTv = KTextView {withId(R.id.logged_in_tv)}

    fun assertTvShowsUsername(username: String) {
        infoTv {
            hasText("User $username is logged in")
        }
    }
}