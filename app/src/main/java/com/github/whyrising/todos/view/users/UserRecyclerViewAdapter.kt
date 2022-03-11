package com.github.whyrising.todos.view.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.whyrising.todos.databinding.UserItemBinding
import com.github.whyrising.todos.presentation.User

private class UserDiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.username == newItem.username
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

class UserAdapter : ListAdapter<User, UserAdapter.ViewHolder>(
    UserDiffCallback()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        UserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(
        private val binding: UserItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Bind a [User] item to UI through [UserItemBinding].
         * @param user item to bind.
         */
        fun bind(user: User) {
            binding.also {
                it.name.text = user.name
                it.username.text = user.username
                it.email.text = user.email
            }
        }
    }
}
