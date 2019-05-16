package com.apiumhub.androidarch.lesson_6.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apiumhub.androidarch.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_row.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.UserViewHolder>() {

    private var users: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.user_row,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) {
        viewHolder.bind(users[position])
    }

    fun update(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(user: User) {
            Glide.with(view).load(user.avatar).into(view.userAvatarIv)
            view.userNameTv.text = user.alias
            view.userIdTv.text = user.id
        }
    }
}