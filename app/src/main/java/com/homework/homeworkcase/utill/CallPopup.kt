package com.homework.homeworkcase.utill

import android.app.AlertDialog
import android.widget.Toast
import android.content.Context
import com.homework.homeworkcase.model.PopUp
import com.homework.homeworkcase.servis.CallRecylerViewint

class CallPopup(val handler:CallRecylerViewint) {


    fun deletePopup(context: Context,popUp: PopUp){
        val builder = AlertDialog.Builder(context)
        val modemName=popUp.modemName
        builder.setTitle("Delete")
        builder.setMessage("Do you want to delete  $modemName ?")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            handler.PopupCall(popUp)
        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->

        }
        builder.show()

    }
}