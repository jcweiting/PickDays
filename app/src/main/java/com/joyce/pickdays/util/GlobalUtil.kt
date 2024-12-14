package com.joyce.pickdays.util

import android.content.Context
import com.joyce.pickdays.MyApplication

object GlobalUtil {

    fun getContext(): Context {
        return MyApplication.getInstance()?.applicationContext!!
    }

}