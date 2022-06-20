package com.example.a160419034_ubayakost.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160419034_ubayakost.model.Kost
import com.example.a160419034_ubayakost.model.Pesan
import com.example.a160419034_ubayakost.util.buildDb
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private var job = Job()
    val kostLD = MutableLiveData<Kost>()

    fun addKost(kostList: List<Kost>) {
        launch {
            val db = buildDb(getApplication())
            db.kostDao().insertAll(*kostList.toTypedArray())
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

//    val KostLiveData= MutableLiveData<Kost>()
//    val MessageLiveData= MutableLiveData<Pesan>()
//    val TAG = "volleyTag"
//    private var queue: RequestQueue? = null

//    fun fetch(kostId : String) {
//
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://ubaya.fun/hybrid/160419034/160419034_ANMP/kost.php?id=$kostId"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET,url,
//            {
//                val result = Gson().fromJson<Kost>(it, Kost::class.java)
//                KostLiveData.value = result
//                Log.d("showvolley", it)
//            },
//            {
//                Log.d("errorvolley",it.toString())
//            }
//        ).apply {
//            tag = "TAG"
//        }
//        queue?.add(stringRequest)
//    }
//
//    fun fetchMessage(messageId : String) {
//
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://ubaya.fun/hybrid/160419034/160419034_ANMP/message.php?id=$messageId"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET,url,
//            {
//                val result = Gson().fromJson<Pesan>(it, Pesan::class.java)
//                MessageLiveData.value = result
//                Log.d("showvolley", it)
//            },
//            {
//                Log.d("errorvolley",it.toString())
//            }
//        ).apply {
//            tag = "TAG"
//        }
//        queue?.add(stringRequest)
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }
}