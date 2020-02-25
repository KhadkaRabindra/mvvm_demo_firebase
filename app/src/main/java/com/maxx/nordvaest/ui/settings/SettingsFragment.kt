package com.maxx.nordvaest.ui.settings

import android.os.Bundle
import android.view.View
import com.maxx.nordvaest.R
import com.maxx.nordvaest.core.BaseFragment
import com.maxx.nordvaest.core.ToolbarCommonActivity
import com.maxx.nordvaest.databinding.FragmentSetttingsBinding
import com.maxx.nordvaest.utils.extensions.setSafeOnClickListener

class SettingsFragment : BaseFragment<SettingsViewModel, FragmentSetttingsBinding>(SettingsViewModel::class.java) {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_setttings
    }

    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }

    override fun initViewModel(viewModel: SettingsViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity is ToolbarCommonActivity)
            (activity as ToolbarCommonActivity).setToolbarTitle(getString(R.string.instillinger))

        mBinding.logoutLL.setSafeOnClickListener {
            mBinding.viewModel?.userRepository?.doLogout(activity!!)
        }
    }
}