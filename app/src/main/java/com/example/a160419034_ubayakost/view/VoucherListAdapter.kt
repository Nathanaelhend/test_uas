package com.example.a160419034_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419034_ubayakost.R
import com.example.a160419034_ubayakost.model.Voucher
//import com.example.a160419034_ubayakost.util.loadImage
import kotlinx.android.synthetic.main.voucher_list_item.view.*

class VoucherListAdapter(val voucherList: ArrayList<Voucher>) : RecyclerView
.Adapter<VoucherListAdapter.voucherViewHolder>(){
    class voucherViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): voucherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.voucher_list_item, parent, false)
        return voucherViewHolder(view)
    }

    override fun onBindViewHolder(holder: voucherViewHolder, position: Int) {
        val voucher = voucherList[position]
        with(holder.view) {
            txtJudulVoucher.text = voucher.judul
            txtDescVoucher.text = voucher.description
            txtExpDate.text = "Exp Date : ${voucher.expDate}"
//            imageVoucher.loadImage(voucher.photo, progressImageVoucher)
        }
    }

    override fun getItemCount() = voucherList.size

    fun updateVoucherList(newVoucherList: ArrayList<Voucher>) {
        voucherList.clear()
        voucherList.addAll(newVoucherList)
        notifyDataSetChanged()
    }
}