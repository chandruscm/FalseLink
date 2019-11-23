package com.chandruscm.whatthelink.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chandruscm.whatthelink.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

/*
 * Show list of white-listed/blocked URLs and other preferences.
 */
class HomeFragment : Fragment() {

    private val adapter by lazy {
        WebsiteTabAdapter(this@HomeFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        with (binding) {
            viewPager.adapter = adapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = adapter.getTabTitle(position)
            }.attach()
        }
        return binding.root
    }
}
