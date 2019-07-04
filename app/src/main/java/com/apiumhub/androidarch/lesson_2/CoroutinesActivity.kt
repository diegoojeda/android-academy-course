package com.apiumhub.androidarch.lesson_2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import androidx.lifecycle.whenStarted
import com.apiumhub.androidarch.R
import kotlinx.android.synthetic.main.activity_coroutines.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutinesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        Log.d("Coroutines", "This will run at the very beginning")
        lifecycleScope.launch {
            whenStarted {
                delay(1000)
                Log.d("Coroutines", "This will only run when activity is started")
            }
            Log.d("Coroutines", "This will run after activity's onStart")
            whenResumed {
                delay(1000)
                Log.d("Coroutines", "This will run when activity is resumed")
            }
            Log.d("Coroutines", "This will run after activity's onResume")
//            updateUI()
        }
        //TODO Try to move this code inside the constructor!
    }

    private fun updateUI() {
        coroutinesTv.text = "Hello world!"
    }
}