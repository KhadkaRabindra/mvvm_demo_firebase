package com.maxx.nordvaest.utils.extensions

import android.widget.TextView

/**
 * Created by rakezb on 5/10/2019.
 */

fun List<TextView>.makeLabelRequired(){
    this.forEach {
        it.makeLabelRequired()
    }
}

fun TextView.makeLabelRequired(){
    this.text = "${this.text} *"
}