package com.github.whyrising.todos.view.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.whyrising.todos.databinding.UserItemBinding
import com.github.whyrising.todos.presentation.UserViewModel

private class UserDiffCallback : DiffUtil.ItemCallback<UserViewModel>() {
    override fun areItemsTheSame(
        oldItem: UserViewModel,
        newItem: UserViewModel
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: UserViewModel,
        newItem: UserViewModel
    ): Boolean = oldItem == newItem
}

class UserAdapter : ListAdapter<UserViewModel, UserAdapter.ViewHolder>(
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
         * Bind a [UserViewModel] item to UI through [UserItemBinding].
         * @param user item to bind.
         */
        fun bind(user: UserViewModel) {
            binding.also {
                it.name.text = user.fullName
                it.username.text = user.username
                it.email.text = user.email
            }
        }
    }
}
