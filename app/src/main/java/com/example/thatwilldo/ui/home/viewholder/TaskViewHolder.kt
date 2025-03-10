package com.example.thatwilldo.ui.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.thatwilldo.databinding.RowTaskBinding
import com.example.thatwilldo.ui.task.model.TaskModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskViewHolder(private val bind: RowTaskBinding) : RecyclerView.ViewHolder(bind.root) {

    fun bind(task: TaskModel) {
        val date = Date(task.date)
        val formatDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = formatDate.format(date)

        val time = Date(task.time)
        val formatTime = SimpleDateFormat("HH:mm", Locale.getDefault())
        val formattedTime = formatTime.format(time)

        bind.textViewTitleTaskRow.text = task.title
        bind.textViewDescriptionTaskRow.text = task.description
        bind.textViewDateTaskRow.text = formattedDate
        bind.textViewTimeTaskRow.text = formattedTime
        bind.textViewCategoryRow.text = task.category
    }
}