package com.joyce.pickdays

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joyce.pickdays.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LoginActivity.startActivity(this)
    }
}