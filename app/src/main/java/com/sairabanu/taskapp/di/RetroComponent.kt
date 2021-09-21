package com.sairabanu.taskapp.di

import com.sairabanu.taskapp.viewmodel.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroInstance::class])
interface RetroComponent {
    fun inject(mainActivityViewModel: MainActivityViewModel)
}