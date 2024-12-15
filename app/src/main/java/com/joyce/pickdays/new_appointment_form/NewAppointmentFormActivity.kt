package com.joyce.pickdays.new_appointment_form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.joyce.pickdays.R
import com.joyce.pickdays.databinding.ActivityNewAppointmentFormBinding

class NewAppointmentFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewAppointmentFormBinding
    private val viewModel: NewAppointmentFormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewAppointmentFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}