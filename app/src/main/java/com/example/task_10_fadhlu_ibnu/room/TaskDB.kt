package com.example.task_10_fadhlu_ibnu.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDB : RoomDatabase() {

    abstract fun taskDao() : TaskDao

    companion object{

        @Volatile private var instance : TaskDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){

            instance ?: buildDatabase(context).also{
                instance = it
            }

        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TaskDB::class.java,
            "TaskDB"
        ).build()

    }

}