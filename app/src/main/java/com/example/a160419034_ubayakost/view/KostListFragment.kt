package com.example.a160419034_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160419034_ubayakost.R
import com.example.a160419034_ubayakost.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_kost_list.*


class KostListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private var kostListAdapter = KostListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recViewVoucher.layoutManager = LinearLayoutManager(context)
        recViewVoucher.adapter = kostListAdapter

        refreshLayoutVoucher.setOnRefreshListener {
            recViewVoucher.visibility = View.GONE

            progresLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutVoucher.isRefreshing = false
            txtErrorVoucher.visibility= View.GONE
        }
        fabAddKost.setOnClickListener {
            val action = KostListFragmentDirections.actionToAddKost()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }


    private fun observeViewModel() {
        viewModel.KostLiveData.observe(viewLifecycleOwner){
            kostListAdapter.updateKostList(it)
        }
        viewModel.LoadErrorLiveData.observe(viewLifecycleOwner){
            txtErrorVoucher.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){ //sedang loading
                recViewVoucher.visibility = View.GONE
                progresLoad.visibility  = View.VISIBLE
            }
            else{
                recViewVoucher.visibility = View.VISIBLE
                progresLoad.visibility = View.GONE
                txtErrorVoucher.visibility = View.GONE
            }
        }
    }
}