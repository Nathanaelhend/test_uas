package com.example.a160419034_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.a160419034_ubayakost.R
import com.example.a160419034_ubayakost.util.loadImage
import com.example.a160419034_ubayakost.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_kost_detail.*
import kotlinx.android.synthetic.main.fragment_message_detail.*

class MessageDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(arguments != null) {
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            val messageId = MessageDetailFragmentArgs.fromBundle(requireArguments()).messageId
            viewModel.fetchMessage(messageId)
        }


        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.MessageLiveData.observe(viewLifecycleOwner){
            val message = it
            message?.let {
                txtIsiMessage.setText(it.description)
            }
        }
    }
}