package com.sairabanu.taskapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sairabanu.taskapp.model.UserListModel
import com.sairabanu.taskapp.network.RetroInstance
import com.sairabanu.taskapp.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    //private var userListModelLiveData: MutableLiveData<UserListModel>
    private var userListModelLiveData: MutableLiveData<UserListModel> = MutableLiveData()

    fun getUserListObserver(): MutableLiveData<UserListModel> {
        return userListModelLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromApi("offset", "9")
            userListModelLiveData.postValue(response)
        }
    }
}