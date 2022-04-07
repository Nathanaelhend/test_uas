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
import kotlinx.android.synthetic.main.fragment_kost_list.*
import kotlinx.android.synthetic.main.fragment_kost_list.txtErrorVoucher
import kotlinx.android.synthetic.main.fragment_voucher.*
import kotlinx.android.synthetic.main.fragment_kost_list.recViewVoucher as recViewVoucher1
import kotlinx.android.synthetic.main.fragment_voucher.refreshLayoutVoucher as refreshLayoutVoucher1

class VoucherFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private var voucherListAdapter = VoucherListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voucher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.showVoucher()

        recViewVoucher.layoutManager = LinearLayoutManager(context)
        recViewVoucher.adapter = voucherListAdapter

        refreshLayoutVoucher.setOnRefreshListener {
            recViewVoucher.visibility = View.GONE

            progressVoucher.visibility = View.VISIBLE
            viewModel.showVoucher()
            refreshLayoutVoucher.isRefreshing = false
            txtErrorVoucher.visibility= View.GONE
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.VoucherLiveData.observe(viewLifecycleOwner){
            voucherListAdapter.updateVoucherList(it)
        }
        viewModel.LoadErrorLiveData.observe(viewLifecycleOwner){
            txtErrorVoucher.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){ //sedang loading
                recViewVoucher.visibility = View.GONE
                progressVoucher.visibility  = View.VISIBLE
            }
            else{
                recViewVoucher.visibility = View.VISIBLE
                progressVoucher.visibility = View.GONE
                txtErrorVoucher.visibility = View.GONE
            }
        }
    }


}