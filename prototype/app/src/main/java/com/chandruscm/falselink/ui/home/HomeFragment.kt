package com.chandruscm.falselink.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chandruscm.falselink.databinding.FragmentHomeBinding
import com.chandruscm.falselink.di.injector
import com.chandruscm.falselink.di.viewModel
import com.google.android.material.tabs.TabLayoutMediator

/*
 * Show list of white-listed/blocked URLs and other preferences.
 */
class HomeFragment : Fragment() {

    private val viewModel by viewModel {
        requireActivity().injector.homeViewModel
    }

    private val adapter by lazy {
        HomeTabAdapter(this@HomeFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        with (binding) {
            viewModel = this@HomeFragment.viewModel
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = 1
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = adapter.getTabTitle(position)
            }.attach()
        }
        return binding.root
    }
}
