package com.example.thatwilldo.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thatwilldo.databinding.RowTaskBinding
import com.example.thatwilldo.ui.home.viewholder.TaskViewHolder
import com.example.thatwilldo.ui.task.model.TaskModel

class AdapterTask : RecyclerView.Adapter<TaskViewHolder>() {

    private var listTasks: List<TaskModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val item = RowTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(item)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(listTasks[position])
    }

    override fun getItemCount(): Int {
        return listTasks.count()
    }

    fun updateTask(list: List<TaskModel>) {
        listTasks = list
    }
}