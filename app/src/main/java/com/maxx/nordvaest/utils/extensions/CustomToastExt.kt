package com.maxx.nordvaest.utils.extensions

import android.content.Context
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.maxx.nordvaest.R
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.support.v4.act

fun Context.showCustomToast(message: String) {
    val inflater = layoutInflater
    val layout = inflater.inflate(R.layout.toast_layout, null)
    val text = layout.findViewById(R.id.text) as TextView
    text.text = message

    val toast = Toast(applicationContext)
    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
    toast.duration = Toast.LENGTH_SHORT
    toast.view = layout
    toast.show()
}