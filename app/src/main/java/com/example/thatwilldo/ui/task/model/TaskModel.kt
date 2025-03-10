package com.example.thatwilldo.ui.task.model

data class TaskModel(
    val id: Int,
    var title: String,
    var description: String,
    var date: Long,
    var time: Long,
    var category: String
) {
}