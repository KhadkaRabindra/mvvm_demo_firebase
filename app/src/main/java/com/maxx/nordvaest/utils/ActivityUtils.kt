package com.maxx.nordvaest.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.maxx.nordvaest.R

class ActivityUtils {
    companion object {

        fun changeFragment(activity: AppCompatActivity?, fragment: Fragment,
                           cleanStack: Boolean = false,
                           addToBackStack: Boolean = true,
                           containerId: Int = R.id.container) {
            val ft = activity?.supportFragmentManager?.beginTransaction()
            if (cleanStack) {
                val manager = activity?.supportFragmentManager
                if (manager!=null && manager.backStackEntryCount > 0) {
                    val first = manager?.getBackStackEntryAt(0)
                    manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                }
            }
            ft?.replace(containerId, fragment)
            if (addToBackStack)
                ft?.addToBackStack(null)
            ft?.commitAllowingStateLoss()
        }

        fun addFragment(activity: AppCompatActivity?, fragment: Fragment,
                           cleanStack: Boolean = false,
                           addToBackStack: Boolean = true,
                           containerId: Int = R.id.container) {
            val ft = activity?.supportFragmentManager?.beginTransaction()
            if (cleanStack) {
                val manager = activity?.supportFragmentManager
                if (manager!=null && manager.backStackEntryCount > 0) {
                    val first = manager?.getBackStackEntryAt(0)
                    manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                }
            }
            ft?.add(containerId, fragment)
            if (addToBackStack)
                ft?.addToBackStack(null)
            ft?.commitAllowingStateLoss()
        }
    }
}