package com.example.task_10_fadhlu_ibnu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.task_10_fadhlu_ibnu.room.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter (private val tasks: ArrayList<Task>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){
    class TaskViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.view.dash_title.text = task.title
        holder.view.dash_task.text = task.task
        holder.view.dash_title.setOnClickListener {
            listener.onReadEdit(task)
        }
        holder.view.dash_task.setOnClickListener {
            listener.onReadEdit(task)
        }
        holder.view.delete_task.setOnClickListener {
            listener.onDelete(task)
        }
    }

    override fun getItemCount()= tasks.size

    fun setData(List: List<Task>){
        tasks.clear()
        tasks.addAll(List)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onReadEdit(task: Task)
        fun onDelete(task: Task)
    }


}