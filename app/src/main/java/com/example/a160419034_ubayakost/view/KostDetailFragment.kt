package com.example.a160419034_ubayakost.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160419034_ubayakost.R
import com.example.a160419034_ubayakost.util.loadImage
import com.example.a160419034_ubayakost.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_kost_detail.*
import kotlinx.android.synthetic.main.kost_list_item.view.*
import java.util.concurrent.TimeUnit

class KostDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_detail, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(arguments != null) {
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            val kostId = KostDetailFragmentArgs.fromBundle(requireArguments()).kostId
            viewModel.fetch(kostId)
        }

        observeViewModel()

    }


    private fun observeViewModel() {
        viewModel.KostLiveData.observe(viewLifecycleOwner){
            val kost = it
            kost?.let {
                imageViewDetail.loadImage(
                    kost.photo,
                    progressBarDetil
                )
                txtNamaKostDetail.setText(it.nama)
                textWilayahDetail.setText("Lokasi : ${it.wilayah}")
                textDescription.setText(it.desc)
                txtPhone.setText("Nomor Telepon : ${it.phone}")

                btnAddFavorite.setOnClickListener {
                    val action = KostDetailFragmentDirections.actionToFavorite()
                    Navigation.findNavController(it).navigate(action)
                }
            }

        }
    }
}