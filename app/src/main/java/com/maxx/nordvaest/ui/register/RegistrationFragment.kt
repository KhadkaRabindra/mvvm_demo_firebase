package com.maxx.nordvaest.ui.register

import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.maxx.nordvaest.R
import com.maxx.nordvaest.core.BaseFragment
import com.maxx.nordvaest.core.ToolbarCommonActivity
import com.maxx.nordvaest.data.local.ViewType
import com.maxx.nordvaest.ui.home.HomeActivity
import com.maxx.nordvaest.utils.extensions.setSafeOnClickListener
import com.maxx.nordvaest.utils.extensions.showCustomToast
import com.maxx.nordvaest.utils.extensions.toast
import org.jetbrains.anko.support.v4.act

class RegistrationFragment :
    BaseFragment<RegistrationFragmentViewModel, com.maxx.nordvaest.databinding.FragmentRegistrationBinding>(
        RegistrationFragmentViewModel::class.java
    ) {
    override fun initViewModel(viewModel: RegistrationFragmentViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_registration
    }

    override fun onFragmentVisible() {
        super.onFragmentVisible()
        if (activity is ToolbarCommonActivity) {
            (activity as ToolbarCommonActivity).hideToolBar()
        }
    }

    override fun initView() {
        super.initView()

        mBinding.registerButton.setSafeOnClickListener {
            validator?.toValidate()
        }

        mBinding.accountLayout.setSafeOnClickListener {
            activity?.finish()
        }

        observers()

    }

    private fun observers() {
        viewModel.registerSuccess.observe(this, Observer {
            if (it) {
                openHomeActivity()
            }
        })
    }

    private fun openHomeActivity() {
        activity?.showCustomToast("Registreringen er godkjent")
        HomeActivity.start(activity)
        finish()
    }



    override fun onValidationSucceeded() {
        super.onValidationSucceeded()
        if (viewModel.password.value?.equals(viewModel.confirmPassword.value)!!)
            viewModel.register()
        else
            activity?.showCustomToast("Password and Confirm Password doesn't matches.")
    }


    companion object {
        fun newInstance(): RegistrationFragment {
            return RegistrationFragment()
        }
    }
}