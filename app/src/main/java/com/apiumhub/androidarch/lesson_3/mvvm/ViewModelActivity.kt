package com.apiumhub.androidarch.lesson_3.mvvm

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apiumhub.androidarch.R
import com.apiumhub.androidarch.lesson_3.common.MainAdapter
import com.apiumhub.androidarch.lesson_3.common.User
import kotlinx.android.synthetic.main.home_layout.*

class ViewModelActivity : AppCompatActivity() {

    private val adapter = MainAdapter()
    private val viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRetryBtn.setOnClickListener { viewModel.start() }
        viewModel.users.observe(this, Observer<List<User>> { users ->
            users?.let {
                adapter.update(it)
            }
        })
        viewModel.start()
    }
}