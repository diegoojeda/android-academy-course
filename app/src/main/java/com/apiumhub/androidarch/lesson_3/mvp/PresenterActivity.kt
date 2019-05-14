package com.apiumhub.androidarch.lesson_3.mvp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apiumhub.androidarch.R
import com.apiumhub.androidarch.lesson_3.common.DatabaseClient
import com.apiumhub.androidarch.lesson_3.common.MainAdapter
import com.apiumhub.androidarch.lesson_3.common.NetworkClient
import com.apiumhub.androidarch.lesson_3.common.User
import kotlinx.android.synthetic.main.home_layout.*

class PresenterActivity: AppCompatActivity(), Presenter.Contract {
    private val presenter = Presenter(
        NetworkClient(),
        DatabaseClient()
    )

    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.home_layout)
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRetryBtn.setOnClickListener { presenter.start() }
        presenter.start()
    }

    override fun onDataLoaded(users: List<User>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNoNetworkConnectionError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}