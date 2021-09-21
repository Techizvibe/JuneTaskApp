package com.sairabanu.taskapp

import android.app.Application
import com.sairabanu.taskapp.di.DaggerRetroComponent
import com.sairabanu.taskapp.di.RetroComponent
import com.sairabanu.taskapp.di.RetroInstance

class MyApplication : Application() {
    private lateinit var retroComponent: RetroComponent

    override fun onCreate() {
        super.onCreate()
        retroComponent= DaggerRetroComponent.builder()
            .retroInstance(RetroInstance())
            .build()
    }
    fun getRetroComponent():RetroComponent{
        return retroComponent
    }
}