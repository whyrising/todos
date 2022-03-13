package com.github.whyrising.todos.view.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import androidx.slidingpanelayout.widget.SlidingPaneLayout.LOCK_MODE_LOCKED
import com.github.whyrising.todos.R
import com.github.whyrising.todos.UserTodosGraphDirections
import com.github.whyrising.todos.databinding.FragmentUsersListBinding
import com.github.whyrising.todos.presentation.UsersViewModel
import com.github.whyrising.todos.view.VmFactory
import kotlinx.coroutines.launch

class UsersFragment : Fragment() {
    private val vm: UsersViewModel by activityViewModels {
        VmFactory(requireContext())
    }

    private fun navHostFragment(): NavHostFragment {
        return childFragmentManager.findFragmentById(R.id.todos_pane)
            as NavHostFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUsersListBinding.inflate(inflater, container, false)
        .let {
            if (container == null) return@let it.root

            it.slidingPaneLayout.lockMode = LOCK_MODE_LOCKED

            // Navigate when a new user selected.
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    launch {
                        vm.showUserTodosEvents.collect { (userId, flag) ->
                            if (flag) {
                                val to =
                                    UserTodosGraphDirections.toUserTodos(userId)
                                navHostFragment().navController.navigate(to)
                            }
                            it.slidingPaneLayout.open()
                        }
                    }
                }
            }

            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                TwoPaneOnBackPressedCallback(it.slidingPaneLayout)
            )

            val adapter = UserAdapter { user ->
                vm.showUserTodos(userId = user.id)
            }
            it.usersList.adapter = adapter
            vm.users.observe(viewLifecycleOwner) { users ->
                adapter.submitList(users)
            }

            it.root
        }
}

class TwoPaneOnBackPressedCallback(
    private val slidingPaneLayout: SlidingPaneLayout
) : OnBackPressedCallback(
    slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen
),
    SlidingPaneLayout.PanelSlideListener {
    init {
        slidingPaneLayout.addPanelSlideListener(this)
    }

    override fun handleOnBackPressed() {
        slidingPaneLayout.closePane()
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) {}

    override fun onPanelOpened(panel: View) {
        isEnabled = true
    }

    override fun onPanelClosed(panel: View) {
        isEnabled = false
    }
}
