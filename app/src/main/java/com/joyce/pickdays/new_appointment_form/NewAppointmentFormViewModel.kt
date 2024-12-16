package com.joyce.pickdays.new_appointment_form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joyce.pickdays.util.CalendarType
import com.joyce.pickdays.util.GlobalConfig
import com.joyce.pickdays.util.mLog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewAppointmentFormViewModel: ViewModel() {

    var isEmptyDataLiveData = MutableLiveData<Boolean>()
    var setStartDateLiveData = MutableLiveData<String>()
    var setEndDateLiveData = MutableLiveData<String>()

    fun checkContent(startDate: String, endDate: String, invitees: String) {
        if (startDate.isBlank() || endDate.isBlank() || invitees.isBlank()){
            isEmptyDataLiveData.value = true
        }
    }

    fun handleSelectedDate(type: CalendarType, timeStamp: Long) {
        mLog.i("type = $type, timeStamp = $timeStamp")

        val selectedDate = SimpleDateFormat(GlobalConfig.DATE_FORMAT, Locale.getDefault()).format(Date(timeStamp))
        when(type){
            CalendarType.StartDate -> setStartDateLiveData.value = selectedDate
            CalendarType.EndDate -> setEndDateLiveData.value = selectedDate
        }
    }
}