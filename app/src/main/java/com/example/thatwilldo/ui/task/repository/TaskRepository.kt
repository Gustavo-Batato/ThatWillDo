package com.example.thatwilldo.ui.task.repository

import android.content.ContentValues
import android.content.Context
import com.example.thatwilldo.ui.task.constants.DataBaseConstants
import com.example.thatwilldo.ui.task.constants.TaskConstants
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

    fun insertTask(task: TaskModel): Boolean {
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

    fun updateTask(task: TaskModel): Boolean {
        return try {
            val db = taskDataBase.writableDatabase
            val values = ContentValues()
            values.put(DataBaseConstants.Task.COLUMNS.TITLE, task.title)
            values.put(DataBaseConstants.Task.COLUMNS.DESCRIPTION, task.description)
            values.put(DataBaseConstants.Task.COLUMNS.DATE, task.date)
            values.put(DataBaseConstants.Task.COLUMNS.TIME, task.time)
            values.put(DataBaseConstants.Task.COLUMNS.CATEGORY, task.category)

            val selection = DataBaseConstants.Task.COLUMNS.ID + " = ?"
            val args = arrayOf(task.id.toString())

            db.update(DataBaseConstants.Task.TABLE_NAME, values, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteTask(taskId: Int): Boolean {
        return try {
            val db = taskDataBase.writableDatabase

            val selection = DataBaseConstants.Task.COLUMNS.ID + " = ?"
            val args = arrayOf(taskId.toString())

            db.delete(DataBaseConstants.Task.TABLE_NAME, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAllTask(): List<TaskModel> {
        val list = mutableListOf<TaskModel>()

        return try {
            val db = taskDataBase.readableDatabase

            val columns = arrayOf(
                DataBaseConstants.Task.COLUMNS.ID,
                DataBaseConstants.Task.COLUMNS.TITLE,
                DataBaseConstants.Task.COLUMNS.DESCRIPTION,
                DataBaseConstants.Task.COLUMNS.DATE,
                DataBaseConstants.Task.COLUMNS.TIME,
                DataBaseConstants.Task.COLUMNS.CATEGORY
            )

            val cursor =
                db.query(DataBaseConstants.Task.TABLE_NAME, columns, null, null, null, null, null)

            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.Task.COLUMNS.ID))
                    val title =
                        cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.Task.COLUMNS.TITLE))
                    val description =
                        cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.Task.COLUMNS.DESCRIPTION))
                    val date =
                        cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseConstants.Task.COLUMNS.DATE))
                    val time =
                        cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseConstants.Task.COLUMNS.TIME))
                    val category =
                        cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.Task.COLUMNS.CATEGORY))

                    list.add(TaskModel(id, title, description, date, time, category))
                }
            }
            cursor.close()

            list
        } catch (e: Exception) {
            list
        }
    }

    fun getAllTaskFiltered(): List<TaskModel> {
        val list = mutableListOf<TaskModel>()

        return try {
            val db = taskDataBase.readableDatabase

            val columns = arrayOf(
                DataBaseConstants.Task.COLUMNS.ID,
                DataBaseConstants.Task.COLUMNS.TITLE,
                DataBaseConstants.Task.COLUMNS.DESCRIPTION,
                DataBaseConstants.Task.COLUMNS.DATE,
                DataBaseConstants.Task.COLUMNS.TIME,
                DataBaseConstants.Task.COLUMNS.CATEGORY
            )

            val selection = DataBaseConstants.Task.COLUMNS.CATEGORY + " = ?"
            val args = arrayOf(TaskConstants.Task.Category.IMPORTANT)
            val order = DataBaseConstants.Task.COLUMNS.DATE + " ASC"

            val cursor =
                db.query(
                    DataBaseConstants.Task.TABLE_NAME,
                    columns,
                    selection,
                    args,
                    null,
                    null,
                    order
                )

            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.Task.COLUMNS.ID))
                    val title =
                        cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.Task.COLUMNS.TITLE))
                    val description =
                        cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.Task.COLUMNS.DESCRIPTION))
                    val date =
                        cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseConstants.Task.COLUMNS.DATE))
                    val time =
                        cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseConstants.Task.COLUMNS.TIME))
                    val category =
                        cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.Task.COLUMNS.CATEGORY))

                    list.add(TaskModel(id, title, description, date, time, category))
                }
            }
            cursor.close()

            list
        } catch (e: Exception) {
            list
        }
    }
}