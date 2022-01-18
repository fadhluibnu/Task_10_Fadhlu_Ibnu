package com.example.task_10_fadhlu_ibnu

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_10_fadhlu_ibnu.room.Task
import com.example.task_10_fadhlu_ibnu.room.TaskDB
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { TaskDB(this) }

    lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setupView()
        setupListener()
        setupRecycleView()

        setFullScreen(window)
        lightStatusBar(window)

    }

//    fun setupView(){
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//    }

    fun setupListener(){
        button_create.setOnClickListener{
            startActivity(Intent(this, CreateActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        loadTask()
    }

    fun loadTask(){
        CoroutineScope(Dispatchers.IO).launch {
            val tasks = db.taskDao().getTasks()
            Log.d("MainActivity", "dbres: $tasks")
            withContext(Dispatchers.Main){
                taskAdapter.setData(tasks)
            }
        }
    }

    fun setupRecycleView(){
        taskAdapter = TaskAdapter(arrayListOf(), object : TaskAdapter.OnAdapterListener{
            override fun onReadEdit(task: Task) {
                startActivity(Intent(applicationContext, ReadEditActivity::class.java).putExtra("id_task", task.id))
            }

            override fun onDelete(task: Task) {
                deleteDialog(task)
            }

        })
        list_tasks.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = taskAdapter
        }
    }

    private fun deleteDialog(task: Task){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin hapus ${task.title}")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.taskDao().deleteTask(task)
                    loadTask()
                }
            }
        }
        alertDialog.show()
    }

//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return super.onSupportNavigateUp()
//    }
}