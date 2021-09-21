package com.sairabanu.taskapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sairabanu.taskapp.MyApplication
import com.sairabanu.taskapp.model.UserListModel
import com.sairabanu.taskapp.di.RetroService
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
        userListModelLiveData=MutableLiveData()
    }

    //Livedata observer
    fun getUserListObserver(): MutableLiveData<UserListModel> {
        return userListModelLiveData
    }

    fun makeApiCall() {
        val call: Call<UserListModel>?=mService.getDataFromApi("offset", "9")
        call?.enqueue(object :Callback<UserListModel>{
            override fun onResponse(call: Call<UserListModel>, response: Response<UserListModel>) {
                if(response.isSuccessful){
                    userListModelLiveData.postValue(response.body())
                }else{
                    userListModelLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                userListModelLiveData.postValue(null)
            }

        })
        /*viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromApi("offset", "9")
            userListModelLiveData.postValue(response)
        }*/
    }
}