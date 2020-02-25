package com.maxx.nordvaest.utils

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class KeyboardHelper {

    companion object {

        /**
         * Hide keyboard on view click except edittext
         */
        fun setupKeyboardOnUI(activity: Activity?, view: View) {
            //Set up touch listener for non-text box views to hide keyboard.
            if (view !is EditText) {
                view.setOnTouchListener { v, event ->
                    hideSoftKeyboard(activity)
                    false
                }
            }
            //If a layout container, iterate over children
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    val innerView = view.getChildAt(i)
                    setupKeyboardOnUI(activity, innerView)
                }
            }
        }

        private fun hideSoftKeyboard(activity: Activity?) {
            val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity?.getCurrentFocus()?.getWindowToken(), 0)
        }
    }
}