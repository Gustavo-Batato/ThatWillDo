package com.example.thatwilldo.ui.task.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.thatwilldo.ui.task.constants.DataBaseConstants

class TaskDataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "taskdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE " + DataBaseConstants.Task.TABLE_NAME + " (" +
                    DataBaseConstants.Task.COLUMNS.ID + " integer primary key autoincrement, " +
                    DataBaseConstants.Task.COLUMNS.TITLE + " text, " +
                    DataBaseConstants.Task.COLUMNS.DESCRIPTION + " text, " +
                    DataBaseConstants.Task.COLUMNS.DATE + " integer, " +
                    DataBaseConstants.Task.COLUMNS.TIME + " integer, " +
                    DataBaseConstants.Task.COLUMNS.CATEGORY + " text)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

}