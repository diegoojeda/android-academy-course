package com.apiumhub.androidarch.lesson_3.mvvm

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.apiumhub.androidarch.R
import com.apiumhub.androidarch.lesson_3.common.MainAdapter
import com.apiumhub.androidarch.lesson_3.common.User
import kotlinx.android.synthetic.main.home_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ViewModelActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private val adapter = MainAdapter()
    private lateinit var viewModel: ViewModelSolution

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)
        viewModel = ViewModelProviders.of(this).get(ViewModelSolution::class.java)
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRetryBtn.setOnClickListener { start() }
        viewModel.users.observe(this, onEventReceived())
        start()
    }

    private fun onEventReceived() = Observer<ViewModelEvent> {
        when (it) {
            is ViewModelEvent.Loading -> showLoading()
            is ViewModelEvent.Success -> showData(it.data)
            is ViewModelEvent.Error -> showError()
        }
    }

    private fun showData(data: List<User>) {
        mainLoading.visibility = View.GONE
        adapter.update(data)
    }

    private fun showLoading() {
        mainLoading.visibility = View.VISIBLE
    }

    private fun showError() {
        mainLoading.visibility = View.GONE
        mainErrorTv.visibility = View.VISIBLE
    }

    private fun start() {
        showLoading()
        launch {
            viewModel.start()
        }
    }
}