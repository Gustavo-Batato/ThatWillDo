package com.example.thatwilldo.ui.task.constants

class DataBaseConstants private constructor() {
    object Task {
        const val TABLE_NAME = "Task"

        object COLUMNS {
            const val ID = "id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val DATE = "date"
            const val TIME = "time"
            const val CATEGORY = "category"
        }
    }
}