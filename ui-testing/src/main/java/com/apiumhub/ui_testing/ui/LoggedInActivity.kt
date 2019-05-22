package com.apiumhub.ui_testing.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apiumhub.ui_testing.R
import kotlinx.android.synthetic.main.activity_logged_in.*
import java.lang.IllegalArgumentException

class LoggedInActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        intent?.extras?.getString(EMAIL_KEY)?.let {
            logged_in_tv.text = "User $it is logged in"
        } ?: throw IllegalArgumentException("Activity started with invalid parameters")
    }

    companion object{
        fun getCallingIntent(context: Context?, email: String) : Intent =
                Intent(context, LoggedInActivity::class.java).putExtra(EMAIL_KEY, email)

        private const val EMAIL_KEY = "emailKey"
    }
}