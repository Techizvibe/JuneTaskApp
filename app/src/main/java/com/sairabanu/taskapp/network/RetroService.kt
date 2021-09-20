package com.sairabanu.taskapp.network

import com.sairabanu.taskapp.model.UserListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("users")
    suspend fun getDataFromApi(
        @Query("Param") Param: String,
        @Query("limit") limit: String
    ): UserListModel
}