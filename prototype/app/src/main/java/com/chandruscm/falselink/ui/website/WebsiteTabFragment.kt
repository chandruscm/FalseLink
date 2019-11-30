package com.chandruscm.falselink.ui.website

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandruscm.falselink.databinding.TabWebsitesBinding
import com.chandruscm.falselink.di.injector
import com.chandruscm.falselink.di.viewModel
import com.chandruscm.falselink.utils.TAB_WEBSITE_FRAGMENT_SAFE
import com.chandruscm.falselink.utils.TAB_WEBSITE_FRAGMENT_TYPE

class WebsiteTabFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(tabType: Int) =
            WebsiteTabFragment().apply {
                arguments = Bundle().apply {
                    putInt(TAB_WEBSITE_FRAGMENT_TYPE, tabType)
                }
            }
    }

    private val viewModel by viewModel {
        requireActivity().injector.websiteViewModel
    }
    private var tabType: Int = TAB_WEBSITE_FRAGMENT_SAFE
    private val adapter = WebsiteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            tabType = getInt(TAB_WEBSITE_FRAGMENT_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = TabWebsitesBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        subscribeUi()
        return binding.root
    }

    private fun subscribeUi() {
        viewModel.getWebsites(tabType).observe(viewLifecycleOwner, Observer { websites ->
            adapter.submitList(websites)
        })
    }
}