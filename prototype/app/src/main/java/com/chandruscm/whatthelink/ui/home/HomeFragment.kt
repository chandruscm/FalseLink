package com.chandruscm.whatthelink.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandruscm.whatthelink.databinding.FragmentHomeBinding
import com.chandruscm.whatthelink.di.injector
import com.chandruscm.whatthelink.di.viewModel

/*
 * Show list of white-listed/blocked URLs and other preferences.
 */
class HomeFragment : Fragment() {

    private val viewModel by viewModel {
        requireActivity().injector.homeViewModel
    }

    private val adapter = WebsiteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        subscribeUi()
        return binding.root
    }

    fun subscribeUi() {
        viewModel.getWebsites().observe(viewLifecycleOwner, Observer { result ->
            adapter.submitList(result)
        })
    }
}
