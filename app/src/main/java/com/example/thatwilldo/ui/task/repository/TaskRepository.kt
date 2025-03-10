package com.example.thatwilldo.ui.task.repository

import android.content.ContentValues
import android.content.Context
import com.example.thatwilldo.ui.task.constants.DataBaseConstants
import com.example.thatwilldo.ui.task.model.TaskModel

class TaskRepository private constructor(context: Context) {

    private val taskDataBase = TaskDataBase(context)

    companion object {
        private lateinit var repository: TaskRepository

        fun getInstance(context: Context): TaskRepository {
            if (!Companion::repository.isInitialized) {
                repository = TaskRepository(context)
            }
            return repository
        }
    }

    fun insert(task: TaskModel): Boolean {
        return try {
            val db = taskDataBase.writableDatabase

            val values = ContentValues()
            values.put(DataBaseConstants.Task.COLUMNS.TITLE, task.title)
            values.put(DataBaseConstants.Task.COLUMNS.DESCRIPTION, task.description)
            values.put(DataBaseConstants.Task.COLUMNS.DATE, task.date)
            values.put(DataBaseConstants.Task.COLUMNS.TIME, task.time)
            values.put(DataBaseConstants.Task.COLUMNS.CATEGORY, task.category)

            db.insert(DataBaseConstants.Task.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun updateTask(task: TaskModel) {
        val db = taskDataBase.writableDatabase
        val values = ContentValues()


    }
}