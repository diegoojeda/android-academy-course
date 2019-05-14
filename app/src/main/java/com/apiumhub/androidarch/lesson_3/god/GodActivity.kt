package com.apiumhub.androidarch.lesson_3.god

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.apiumhub.androidarch.R
import com.apiumhub.androidarch.lesson_3.common.DatabaseClient
import com.apiumhub.androidarch.lesson_3.common.MainAdapter
import com.apiumhub.androidarch.lesson_3.common.NetworkClient
import com.apiumhub.androidarch.lesson_3.common.User
import kotlinx.android.synthetic.main.home_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.random.Random

class GodActivity : AppCompatActivity(), CoroutineScope by MainScope() {
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
                    val noConnection: (Error) -> Unit = {
                        mainErrorTv.visibility = View.VISIBLE
                    }
                    val hasConnection: (List<User>) -> Unit = {
                        databaseClient.storeUsers(it)
                        adapter.update(it)
                        mainRecyclerView.visibility = View.VISIBLE
                    }
                    networkClient.getUsers().fold(
                        noConnection,
                        hasConnection)
                } else {
                    mainErrorTv.visibility = View.VISIBLE
                }
            }, {
                adapter.update(it)
            })
            mainLoading.visibility = View.GONE
        }
    }

    //Returns false 10% of the times
    private fun hasInternetConnection() = Random.nextInt().absoluteValue % 100 > 10

}