package com.maxx.nordvaest.utils.extensions

fun String?.isNotEmptyorNull(): Boolean {
    return this?.isNullOrEmpty() == false
}