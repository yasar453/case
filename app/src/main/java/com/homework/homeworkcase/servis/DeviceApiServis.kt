package com.homework.homeworkcase.servis



import com.homework.homeworkcase.model.DeviceModem
import com.homework.homeworkcase.model.DeviceModemData
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DeviceApiServis {

    private val BASE_URL="https://veramobile.mios.com/"
    private val deviceapi=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())//rxjava kullanırsak ekleriz
        .build()
        .create(DeviceApi::class.java)
    fun getDevices(): Single<DeviceModem> {
       return deviceapi.getDevices()
    }


}