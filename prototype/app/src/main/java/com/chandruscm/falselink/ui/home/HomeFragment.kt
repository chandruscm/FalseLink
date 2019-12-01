/*
 * Copyright 2019 Chandramohan Sudar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
