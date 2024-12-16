package com.joyce.pickdays.new_appointment_form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joyce.pickdays.R
import com.joyce.pickdays.util.CalendarType
import com.joyce.pickdays.util.GlobalConfig
import com.joyce.pickdays.util.GlobalUtil.getContext
import com.joyce.pickdays.util.mLog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewAppointmentFormViewModel: ViewModel() {

    var isShowAlertDataLiveData = MutableLiveData<String>()
    var setStartDateLiveData = MutableLiveData<String>()
    var setEndDateLiveData = MutableLiveData<String>()
    private var startDate: Long = 0
    private var endDate: Long = 0

    fun checkContent(startDate: String, endDate: String, invitees: String) {
        if (startDate.isBlank() || endDate.isBlank() || invitees.isBlank()){
            isShowAlertDataLiveData.value = getContext().getString(R.string.please_fill_all_info)

        } else if (!isWithTwoYears()){
            isShowAlertDataLiveData.value = getContext().getString(R.string.is_over_two_years)

        } else if (endDate < startDate){
            isShowAlertDataLiveData.value = getContext().getString(R.string.start_date_error)
        }
    }

    fun handleSelectedDate(type: CalendarType, timeStamp: Long) {
        mLog.i("type = $type, timeStamp = $timeStamp")

        val selectedDate = SimpleDateFormat(GlobalConfig.DATE_FORMAT, Locale.getDefault()).format(Date(timeStamp))
        when(type){
            CalendarType.StartDate -> {
                startDate = timeStamp
                setStartDateLiveData.value = selectedDate
            }
            CalendarType.EndDate -> {
                endDate = timeStamp
                setEndDateLiveData.value = selectedDate
            }
        }
    }

    private fun isWithTwoYears(): Boolean{
        val twoYearsInMills = 2L*365*24*60*60*1000 //約2年
        return (endDate-startDate) <= twoYearsInMills
    }
}