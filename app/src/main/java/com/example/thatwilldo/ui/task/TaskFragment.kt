package com.example.thatwilldo.ui.task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.thatwilldo.databinding.FragmentTaskBinding
import com.example.thatwilldo.ui.task.model.TaskModel
import com.example.thatwilldo.ui.task.viewmodel.TaskViewModel
import java.util.Calendar

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        setTimeDialog()
        observers()
        saveTask()

        return binding.root
    }

    private fun saveTask() {
        binding.buttonSaveTask.setOnClickListener {
            setCategory()

            val titleTask = binding.editTextTitle.text.toString()
            val descriptionTask = binding.editTextDescription.text.toString()
            val selectedDate = taskViewModel.selectedDateLong.value ?: 0
            val selectedTime = taskViewModel.selectedTimeLong.value ?: 0
            val selectedCategory = taskViewModel.selectedCategory.value ?: "Normal"

            val task = TaskModel(
                0,
                titleTask,
                descriptionTask,
                selectedDate,
                selectedTime,
                selectedCategory
            )

            taskViewModel.insert(task)
        }
    }

    private fun setTimeDialog() {
        val textViewTime = binding.textViewTimeTask
        val textViewDate = binding.textViewDateTask

        textViewTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                taskViewModel.setTimeTask(selectedHour, selectedMinute)
            }, hour, minute, true)

            timePicker.show()
        }

        textViewDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                taskViewModel.setDateTask(
                    selectedDay, selectedMonth, selectedYear
                )
            }, year, month, day).show()
        }
    }

    private fun setCategory() {
        val categoryId = binding.radioGroupCategoryTask.checkedRadioButtonId

        val selectedCategory = when (categoryId) {
            binding.radioButtonCategoryNormal.id -> "Normal"
            binding.radioButtonCategoryImportant.id -> "Important"
            else -> "Normal"
        }
        taskViewModel.setCategory(selectedCategory)
    }

    private fun observers() {
        taskViewModel.selectedTime.observe(viewLifecycleOwner) {
            binding.textViewTimeTask.text = it
        }
        taskViewModel.selectedDate.observe(viewLifecycleOwner) {
            binding.textViewDateTask.text = it
        }
    }
}