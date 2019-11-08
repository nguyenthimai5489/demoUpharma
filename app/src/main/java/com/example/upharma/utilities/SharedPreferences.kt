package com.example.upharma.utilities

import android.content.Context

object SharedPreferences {

    val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME"
    private val USER_CODE = "USER_CODE"
    private val TOKEN = "TOKEN"

    fun saveUserCode(context: Context,userCode: String){
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(USER_CODE,userCode)
        editor.apply()
    }

    fun saveToken(context: Context,token: String){
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(TOKEN,token)
        editor.apply()
    }

    fun getUserCode(context: Context) : String{
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val userCode = sharedPref.getString(USER_CODE,"")
        if (userCode != null)
            return userCode
        return ""
    }

    fun getToken(context: Context) : String{
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val token = sharedPref.getString(TOKEN,"")
        if (token != null)
            return token
        return ""
    }

    fun logout(context: Context){
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
}