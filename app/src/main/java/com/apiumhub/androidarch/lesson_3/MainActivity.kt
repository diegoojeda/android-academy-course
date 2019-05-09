package com.apiumhub.androidarch.lesson_3

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.apiumhub.androidarch.R
import kotlinx.android.synthetic.main.home_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private val networkClient = NetworkClient()
    private val databaseClient = DatabaseClient()

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
        mainErrorTv.visibility = View.GONE
        launch {
            databaseClient.getUsers().fold({
                if (hasInternetConnection()) {
                    networkClient.getUsers().fold({
                        mainErrorTv.visibility = View.VISIBLE
                    }, {
                        databaseClient.storeUsers(it)
                        adapter.update(it)
                        mainRecyclerView.visibility = View.VISIBLE
                    })
                } else {
                    mainErrorTv.visibility = View.VISIBLE
                }
            }, {
                adapter.update(it)
            })
            mainLoading.visibility = View.GONE
        }
    }

    //Returns false 30% of the times
    private fun hasInternetConnection() = Random.nextInt() % 100 > 30

}