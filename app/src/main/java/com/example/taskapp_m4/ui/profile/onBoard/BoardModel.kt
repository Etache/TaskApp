package com.example.taskapp_m4.ui.profile.onBoard
import java.io.Serializable

data class BoardModel(
    var img: Int,
    var title: String,
    var description: String,
    var isLast: Boolean
) : Serializable
