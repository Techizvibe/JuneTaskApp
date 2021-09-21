package com.sairabanu.taskapp.di

import com.sairabanu.taskapp.model.UserListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("users")
    fun getDataFromApi(
        @Query("Param") Param: String,
        @Query("limit") limit: String
    ): Call<UserListModel>?
}