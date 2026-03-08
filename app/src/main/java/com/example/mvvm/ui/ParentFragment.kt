package com.example.mvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvm.R
import com.example.mvvm.viewModel.ZipViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.example.mvvm.utils.FlowResponse

@AndroidEntryPoint
class ParentFragment : Fragment() {

    private val viewModel: ZipViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getZIpDetails("33162")
        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.zipResponseLiveData.observe(viewLifecycleOwner){
            when(it.status){
                FlowResponse.Status.LOADING -> {
                    println("Loading")
                }

                FlowResponse.Status.SUCCESS -> {
                    val place = it.data?.places?.firstOrNull()
                    println("city: ${place?.name}")
                    println("State: ${place?.state}")
                }
                FlowResponse.Status.ERROR -> {
                    println("Error: ${it.error}")
                }
            }
        }
    }
}