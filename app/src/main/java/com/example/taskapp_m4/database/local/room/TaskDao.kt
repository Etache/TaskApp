package com.example.taskapp_m4.database.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taskapp_m4.ui.home.TaskModel

@Dao
interface TaskDao {

     @Insert
     fun insert(task: TaskModel?)

     @Query ("SELECT * FROM TaskModel")
     fun getAllTasks(): List<TaskModel?>?

}