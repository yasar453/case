package com.homework.homeworkcase.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.homework.homeworkcase.model.DeviceModemData
import com.homework.homeworkcase.model.Flag
import com.homework.homeworkcase.servis.ModemDeviceDatabase
import com.homework.homeworkcase.utill.Flaginf
import com.homework.homeworkcase.utill.SharedPref
import kotlinx.coroutines.launch


class ModeminfoViewModel(application: Application) :MainViewModel(application){
    val modeDeviceData=MutableLiveData<DeviceModemData>()
    val editable=MutableLiveData<Boolean>()
   private val sharedPref=SharedPref(getApplication())
    fun getDataWithid(uuid: Int){
        launch {
            val databaseobje= ModemDeviceDatabase(getApplication()).modemDeviceDao()
            val list=databaseobje.selectModemRoom(uuid)
            modeDeviceData.value=list
        }
    }
    fun isEditable(){
        editable.value=false
        val res=sharedPref.getsharedPreferences(Flaginf.Editable.flagName)
        if (res!!) {
            editable.value=true
        }
    }
    fun saveSpeacialTitle(newTitle:String){
        launch {
            val databaseobje= ModemDeviceDatabase(getApplication()).modemDeviceDao()
            databaseobje.updateModemRoom(modeDeviceData.value!!.uid,newTitle)
        }
        sharedPref.savesharedPreferences(Flag(Flaginf.Refresh.flagName,false))
    }
    fun upDateShared(flag: Flag){
        sharedPref.savesharedPreferences(flag)
    }







}