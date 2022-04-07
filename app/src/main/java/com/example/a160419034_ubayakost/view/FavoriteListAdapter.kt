package com.example.a160419034_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419034_ubayakost.R
import com.example.a160419034_ubayakost.model.Favorite
import com.example.a160419034_ubayakost.model.Kost
import com.example.a160419034_ubayakost.util.loadImage
import kotlinx.android.synthetic.main.favorite_list_item.view.*
import kotlinx.android.synthetic.main.kost_list_item.view.*

class FavoriteListAdapter(val favoriteList:ArrayList<Favorite>):RecyclerView
.Adapter<FavoriteListAdapter.favoriteListViewHolder>(){
    class favoriteListViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): favoriteListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.favorite_list_item, parent, false)
        return favoriteListViewHolder(view)
    }

    override fun onBindViewHolder(holder: favoriteListViewHolder, position: Int) {
        val favorite = favoriteList[position]
        with(holder.view){
            textKostFavorit.text = favorite.nama
            textWilayahFavorit.text = favorite.wilayah
            textJenisFavorit.text = favorite.jenis
            textRatingFavorit.text = favorite.rating
            txtHargaFavorit.text = favorite.harga

            imagePhotoFavorit.loadImage(favorite.photo, progressLoadingFavorit)
        }
    }

    override fun getItemCount() = favoriteList.size

    fun updateFavoriteList(newFavoriteList: ArrayList<Favorite>){
        favoriteList.clear()
        favoriteList.addAll(newFavoriteList)
        notifyDataSetChanged()
    }
}