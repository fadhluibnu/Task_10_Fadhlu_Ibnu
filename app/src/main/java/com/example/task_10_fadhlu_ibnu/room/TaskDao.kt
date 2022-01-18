package com.example.task_10_fadhlu_ibnu.room

import android.provider.ContactsContract
import androidx.room.*
import com.example.task_10_fadhlu_ibnu.room.Task

@Dao
interface TaskDao {

    @Insert
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM task")
    suspend fun getTasks(): List<Task>

    @Query("SELECT * FROM task WHERE id=:task_id")
    suspend fun getTask(task_id: Int): List<Task>


}