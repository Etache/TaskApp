package com.example.taskapp_m4.utils

import android.content.Context
import com.example.taskapp_m4.R

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

//    var etUsername: String
//        set(username) = sharedPreference.edit().putString("etUsername", username).apply()
//        get() = sharedPreference.getString("etUsername", null).toString()

    var imgUri: String?
        set(value) = sharedPreference.edit().putString("imgUri", value).apply()
        get() = sharedPreference.getString("imgUri", "https://www.technol.si/wp-content/uploads/2018/11/default-image1.jpg")
}
