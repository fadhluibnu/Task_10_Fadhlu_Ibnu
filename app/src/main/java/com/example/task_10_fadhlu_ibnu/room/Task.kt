package com.example.task_10_fadhlu_ibnu.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val  id: Int,
    val title: String,
    val task: String
)
