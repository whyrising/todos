package com.github.whyrising.todos.view.todos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.whyrising.todos.databinding.TodoItemBinding
import com.github.whyrising.todos.presentation.TodoViewModel

private class TodoDiffCallback : DiffUtil.ItemCallback<TodoViewModel>() {
    override fun areItemsTheSame(
        oldItem: TodoViewModel,
        newItem: TodoViewModel
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: TodoViewModel,
        newItem: TodoViewModel
    ): Boolean = oldItem == newItem
}

class TodoAdapter : ListAdapter<TodoViewModel, TodoAdapter.ViewHolder>(
    TodoDiffCallback()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        TodoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(
        private val binding: TodoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Bind a [TodoViewModel] item to UI through [TodoItemBinding].
         * @param todo item to bind.
         */
        fun bind(todo: TodoViewModel) {
            binding.also {
                it.task.text = todo.title
                it.task.isChecked = todo.isDone
            }
        }
    }
}
