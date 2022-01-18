package com.example.task_10_fadhlu_ibnu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task_10_fadhlu_ibnu.room.Task
import com.example.task_10_fadhlu_ibnu.room.TaskDB
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.android.synthetic.main.activity_read_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ReadEditActivity : AppCompatActivity() {

    val db by lazy { TaskDB(this) }

//    private var taskId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_edit)
        getTaskReadEdit()

        setFullScreen(window)
        lightStatusBar(window)
        back_dash_view.setOnClickListener {
            onBackPressed()
        }
    }

    fun getTaskReadEdit(){
        var taskId = intent.getIntExtra("id_task", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val tasks = db.taskDao().getTask(taskId)[0]
            et_edit_title.setText(tasks.title)
            et_edit_task.setText(tasks.task)
        }
        button_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.taskDao().updateTask(
                    Task(
                        taskId,
                        et_edit_title.text.toString(),
                        et_edit_task.text.toString()
                    )
                )
                finish()
            }
        }
    }

}