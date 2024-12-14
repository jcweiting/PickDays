package com.joyce.pickdays

import android.app.Application

class MyApplication: Application() {

    companion object{
        var app: MyApplication? = null

        fun getInstance(): MyApplication? {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}