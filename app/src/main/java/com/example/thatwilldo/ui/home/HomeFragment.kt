package com.example.thatwilldo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thatwilldo.databinding.FragmentHomeBinding
import com.example.thatwilldo.ui.home.adapter.AdapterTask
import com.example.thatwilldo.ui.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private val adapterTask = AdapterTask()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeViewModel.getList()

        binding.recyclerViewTask.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewTask.adapter = adapterTask

        observers()
        return binding.root
    }

    private fun observers() {
        homeViewModel.list.observe(viewLifecycleOwner) {
            adapterTask.updateTask(it)
        }
    }
}