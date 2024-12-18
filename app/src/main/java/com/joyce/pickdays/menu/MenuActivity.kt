package com.joyce.pickdays.menu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joyce.pickdays.R
import com.joyce.pickdays.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    companion object {
        fun startActivity(context: Context){
            val intent = Intent(context, MenuActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonCollection()
    }

    private fun buttonCollection() {
        binding.tvFillAvailableTime.setOnClickListener {

        }

        binding.tvCheckAvailableTime.setOnClickListener {

        }

        binding.tvStartAvailableTime.setOnClickListener {

        }
    }
}