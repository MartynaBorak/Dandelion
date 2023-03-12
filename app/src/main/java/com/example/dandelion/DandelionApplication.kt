package com.example.dandelion

import android.app.Application
import com.example.dandelion.data.AppContainer
import com.example.dandelion.data.AppDataContainer

class DandelionApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}