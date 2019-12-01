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

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chandruscm.falselink.R
import com.chandruscm.falselink.ui.website.WebsiteTabFragment
import com.chandruscm.falselink.utils.COUNT_WEBSITE_FRAGMENTS
import com.chandruscm.falselink.utils.TAB_WEBSITE_FRAGMENT_BLOCKED
import com.chandruscm.falselink.utils.TAB_WEBSITE_FRAGMENT_SAFE

class HomeTabAdapter(
    private val fragment: Fragment
) : FragmentStateAdapter(fragment) {

    companion object {
        private val TAB_TITLES = arrayOf(
            R.string.tab_safe,
            R.string.tab_blocked
        )
    }

    fun getTabTitle(position: Int) = fragment.getString(TAB_TITLES[position])

    override fun getItemCount() = COUNT_WEBSITE_FRAGMENTS

    override fun createFragment(position: Int) = when (position) {
        0 -> WebsiteTabFragment.newInstance(TAB_WEBSITE_FRAGMENT_SAFE)
        else -> WebsiteTabFragment.newInstance(TAB_WEBSITE_FRAGMENT_BLOCKED)
    }
}