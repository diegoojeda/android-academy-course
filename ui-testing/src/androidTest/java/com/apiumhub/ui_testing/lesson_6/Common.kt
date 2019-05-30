package com.apiumhub.ui_testing.lesson_6

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import androidx.test.core.app.ApplicationProvider


inline fun <reified T : Activity> waitUntilActivityVisible() {
    val startTime = System.currentTimeMillis()
    while (!isVisible<T>()) {
        Thread.sleep(CONDITION_CHECK_INTERVAL)
        if (System.currentTimeMillis() - startTime >= TIMEOUT) {
            throw AssertionError("Activity ${T::class.java.simpleName} not visible after $TIMEOUT milliseconds")
        }
    }
}

inline fun <reified T : Activity> isVisible() : Boolean {
    val am = ApplicationProvider.getApplicationContext<Context>().getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val visibleActivityName = am.appTasks[0].taskInfo.topActivity.className
    println("ACTIVITY: $visibleActivityName")
    return visibleActivityName == T::class.java.name
}

const val TIMEOUT = 5000L
const val CONDITION_CHECK_INTERVAL = 100L
