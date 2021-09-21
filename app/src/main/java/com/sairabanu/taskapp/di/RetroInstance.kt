package com.sairabanu.taskapp.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetroInstance {
    companion object {
        private const val baseURL = "https://sd2-hiring.herokuapp.com/api/"

        @Singleton
        @Provides
        fun getRetroService(retrofit: Retrofit):RetroService{
            return retrofit.create(RetroService::class.java)
        }

        @Singleton
        @Provides
        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}