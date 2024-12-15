package com.joyce.pickdays

import android.app.Application

class MyApplication: Application() {

    companion object{
        lateinit var app: MyApplication
            private set

        fun getInstance(): MyApplication {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}