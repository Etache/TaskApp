package com.example.taskapp_m4.utils

import android.content.Context

class Preferences(context: Context) {
    private val sharedPreference = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun setBoardingShowed(isShow: Boolean) {
        sharedPreference.edit().putBoolean("onBoard", isShow).apply()
    }

    fun isBoardingShowed(): Boolean {
        return sharedPreference.getBoolean("onBoard", false)
    }

    fun setEditTextUsername(username: String) {
        sharedPreference.edit().putString("editText", username).apply()
    }

    fun getEditTextValue(): String? {
        return sharedPreference.getString("editText", null)
    }
}