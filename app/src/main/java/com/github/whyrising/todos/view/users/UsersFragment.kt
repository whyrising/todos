package com.github.whyrising.todos.view.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.whyrising.todos.databinding.FragmentUsersListBinding
import com.github.whyrising.todos.presentation.UsersViewModel
import com.github.whyrising.todos.view.VmFactory

class UsersFragment : Fragment() {
    private val vm: UsersViewModel by activityViewModels { VmFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUsersListBinding.inflate(inflater, container, false).let {
        when (container) {
            null -> it.root
            else -> {
                val adapter = UserAdapter()
                it.usersList.adapter = adapter
                vm.users.observe(viewLifecycleOwner) { users ->
                    adapter.submitList(users)
                }
                it.root
            }
        }
    }
}
