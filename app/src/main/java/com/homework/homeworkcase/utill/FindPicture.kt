package com.homework.homeworkcase.utill

class FindPicture {

    companion object{
        var modemPicture="vera_edge_big"
        fun find(platform:String){
            when (platform) {
                Platform.P1.platform -> modemPicture=Pictures.SercommG450.picture
                Platform.P2.platform -> modemPicture=Pictures.SercommG550.picture
                Platform.P3.platform -> modemPicture=Pictures.MiCasaVerdeVeraLite.picture
                Platform.P4.platform -> modemPicture=Pictures.SercommNA900.picture
                Platform.P5.platform -> modemPicture=Pictures.SercommNA301.picture
                Platform.P6.platform -> modemPicture=Pictures.SercommNA930.picture
            }

        }

    }
}
enum class Platform(val platform:String){
    P1("Sercomm G450"),
    P2("Sercomm G550"),
    P3("MiCasaVerde VeraLite"),
    P4("Sercomm NA900"),
    P5("Sercomm NA301"),
    P6("Sercomm NA930"),

}
enum class Pictures(val picture:String){
    SercommG450("vera_plus_big"),
    SercommG550("vera_secure_big"),
    MiCasaVerdeVeraLite("vera_edge_big"),
    SercommNA900("vera_edge_big"),
    SercommNA301("vera_edge_big"),
    SercommNA930("vera_edge_big")
}
enum class Flaginf(val flagName:String){
    Refresh("isRefresh"),
    Editable("isEditeable"),
    FirstLogin("isFirstlogin"),
    FirstEnter("firstEnter")
}


