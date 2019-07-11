package com.apiumhub.androidarch.lesson_8

import androidx.room.Room
import com.apiumhub.androidarch.AppDatabase
import com.apiumhub.androidarch.DATA_BASE_NAME
import com.apiumhub.androidarch.lesson_4.data.db.UsersRoomRepository
import com.apiumhub.androidarch.lesson_4.data.network.UsersApi
import com.apiumhub.androidarch.lesson_4.data.network.UsersRetrofitRepository
import com.apiumhub.androidarch.lesson_4.domain.GetUsers
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit
            .Builder()
            .baseUrl("https://apiumacademy.serveo.net")
            .client(get())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        OkHttpClient.Builder().build()
    }
}

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DATA_BASE_NAME
        ).build()
    }
}

val viewModelModule = module {
    viewModel {
        KoinViewModel(get())
    }
    single {
        GetUsers(get(), get())
    }
}

val apisModule = module {
    single {
        get<Retrofit>().create(UsersApi::class.java)
    }
}

val repositoriesModule = module {
    single {
        UsersRoomRepository(get<AppDatabase>().userDao())
    }
    single {
        UsersRetrofitRepository(get())
    }
}