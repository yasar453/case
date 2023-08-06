package com.homework.homeworkcase.servis


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.homework.homeworkcase.model.DeviceModemData
@Dao
interface ModemDeviceDao {

    @Insert
    suspend fun saveDeviceModemDataRoom(vararg deviceModemData: DeviceModemData):List<Long>
    @Query("SELECT * FROM DeviceModemData")
    suspend fun getAllDeviceModemDataRoom():List<DeviceModemData>
   @Query(value="SELECT * FROM DeviceModemData WHERE uid=:modemid")
    suspend fun selectModemRoom(modemid:Int):DeviceModemData
    @Query(value="DELETE  FROM DeviceModemData WHERE uid=:modemid")
    suspend fun deleteModemRoom(modemid:Int):Int
    @Query("UPDATE DeviceModemData SET DeviceTitleName = :newName WHERE uid = :userId")
    suspend fun updateModemRoom(userId: Int, newName: String)
    @Query("DELETE FROM DeviceModemData")
    suspend fun deleteAllModemRoom()



}