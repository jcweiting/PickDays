package com.joyce.pickdays.new_appointment_form

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.joyce.pickdays.R
import com.joyce.pickdays.databinding.ActivityNewAppointmentFormBinding
import com.joyce.pickdays.util.CalendarType
import java.util.Calendar

class NewAppointmentFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewAppointmentFormBinding
    private val viewModel: NewAppointmentFormViewModel by viewModels()

    companion object{
        fun startActivity(context: Context){
            val intent = Intent(context, NewAppointmentFormActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewAppointmentFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        buttonCollection()
        liveDataCollection()
    }

    private fun liveDataCollection() {
        viewModel.isShowAlertDataLiveData.observe(this){ content ->
            showAlertDialog(content)
        }

        viewModel.setStartDateLiveData.observe(this){
            binding.tvNewAppointmentStartDate.text = it
        }

        viewModel.setEndDateLiveData.observe(this){
            binding.tvNewAppointmentEndDate.text = it
        }
    }

    private fun buttonCollection() {
        binding.tvNewAppointmentStartDate.setOnClickListener {
            showCalendarDialog(CalendarType.StartDate)
        }

        binding.tvNewAppointmentEndDate.setOnClickListener {
            showCalendarDialog(CalendarType.EndDate)
        }

        binding.tvConfirmCreateNewAppointmentForm.setOnClickListener {
            viewModel.checkContent(binding.tvNewAppointmentStartDate.text.toString().trim(), binding.tvNewAppointmentEndDate.text.toString().trim(), binding.tvNewAppointmentInvitation.text.toString().trim())
        }

        binding.actionBarNewAppointmentForm.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun initView() {
        binding.actionBarNewAppointmentForm.tvTitle.text = getString(R.string.create_new_appointment)
    }

    private fun showCalendarDialog(type: CalendarType) {
        val calendar = Calendar.getInstance()
        val title = if (type == CalendarType.StartDate) getString(R.string.please_choose_start_date) else getString(R.string.please_choose_end_date)
        
        val dataPicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(title)
            .setSelection(calendar.timeInMillis) //預設為今天
            .setCalendarConstraints(
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointForward.from(calendar.timeInMillis)) // 僅允許今天及以後的日期
                    .build()
            )
            .build()

        dataPicker.addOnPositiveButtonClickListener { timeStamp ->
            viewModel.handleSelectedDate(type, timeStamp)
        }

        dataPicker.show((this as AppCompatActivity).supportFragmentManager, "date_picker")
    }

    private fun showAlertDialog(content: String){
        val dialog = AlertDialog.Builder(this)
            .setMessage(content)
            .setPositiveButton(getString(R.string.confirm)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()
        dialog.show()
    }
}