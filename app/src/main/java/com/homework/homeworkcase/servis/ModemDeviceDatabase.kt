package com.homework.homeworkcase.servis



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.homework.homeworkcase.model.DeviceModemData

@Database(entities = arrayOf(DeviceModemData::class), version = 1)
abstract class ModemDeviceDatabase:RoomDatabase() {
abstract fun modemDeviceDao():ModemDeviceDao

    companion object{

            @Volatile private var instance:ModemDeviceDatabase?=null
            private val lock=Any()
            operator fun invoke(context: Context)= instance ?: synchronized(lock){
                instance?: makeRoomDatabase(context).also {
                    instance=it
                }
            }

        private fun makeRoomDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            ModemDeviceDatabase::class.java,
            "ModemDatabase"
        ).build()

    }





}


