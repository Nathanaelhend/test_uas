package com.example.a160419034_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160419034_ubayakost.R
import com.example.a160419034_ubayakost.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.favorite_list_item.*
import kotlinx.android.synthetic.main.fragment_favorite_list.*
import kotlinx.android.synthetic.main.fragment_message_list.*
import kotlinx.android.synthetic.main.fragment_message_list.refreshLayoutVoucher as refreshLayoutVoucher1

class FavoriteListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private var favoriteListAdapter = FavoriteListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
//        viewModel.favoriteRefresh()

        recViewFavorite.layoutManager = LinearLayoutManager(context)
        recViewFavorite.adapter = favoriteListAdapter

        refreshLayoutVoucher.setOnRefreshListener {
            recViewFavorite.visibility = View.GONE

            progressBarLoad.visibility = View.VISIBLE
//            viewModel.favoriteRefresh()
            refreshLayoutVoucher.isRefreshing = false
            txtErrorFavorite.visibility= View.GONE
        }
        observeViewModel()
    }

    private fun observeViewModel() {
//        viewModel.FavoriteLiveData.observe(viewLifecycleOwner){
//            favoriteListAdapter.updateFavoriteList(it)
//        }
        viewModel.LoadErrorLiveData.observe(viewLifecycleOwner){
            txtErrorFavorite.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){ //sedang loading
                recViewFavorite.visibility = View.GONE
                progressBarLoad.visibility  = View.VISIBLE
            }
            else{
                recViewFavorite.visibility = View.VISIBLE
                progressBarLoad.visibility = View.GONE
                txtErrorFavorite.visibility = View.GONE
            }
        }
    }
}