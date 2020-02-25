package com.maxx.nordvaest.utils

import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.maxx.nordvaest.utils.Constants.Companion.NotificationChannelId
import com.maxx.nordvaest.utils.Constants.Companion.NotificationChannelName
import java.io.IOException


class CommonUtils {

    companion object {
        fun loadJSONFromAsset(context: Context?, fileName: String): String? {
            var json: String? = null
            try {
                val inputStream = context?.assets?.open(fileName)
                val size = inputStream?.available()
                if (size != null && size > 0) {
                    val buffer = ByteArray(size)
                    inputStream?.read(buffer)
                    inputStream?.close()
                    json = String(buffer, Charsets.UTF_8)
                }
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }

            return json
        }

        fun setCountryFlag(imageView: ImageView?, code: String?) {
            var countryCode = getCountryFlagCode(code)

            val resourceId =
                imageView?.context?.resources?.getIdentifier(countryCode, "drawable", imageView.context.packageName)
            if (resourceId != null)
                imageView?.setImageResource(resourceId)
        }

        fun getCountryFlagCode(code: String?): String? {
            var countryCode = code
            if (code.equals("do"))
                countryCode = "dom"
            return countryCode
        }

        fun hideKeyboard(activity: Activity?) {
            val view = activity?.currentFocus
            view?.let { v ->
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.let { it.hideSoftInputFromWindow(v.windowToken, 0) }
            }
        }

        fun showKeyboard(activity: Activity) {
            val view = activity.currentFocus
            val methodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            assert(view != null)
            methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }


        fun getNotificationManager(context: Context): NotificationManager {

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                // The user-visible description of the channel.
                val description = "Notifications regarding our products"
                val importance = NotificationManager.IMPORTANCE_HIGH as Int
                val mChannel = NotificationChannel(NotificationChannelId, NotificationChannelName, importance)
                // Configure the notification channel.
                mChannel.description = description
                // Sets whether notifications posted to this channel should display notification lights
                mChannel.enableLights(true)
                // Sets whether notification posted to this channel should vibrate.
                mChannel.enableVibration(true)
                // Sets whether notifications posted to this channel appear on the lockscreen or not
                mChannel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

                // Sets the notification light color for notifications posted to this
                // channel, if the device supports this feature.
                mChannel.lightColor = Color.RED
                notificationManager.createNotificationChannel(mChannel)
            }

            return notificationManager
        }
    }
}