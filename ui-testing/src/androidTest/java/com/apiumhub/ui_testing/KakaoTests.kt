package com.apiumhub.ui_testing

import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.agoda.kakao.text.KTextView
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

    @Test
    fun shouldClickNotificationsNavButtonAndAssertText() {
        onScreen<HomeScreen> {
            navigateToNotifications {
                assertNotificationsText("This is notifications Fragment")
            }
        }
    }
}

class HomeScreen : Screen<HomeScreen>() {
    val homeIcon = KView { withId(R.id.navigation_home) }
    val dashboardIcon = KView { withId(R.id.navigation_dashboard) }
    val notificationsIcon = KView { withId(R.id.navigation_notifications) }

    val homeTextView = KTextView { withId(R.id.text_home) }
    val dashboardTextView = KTextView { withId(R.id.text_dashboard) }
    val notificationsTextView = KTextView { withId(R.id.text_notifications) }

    fun navigateToHome(finishWith: (HomeScreen) -> Unit) {
        homeIcon { click() }
        finishWith(this)
    }

    fun navigateToDashboard(finishWith: (HomeScreen) -> Unit) {
        dashboardIcon { click() }
        finishWith(this)
    }

    fun navigateToNotifications(finishWith: (HomeScreen) -> Unit) {
        notificationsIcon { click() }
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

    fun assertNotificationsText(text: String) {
        notificationsTextView {
            hasText(text)
        }
    }
}