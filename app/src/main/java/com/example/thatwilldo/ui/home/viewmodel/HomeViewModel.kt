package com.example.thatwilldo.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thatwilldo.ui.task.model.TaskModel
import com.example.thatwilldo.ui.task.repository.TaskRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository = TaskRepository.getInstance(application)

    private val _listTask = MutableLiveData<List<TaskModel>>()
    val list: LiveData<List<TaskModel>> = _listTask

    fun getList() {
        _listTask.value = taskRepository.getAllTask()
    }
}