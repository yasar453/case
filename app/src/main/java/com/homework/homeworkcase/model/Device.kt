package com.homework.homeworkcase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class DeviceModemData(
                @ColumnInfo(name="PK_Device")
                 @SerializedName("PK_Device")
                  val PK_Device:Int?,
                @ColumnInfo(name="MacAddress")
                 @SerializedName("MacAddress")
                 val MacAddress:String?,
                @ColumnInfo(name="PK_DeviceType")
                 @SerializedName("PK_DeviceType")
                 val PK_DeviceType:Int?,
                @ColumnInfo(name="PK_DeviceSubType")
                 @SerializedName("PK_DeviceSubType")
                 val PK_DeviceSubType:Int?,
                @ColumnInfo(name="Firmware")
                 @SerializedName("Firmware")
                 val Firmware:String?,
                @ColumnInfo(name="Server_Device")
                 @SerializedName("Server_Device")
                 val Server_Device:String?,
                @ColumnInfo(name="Server_Event")
                 @SerializedName("Server_Event")
                 val Server_Event:String?,
                @ColumnInfo(name="Server_Account")
                 @SerializedName("Server_Account")
                 val Server_Account:String?,
                @ColumnInfo(name="InternalIP")
                 @SerializedName("InternalIP")
                 val InternalIP:String?,
                @ColumnInfo(name="LastAliveReported")
                 @SerializedName("LastAliveReported")
                 val LastAliveReported:String?,
                @ColumnInfo(name="Platform")
                 @SerializedName("Platform")
                 val Platform:String?,
                @ColumnInfo(name="DeviceTitleName")
                @SerializedName("DeviceTitleName")
                var DeviceTitleName:String?,
                @ColumnInfo(name="DevicePictures")
                @SerializedName("DevicePictures")
                var DevicePictures:String?
                  )    {
            @PrimaryKey(autoGenerate = true)
            var uid:Int=0
}
data class DeviceModem(val Devices:List<DeviceModemData>){

}
