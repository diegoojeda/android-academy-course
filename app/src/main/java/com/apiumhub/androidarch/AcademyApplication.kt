package com.apiumhub.androidarch

import android.app.Application
import com.apiumhub.androidarch.lesson_8.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AcademyApplication : Application() {

    private val modules = listOf(
        networkModule,
        roomModule,
        viewModelModule,
        apisModule,
        repositoriesModule
    )

    override fun onCreate() {
        super.onCreate()
        AppDb.initializeDb(this)
        //Uncomment for lesson 8
        startKoin {
            androidContext(this@AcademyApplication)
            modules(modules)
        }
    }
}