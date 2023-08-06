package com.homework.homeworkcase.servis




import com.homework.homeworkcase.model.DeviceModem
import io.reactivex.rxjava3.core.Single
import retrofit2.Call


import retrofit2.http.GET

interface DeviceApi {
        //https://veramobile.mios.com/test_android/items.test
    @GET("test_android/items.test")
    fun getDevices():Single<DeviceModem>
}