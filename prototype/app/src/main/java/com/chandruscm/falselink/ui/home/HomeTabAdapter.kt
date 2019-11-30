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