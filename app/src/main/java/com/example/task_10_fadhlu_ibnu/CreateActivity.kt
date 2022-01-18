package com.example.task_10_fadhlu_ibnu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task_10_fadhlu_ibnu.room.Task
import com.example.task_10_fadhlu_ibnu.room.TaskDB
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateActivity : AppCompatActivity() {

    val db by lazy { TaskDB(this) }

//    private var taskId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
//        setupView()

        setFullScreen(window)
        lightStatusBar(window)
        setupListener()
    }
//    fun setupView(){
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return super.onSupportNavigateUp()
//    }
    fun setupListener(){
        button_save.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.taskDao().addTask(
                    Task(
                        0,
                        et_title.text.toString(),
                        et_task.text.toString()
                    )
                )
                finish()
            }
        }
        back_dash.setOnClickListener {
            onBackPressed()
        }
    }
}