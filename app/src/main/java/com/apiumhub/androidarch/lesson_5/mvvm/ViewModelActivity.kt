package com.apiumhub.androidarch.lesson_5.mvvm

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.apiumhub.androidarch.R
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import com.apiumhub.androidarch.lesson_5.common.MainAdapter
import kotlinx.android.synthetic.main.home_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class ViewModelActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private val adapter = MainAdapter()
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRetryBtn.setOnClickListener { viewModel.retry() }
        viewModel.users.observe(this, onEventReceived())
    }

    private fun onEventReceived() = Observer<ViewModelEvent> {
        when (it) {
            is ViewModelEvent.Loading -> showLoading()
            is ViewModelEvent.Success -> showData(it.data)
            is ViewModelEvent.Error -> showError()
        }
    }

    private fun showData(data: List<User>) {
        mainErrorTv.visibility = View.GONE
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
}