package com.example.a160419034_ubayakost.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a160419034_ubayakost.util.MIGRATION_1_2
import com.example.a160419034_ubayakost.util.MIGRATION_1_3

@Database(entities = arrayOf(Kost::class), version = 1)
abstract class KostDatabase: RoomDatabase(){
    abstract fun kostDao(): KostDao

    companion object {
        @Volatile
        private var instance: KostDatabase? =null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, KostDatabase::class.java, "newkostdb")
                .addMigrations(MIGRATION_1_2, MIGRATION_1_3)
                .build()

        operator fun invoke(context: Context) {
            if(instance != null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}