package com.apiumhub.androidarch.lesson_3.god

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apiumhub.androidarch.R
import com.apiumhub.androidarch.lesson_3.common.*
import kotlinx.android.synthetic.main.home_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class GodActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private val networkClient = NetworkClient()
    private val databaseClient = DatabaseClient()
    private val connectionProvider = ConnectionProvider()

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
            databaseClient.getUsers().fold({
                if (connectionProvider.hasNetworkConnection()) {
                    val noConnection: (Error) -> Unit = {
                        mainErrorTv.visibility = View.VISIBLE
                    }
                    val hasConnection: (List<User>) -> Unit = {
                        databaseClient.storeUsers(it)
                        adapter.update(it)
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

}