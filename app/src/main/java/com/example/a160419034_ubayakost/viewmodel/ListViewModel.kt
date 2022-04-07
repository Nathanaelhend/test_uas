package com.example.a160419034_ubayakost.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160419034_ubayakost.model.Favorite
import com.example.a160419034_ubayakost.model.Kost
import com.example.a160419034_ubayakost.model.Pesan
import com.example.a160419034_ubayakost.model.Voucher
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application) : AndroidViewModel(application) {
    val KostLiveData= MutableLiveData<ArrayList<Kost>>()
    val MessageLiveData= MutableLiveData<ArrayList<Pesan>>()
    val FavoriteLiveData= MutableLiveData<ArrayList<Favorite>>()
    val VoucherLiveData = MutableLiveData<ArrayList<Voucher>>()
    val LoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {

        LoadErrorLiveData.value = false
        loadingLiveData.value = false
        queue = Volley.newRequestQueue(getApplication())
        var url= "https://ubaya.fun/hybrid/160419034/160419034_ANMP/kost.php"

        val stringRequest = StringRequest(
            Request.Method.GET,url,
            {
                val sType = object : TypeToken<ArrayList<Kost>>(){}.type
                val result = Gson().fromJson<ArrayList<Kost>>(it, sType)
                KostLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                LoadErrorLiveData.value = true
                Log.d("errorvolley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    fun MessageRefresh() {

        LoadErrorLiveData.value = false
        loadingLiveData.value = false
        queue = Volley.newRequestQueue(getApplication())
        var url= "https://ubaya.fun/hybrid/160419034/160419034_ANMP/message.php"

        val stringRequest = StringRequest(
            Request.Method.GET,url,
            {
                val sType = object : TypeToken<ArrayList<Pesan>>(){}.type
                val result = Gson().fromJson<ArrayList<Pesan>>(it, sType)
                MessageLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                LoadErrorLiveData.value = true
                Log.d("errorvolley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    fun favoriteRefresh() {

        LoadErrorLiveData.value = false
        loadingLiveData.value = false
        queue = Volley.newRequestQueue(getApplication())
        var url= "https://ubaya.fun/hybrid/160419034/160419034_ANMP/favorite.php"

        val stringRequest = StringRequest(
            Request.Method.GET,url,
            {
                val sType = object : TypeToken<ArrayList<Favorite>>(){}.type
                val result = Gson().fromJson<ArrayList<Favorite>>(it, sType)
                FavoriteLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                LoadErrorLiveData.value = true
                Log.d("errorvolley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    fun showVoucher() {

        LoadErrorLiveData.value = false
        loadingLiveData.value = false
        queue = Volley.newRequestQueue(getApplication())
        var url= "https://ubaya.fun/hybrid/160419034/160419034_ANMP/voucher.php"

        val stringRequest = StringRequest(
            Request.Method.GET,url,
            {
                val sType = object : TypeToken<ArrayList<Voucher>>(){}.type
                val result = Gson().fromJson<ArrayList<Voucher>>(it, sType)
                VoucherLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                LoadErrorLiveData.value = true
                Log.d("errorvolley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}