package com.github.yasukotelin.ui_main

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.yasukotelin.ui_main.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_main,
                container,
                false
            ) as FragmentMainBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.userCardState.observe(viewLifecycleOwner, Observer {
            updateUserCardState(it)
        })
    }

    private fun updateUserCardState(state: UserCardState) {
        when (state) {
            UserCardState.OnNormal -> {
                ConstraintSet().apply {
                    this.load(context, R.layout.fragment_main)
                    TransitionManager.beginDelayedTransition(binding.root as ViewGroup)
                    this.applyTo(fragment_main_root)
                    this.applyTo(fragment_main_constraint_in_card)
                }
            }
            UserCardState.OnDetails -> {
                ConstraintSet().apply {
                    this.load(context, R.layout.constset_user_card)
                    TransitionManager.beginDelayedTransition(binding.root as ViewGroup)
                    this.applyTo(fragment_main_root)
                    this.applyTo(fragment_main_constraint_in_card)
                }
            }
        }
    }
}
