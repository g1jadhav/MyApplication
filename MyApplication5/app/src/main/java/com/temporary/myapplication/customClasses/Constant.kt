package com.temporary.myapplication.customClasses

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

object Constant {

    const val DB_VERSION = 1
    const val DB_NAME = "RocketList.db"
    private const val IS_DATA_STORED = "isStored"

    @JvmStatic
    fun dataIsStored(mContext: Context, value: Boolean) {
        val sharedPreferences =
            mContext.getSharedPreferences(IS_DATA_STORED, AppCompatActivity.MODE_PRIVATE)

        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("storedOrNot", value)
        editor.apply()
    }

    @JvmStatic
    fun checkDataIsStored(mContext: Context): Boolean {
        val sharedPreferences =
            mContext.getSharedPreferences(IS_DATA_STORED, AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getBoolean("storedOrNot", false)
    }
}