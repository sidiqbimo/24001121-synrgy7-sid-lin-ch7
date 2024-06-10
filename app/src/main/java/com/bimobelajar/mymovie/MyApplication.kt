package com.bimobelajar.mymovie

import android.annotation.SuppressLint
import android.app.Application
import com.bimobelajar.core.data.DataStoreManager

class MyApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var dataStoreManager: DataStoreManager
            private set
    }

    override fun onCreate() {
        super.onCreate()
        dataStoreManager = DataStoreManager.getInstance(applicationContext)
    }
}
