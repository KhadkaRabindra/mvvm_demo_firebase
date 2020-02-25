package com.maxx.nordvaest.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class DialogHelper {
    companion object {
        fun showDialog(context: Context, title: String, message: String,
                       positiveBtnText: String, negativeBtnText: String?,
                       positiveBtnClickListener: DialogInterface.OnClickListener,
                       negativeBtnClickListener: DialogInterface.OnClickListener?): AlertDialog {
            val builder = AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setCancelable(true)
                    .setPositiveButton(positiveBtnText, positiveBtnClickListener)
            if (negativeBtnText != null)
                builder.setNegativeButton(negativeBtnText, negativeBtnClickListener)
            val alert = builder.create()
            alert.show()
            return alert
        }

    }
}