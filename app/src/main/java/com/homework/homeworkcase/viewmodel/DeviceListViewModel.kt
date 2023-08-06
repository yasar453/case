package com.homework.homeworkcase.viewmodel



import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.homework.homeworkcase.model.DeviceModem
import com.homework.homeworkcase.model.DeviceModemData
import com.homework.homeworkcase.model.Flag
import com.homework.homeworkcase.model.PopUp
import com.homework.homeworkcase.servis.DeviceApiServis
import com.homework.homeworkcase.servis.ModemDeviceDatabase
import com.homework.homeworkcase.utill.FindPicture
import com.homework.homeworkcase.utill.Flaginf
import com.homework.homeworkcase.utill.SharedPref
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DeviceListViewModel(application: Application):MainViewModel(application) {
    val ERRORTAG = "Retrofit data gelmedi"
    var deviceList = MutableLiveData<List<DeviceModemData>>()
    val isDataLoading = MutableLiveData<Boolean>()
    private val deviceApiServis = DeviceApiServis()
    private val disposable = CompositeDisposable()//rxjava
    private val sharedPref = SharedPref(getApplication())
    private var firstEnter=true
    fun getModemData() {
        if (!isFirstLogin()!!) {
            loadDevicesFromApi()
        }else{
            val refresh=sharedPref.getsharedPreferences(Flaginf.Refresh.flagName)
            val editable=sharedPref.getsharedPreferences(Flaginf.Refresh.flagName)
            if (refresh!!) {
                loadDevicesFromApi()
            }
            if (editable!! || firstEnter){
                    getApiDataSqlite()
            }
            firstEnter=false
        }

    }
    private fun loadDevicesFromApi() {
        //eger uygulama cok fazla istek yaparsa hafıza sorunu yasamamak için disposable kullanırız
        // disposable tek seferlik kullanmalık atabilecegimiz data turleridir ,rxjava ile gelir
        isDataLoading.value = true
        disposable.add(
            deviceApiServis.getDevices()
                .subscribeOn(Schedulers.newThread())//dondurulen single objesıne kayıt ol,yeni bir thread içinde
                .observeOn(AndroidSchedulers.mainThread())// MAİN treadde datayı gözlemle
                .subscribeWith(object : DisposableSingleObserver<DeviceModem>() {
                    override fun onSuccess(t: DeviceModem) {
                        isDataLoading.value = false
                         saveApiDataSqlite(t.Devices)
                    }

                    override fun onError(e: Throwable) {
                        Log.e(ERRORTAG, "Hata :$e")
                        e.printStackTrace()
                    }

                })
        )

    }
    private fun saveApiDataSqlite(list: List<DeviceModemData>) {
        launch {
             val databaseobje=ModemDeviceDatabase(getApplication()).modemDeviceDao()
              databaseobje.deleteAllModemRoom()

            //burada dataları normalde hepsini tek tek vermek için kullanırız *list.toTypedArray()
            // fakat resimleri ve title nameleri localden alacagimiz için for loop ile aktaracagiz

            for ((x, a) in list.withIndex()) {
                a.Platform?.let { FindPicture.find(it) }
                a.DeviceTitleName = "Home Number " + (x+1)
                a.DevicePictures = FindPicture.modemPicture
                a.uid = x + 1
                 databaseobje.saveDeviceModemDataRoom(a)
            }

            deviceList.value = list
        }
        sharedPref.savesharedPreferences(Flag(Flaginf.Refresh.flagName, false))
    }
    private fun getApiDataSqlite() {
        launch {
            val databaseobje = ModemDeviceDatabase(getApplication()).modemDeviceDao()
            val list = databaseobje.getAllDeviceModemDataRoom()
            deviceList.value = list
        }


    }
    fun updateShared(flag: Flag) {
        sharedPref.savesharedPreferences(flag)
    }
    fun handlePopUp(popUp: PopUp){
        launch {
            val databaseobje = ModemDeviceDatabase(getApplication()).modemDeviceDao()
            databaseobje.deleteModemRoom(popUp.modemUid)
            updateShared(Flag(Flaginf.Editable.flagName,true))
            getModemData()

        }



    }
    fun isFirstLogin(): Boolean? {
        val firstlogin=sharedPref.getsharedPreferences(Flaginf.FirstLogin.flagName)
        if (firstlogin == null || firstlogin==false) {
            updateShared(Flag(Flaginf.FirstLogin.flagName,true))
        }
        return firstlogin
    }





}







 /* /*// Eger  retrofit call kullanirsak
 deviceApiServis.getDevices().enqueue(object : Callback<DeviceModem> {
        override fun onResponse(call: Call<DeviceModem>, response: Response<DeviceModem>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    //deviceList.value=it.Devices
                    saveApiDataSqlite(it.Devices)
                }
            }
        }

        override fun onFailure(call: Call<DeviceModem>, t: Throwable) {
            Log.e(ERRORTAG,"Hata :$ t"   )
        }

    })*/
}*/


