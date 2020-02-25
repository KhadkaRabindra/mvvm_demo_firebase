package com.maxx.nordvaest.utils.extensions

import android.util.Log
import com.maxx.nordvaest.BuildConfig

fun Any.logE(message: String) = if (BuildConfig.DEBUG) Log.e(this::class.java.simpleName, message) else null

fun Any.logD(message: String) = if (BuildConfig.DEBUG) Log.d(this::class.java.simpleName, message) else null

fun Any.logV(message: String) = if (BuildConfig.DEBUG) Log.v(this::class.java.simpleName, message) else null

fun Any.logW(message: String) = if (BuildConfig.DEBUG) Log.w(this::class.java.simpleName, message) else null

fun Any.logI(message: String) = if (BuildConfig.DEBUG) Log.i(this::class.java.simpleName, message) else null

fun Any.emptyString() = ""