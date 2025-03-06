package com.example.thatwilldo.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TaskViewModel : ViewModel() {

    private val _selectedTime = MutableLiveData<String>()
    val selectedTime: LiveData<String> = _selectedTime

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> = _selectedDate

    fun setTimeTask(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)

        _selectedTime.value = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)

    }

    fun setDateTask(day: Int, month: Int, year: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        _selectedDate.value = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
    }
}