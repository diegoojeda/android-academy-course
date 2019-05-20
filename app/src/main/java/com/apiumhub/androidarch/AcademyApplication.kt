package com.apiumhub.androidarch

import android.app.Application

class AcademyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDb.initializeDb(this)
    }
}