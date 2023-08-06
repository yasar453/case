package com.homework.homeworkcase.utill

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit


import androidx.preference.PreferenceManager
import com.homework.homeworkcase.model.Flag


class SharedPref {

    companion object{

        private var sharedPreferences: SharedPreferences?=null

        @Volatile private var instance: SharedPref?=null
        private val lock=Any()
        operator fun invoke(context: Context)= instance ?: synchronized(lock){
            instance?: makeSharedPreferences(context).also {
                instance=it
            }
        }

        private fun makeSharedPreferences(context: Context):SharedPref {
            sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPref()
        }

    }
    fun savesharedPreferences(flag: Flag){
       sharedPreferences?.edit(commit=true) {
        putBoolean(flag.dataName,flag.dataFlag)
        }
    }
    fun getsharedPreferences(flagName:String)= sharedPreferences?.getBoolean(flagName,false)

}