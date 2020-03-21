package com.sergiobelda.androidtodometer.ui.adapter

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergiobelda.androidtodometer.databaseview.ProjectTaskListing
import com.sergiobelda.androidtodometer.databinding.ItemTaskBinding
import com.sergiobelda.androidtodometer.model.Task
import com.sergiobelda.androidtodometer.model.TaskState

class TasksAdapter(private val items: List<ProjectTaskListing>) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    lateinit var taskClickListener: TaskClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ),
            parent.context
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: ItemTaskBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(projectTaskListing: ProjectTaskListing) {
            val task = projectTaskListing.task
            binding.taskCard.transitionName = task.taskName
            binding.task = task
            binding.taskProjectName.text = projectTaskListing.projectName
            if (task.taskState == TaskState.DOING) {
                binding.checkTaskButton.setOnClickListener {
                    taskClickListener.onTaskDoneClick(task)
                }
                binding.taskNameTextView.text = task.taskName
            } else {
                binding.checkTaskButton.setOnClickListener {
                    taskClickListener.onTaskDoingClick(task)
                }
                val spannableString = SpannableString(task.taskName)
                spannableString.setSpan(StrikethroughSpan(), 0, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.taskNameTextView.text = spannableString
            }
            binding.taskCard.setOnClickListener {
                taskClickListener.onTaskClick(task, binding.taskCard)
            }
        }
    }

    interface TaskClickListener {
        fun onTaskClick(task: Task, view: View)
        fun onDeleteTaskClick(task: Task)
        fun onTaskDoneClick(task: Task)
        fun onTaskDoingClick(task: Task)
    }
}