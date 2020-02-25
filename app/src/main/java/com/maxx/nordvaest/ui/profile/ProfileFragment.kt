package com.maxx.nordvaest.ui.profile

import android.os.Bundle
import android.view.View
import com.maxx.nordvaest.R
import com.maxx.nordvaest.core.BaseFragment
import com.maxx.nordvaest.ui.home.HomeActivity

class ProfileFragment :
    BaseFragment<ProfileFragmentViewModel, com.maxx.nordvaest.databinding.FragmentProfileBinding>(
        ProfileFragmentViewModel::class.java
    ) {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_profile
    }

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun initViewModel(viewModel: ProfileFragmentViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity is HomeActivity)
            (activity as HomeActivity).setToolbarTitle(getString(R.string.profile))

        /*QR Data*/
        viewModel.setData()
        viewModel.getFireBaseData()
    }


}