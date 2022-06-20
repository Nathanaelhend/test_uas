package com.example.a160419034_ubayakost.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160419034_ubayakost.model.*
import com.example.a160419034_ubayakost.util.buildDb
import com.google.gson.Gson
import kotlinx.coroutines.launch
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val KostLD = MutableLiveData<List<Kost>>()
    val LoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()


    fun refresh() {

        LoadErrorLiveData.value = false
        loadingLiveData.value = true
        launch {
            val db = buildDb(getApplication())
            KostLD.value = db.kostDao().selectAllKost()
        }
    }

//    fun MessageRefresh() {
//
//        LoadErrorLiveData.value = false
//        loadingLiveData.value = false
////        queue = Volley.newRequestQueue(getApplication())
////        var url= "https://ubaya.fun/hybrid/160419034/160419034_ANMP/message.php"
////
////        val stringRequest = StringRequest(
////            Request.Method.GET,url,
////            {
////                val sType = object : TypeToken<ArrayList<Pesan>>(){}.type
////                val result = Gson().fromJson<ArrayList<Pesan>>(it, sType)
////                MessageLiveData.value = result
////                loadingLiveData.value = false
////                Log.d("showvolley", it)
////            },
////            {
////                loadingLiveData.value = false
////                LoadErrorLiveData.value = true
////                Log.d("errorvolley",it.toString())
////            }
////        ).apply {
////            tag = "TAG"
////        }
////        queue?.add(stringRequest)
//    }

//    fun favoriteRefresh() {
//
//        LoadErrorLiveData.value = false
//        loadingLiveData.value = false
////        queue = Volley.newRequestQueue(getApplication())
////        var url= "https://ubaya.fun/hybrid/160419034/160419034_ANMP/favorite.php"
////
////        val stringRequest = StringRequest(
////            Request.Method.GET,url,
////            {
////                val sType = object : TypeToken<ArrayList<Favorite>>(){}.type
////                val result = Gson().fromJson<ArrayList<Favorite>>(it, sType)
////                FavoriteLiveData.value = result
////                loadingLiveData.value = false
////                Log.d("showvolley", it)
////            },
////            {
////                loadingLiveData.value = false
////                LoadErrorLiveData.value = true
////                Log.d("errorvolley",it.toString())
////            }
////        ).apply {
////            tag = "TAG"
////        }
////        queue?.add(stringRequest)
//    }

//    fun showVoucher() {
//
//        LoadErrorLiveData.value = false
//        loadingLiveData.value = false
////        queue = Volley.newRequestQueue(getApplication())
////        var url= "https://ubaya.fun/hybrid/160419034/160419034_ANMP/voucher.php"
////
////        val stringRequest = StringRequest(
////            Request.Method.GET,url,
////            {
////                val sType = object : TypeToken<ArrayList<Voucher>>(){}.type
////                val result = Gson().fromJson<ArrayList<Voucher>>(it, sType)
////                VoucherLiveData.value = result
////                loadingLiveData.value = false
////                Log.d("showvolley", it)
////            },
////            {
////                loadingLiveData.value = false
////                LoadErrorLiveData.value = true
////                Log.d("errorvolley",it.toString())
////            }
////        ).apply {
////            tag = "TAG"
////        }
////        queue?.add(stringRequest)
//    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }
}