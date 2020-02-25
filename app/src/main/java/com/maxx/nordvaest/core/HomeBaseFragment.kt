package com.maxx.nordvaest.core

import androidx.databinding.ViewDataBinding

/**
 * Created by rakezb on 3/6/2019.
 */
abstract class HomeBaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>): BaseFragment<VM, DB>(mViewModelClass) {

    private var isVisibleToUser: Boolean = false
    private var isStarted = false

    override fun viewStarter() {

    }

    override fun onStart() {
        super.onStart()
        isStarted = true
        if (isVisibleToUser && isStarted) {
            //view visible here
            initView()
        }
    }

    override fun setUserVisibleHint(visible: Boolean) {
        super.setUserVisibleHint(visible)
        isVisibleToUser = visible
        if (visible && isStarted) {
            //view visible here
            initView()
        }
    }

    override fun onStop() {
        super.onStop()
        isStarted = false
    }

}