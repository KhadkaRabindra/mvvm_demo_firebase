package com.maxx.nordvaest.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.maxx.nordvaest.utils.ActivityUtils
import com.maxx.nordvaest.R

abstract class AppCompatBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        initView()
    }

    open fun initView() {}

    abstract val layoutId: Int

    fun changeFragment(
            fragment: Fragment,
            cleanStack: Boolean = false,
            addToBackStack: Boolean = true,
            containerId: Int = R.id.container) {
        ActivityUtils.changeFragment(this, fragment, cleanStack, addToBackStack, containerId)
    }

    fun addFragment(
            fragment: Fragment,
            cleanStack: Boolean = false,
            addToBackStack: Boolean = true,
            containerId: Int = R.id.container) {
        ActivityUtils.changeFragment(this, fragment, cleanStack, addToBackStack, containerId)
    }
}