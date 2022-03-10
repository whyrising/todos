package com.github.whyrising.todos.view.users

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.whyrising.todos.databinding.UserItemBinding
import com.github.whyrising.todos.vm.User

class UserRecyclerViewAdapter(
    private val users: List<User>
) : RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (name, username, email) = users[position]
        holder.name.text = name
        holder.username.text = username
        holder.email.text = email
    }

    override fun getItemCount(): Int = users.size

    inner class ViewHolder(
        binding: UserItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val username: TextView = binding.username
        val email: TextView = binding.email

        override fun toString(): String = "${super.toString()} ${name.text}"
    }
}
