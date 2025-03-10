package com.example.thatwilldo.ui.task.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thatwilldo.ui.task.model.TaskModel
import com.example.thatwilldo.ui.task.repository.TaskRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository = TaskRepository.getInstance(application)

    private val _selectedTime = MutableLiveData<String>()
    val selectedTime: LiveData<String> = _selectedTime
    private val _selectedTimeLong = MutableLiveData<Long>()
    val selectedTimeLong: LiveData<Long> = _selectedTimeLong

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> = _selectedDate
    private val _selectedDateLong = MutableLiveData<Long>()
    val selectedDateLong: LiveData<Long> = _selectedDateLong

    private val _selectedCategory = MutableLiveData<String>()
    val selectedCategory: LiveData<String> = _selectedCategory

    fun setTimeTask(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)

        _selectedTime.value = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
        _selectedTimeLong.value = calendar.timeInMillis
    }

    fun setDateTask(day: Int, month: Int, year: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        _selectedDate.value =
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
        _selectedDateLong.value = calendar.timeInMillis
    }

    fun setCategory(selectedCategory: String) {
        _selectedCategory.value = selectedCategory
    }

    fun insert(task: TaskModel) {
        taskRepository.insertTask(task)
    }
}