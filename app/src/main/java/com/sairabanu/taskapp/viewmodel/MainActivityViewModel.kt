package com.sairabanu.taskapp.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sairabanu.taskapp.MyApplication
import com.sairabanu.taskapp.di.RetroService
import com.sairabanu.taskapp.model.UserListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    @Inject
    lateinit var mService: RetroService

    private var userListModelLiveData: MutableLiveData<UserListModel>

    init {
        (application as MyApplication).getRetroComponent().inject(this)
        userListModelLiveData = MutableLiveData()
    }

    //Livedata observer
    fun getUserListObserver(): MutableLiveData<UserListModel> {
        return userListModelLiveData
    }

    fun makeApiCall() {
        try {
            if (hasInternetConnection()) {
                val call: Call<UserListModel>? = mService.getDataFromApi("offset", "9")
                call?.enqueue(object : Callback<UserListModel> {
                    override fun onResponse(
                        call: Call<UserListModel>,
                        response: Response<UserListModel>
                    ) {
                        if (response.isSuccessful) {
                            userListModelLiveData.postValue(response.body())
                        } else {
                            userListModelLiveData.postValue(null)
                        }
                    }
                    override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                        userListModelLiveData.postValue(null)
                    }
                })
            } else {
                Toast.makeText(getApplication(), "No internet", Toast.LENGTH_LONG).show()
            }
        } catch (t: Throwable) {

        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<MyApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}