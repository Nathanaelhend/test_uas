package com.example.a160419034_ubayakost.model

import com.google.gson.annotations.SerializedName


data class Kost(
    var id:String?,
    @SerializedName("nama")
    var nama:String?,
    var jenis:String?,
    @SerializedName("rating")
    var rating:String?,
    var wilayah:String?,
    var desc:String?,
    var phone:String?,
    var harga:String?,
    var address: String?,
    @SerializedName("photo")
    var photo:String?)

data class Pesan(
    var id:String?,
    var isi:String?,
    var description:String?)

data class Favorite(
    var id:String?,
    var nama:String?,
    var wilayah: String?,
    var jenis: String?,
    var rating: String?,
    var photo: String?,
    var harga: String?)

data class Voucher(
    var id:String?,
    var judul:String?,
    var description: String?,
    var expDate: String?,
    var photo: String?)