package com.apiumhub.androidarch.lesson_5.god

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apiumhub.androidarch.AppDb
import com.apiumhub.androidarch.R
import com.apiumhub.androidarch.lesson_4.data.db.UsersRoomRepository
import com.apiumhub.androidarch.lesson_4.data.network.UsersApi
import com.apiumhub.androidarch.lesson_4.data.network.UsersRetrofitRepository
import com.apiumhub.androidarch.lesson_4.domain.GetUsers
import com.apiumhub.androidarch.lesson_4.domain.exception.NoInternetConnectionException
import com.apiumhub.androidarch.lesson_5.common.MainAdapter
import kotlinx.android.synthetic.main.home_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class GodActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    /*
    Luckily, we are using a UseCase here. But many times (we've all seen that) we'll find that on GodActivities the
    network and database clients are used directly, which makes us implement a lot of business logic inside the activity
     */
    private val getUsers = GetUsers(
        UsersRetrofitRepository(UsersApi.create()),
        UsersRoomRepository(AppDb.getDb().userDao())
    )

    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRetryBtn.setOnClickListener { getData() }
        getData()
    }

    private fun getData() {
        mainLoading.visibility = View.VISIBLE
        launch {
            getUsers.execute().fold({
                when(it) {
                    is NoInternetConnectionException -> noInternetError()
                    else -> unknownError()
                }
            },{
                adapter.update(it)
            })

            mainLoading.visibility = View.GONE
        }
    }

    private fun noInternetError() {
        mainErrorTv.visibility = View.VISIBLE
        mainErrorTv.text = "No internet connection \\n Please try again later!"
    }

    private fun unknownError() {
        mainErrorTv.visibility = View.VISIBLE
        mainErrorTv.text = "Oops, something went wrong! \\n Please try again later!"
    }

}