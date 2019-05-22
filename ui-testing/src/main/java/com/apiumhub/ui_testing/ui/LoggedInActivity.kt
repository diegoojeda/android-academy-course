package com.apiumhub.ui_testing.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apiumhub.ui_testing.R
import kotlinx.android.synthetic.main.activity_logged_in.*

class LoggedInActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        logged_in_tv.text = "User is logged in"
    }
}