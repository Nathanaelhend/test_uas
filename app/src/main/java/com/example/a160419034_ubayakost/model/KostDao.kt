package com.example.a160419034_ubayakost.model

import androidx.room.*

@Dao
interface KostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg kost: Kost)

    @Query("SELECT * FROM kost ORDER BY uuid ASC")
    suspend fun selectAllKost(): List<Kost>

    @Query("SELECT * FROM kost WHERE uuid = :id")
    suspend fun selectKost(id:Int): Kost

    @Query("UPDATE kost SET nama = :nama, jenis = :jenis, rating = :rating, wilayah = :wilayah, descr = :descr, phone = :phone, phone = :harga, address = :address, photo = :photo WHERE uuid = :id")
    suspend fun update(id: Int, nama: String, jenis: String, rating: String, wilayah: String, descr: String, phone: String, harga: String, address: String, photo: String)

    @Update
    suspend fun update(kost: Kost)

    @Delete
    suspend fun deleteKost(kost: Kost)

}