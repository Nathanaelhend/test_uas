package com.example.a160419034_ubayakost.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419034_ubayakost.R
import com.example.a160419034_ubayakost.model.Kost
//import com.example.a160419034_ubayakost.util.loadImage
import kotlinx.android.synthetic.main.fragment_kost_list.view.*
import kotlinx.android.synthetic.main.kost_list_item.view.*

class KostListAdapter(val kostList: ArrayList<Kost>) : RecyclerView
.Adapter<KostListAdapter.KostListViewHolder>(){
    class KostListViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kost_list_item, parent, false)
        return KostListViewHolder(view)


    }

    override fun onBindViewHolder(holder: KostListViewHolder, position: Int) {
        val kost = kostList[position]
        with(holder.view) {
            textKostName.text = kost.nama
            textWilayah.text = kost.wilayah
            textJenis.text = kost.jenis
            textRating.text = kost.rating
            txtHarga.text = kost.harga
            buttonDetail.setOnClickListener {
                val action = KostListFragmentDirections.actionKostDetail(kost.uuid.toString())
                Navigation.findNavController(it).navigate(action)
            }
//            imageKostPhoto.loadImage(kost.photo, progressLoadingPhoto)
        }

    }
    override fun getItemCount() =kostList.size

    fun updateKostList(newKostList: ArrayList<Kost>){
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }
}