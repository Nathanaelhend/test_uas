package com.example.a160419034_ubayakost.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a160419034_ubayakost.R
import com.example.a160419034_ubayakost.model.KostDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

val DB_NAME = "newkostdb"

fun buildDb(context: Context) = Room.databaseBuilder(context, KostDatabase::class.java, DB_NAME)
    .addMigrations(MIGRATION_1_2, MIGRATION_1_3)
    .build()

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE kost ADD COLUMN nama STRING DEFAULT NULL"
        )
    }
}
val MIGRATION_1_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE kost ADD COLUMN jenis STRING DEFAULT NULL")
    }
}