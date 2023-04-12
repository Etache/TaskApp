package com.example.taskapp_m4.database.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskapp_m4.ui.home.TaskModel

@Database (entities = [TaskModel::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}