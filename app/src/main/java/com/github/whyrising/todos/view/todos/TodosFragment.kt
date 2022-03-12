package com.github.whyrising.todos.view.todos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.github.whyrising.todos.databinding.FragmentTodosListBinding
import com.github.whyrising.todos.presentation.UsersViewModel
import com.github.whyrising.todos.view.VmFactory

class TodosFragment : Fragment() {
    private val vm: UsersViewModel by activityViewModels { VmFactory }
    private val args: TodosFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentTodosListBinding.inflate(inflater, container, false)
            .let {
                if (container != null) {
                    vm.fetchTodos(args.userId)
                    
                    val adapter = TodoAdapter()
                    it.todosList.adapter = adapter
                    vm.userTodos.observe(viewLifecycleOwner) { todos ->
                        Log.i("Todos#${args.userId}", "$todos")
                        adapter.submitList(todos)
                    }
                }
                it.root
            }
    }
}
