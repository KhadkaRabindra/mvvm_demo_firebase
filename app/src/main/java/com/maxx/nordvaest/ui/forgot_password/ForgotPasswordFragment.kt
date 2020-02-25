package com.maxx.nordvaest.ui.forgot_password

import androidx.lifecycle.Observer
import com.maxx.nordvaest.R
import com.maxx.nordvaest.core.BaseFragment
import com.maxx.nordvaest.utils.extensions.makeLabelRequired
import com.maxx.nordvaest.utils.extensions.setSafeOnClickListener
import com.maxx.nordvaest.utils.extensions.toast

class ForgotPasswordFragment : BaseFragment<ForgotPasswordFragmentViewModel, com.maxx.nordvaest.databinding.FragmentForgotPasswordBinding>(ForgotPasswordFragmentViewModel::class.java) {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_forgot_password
    }

    override fun initViewModel(viewModel: ForgotPasswordFragmentViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun initView() {
        super.initView()
        listOf(mBinding.newPasswordLabel, mBinding.confirmPasswordLabel).makeLabelRequired()
        mBinding.submitButton.setSafeOnClickListener {
            validator?.toValidate()
        }
        observers()
    }

    private fun observers() {
        viewModel.isPasswordResetSuccess.observe(this, Observer {
            if(it){
                toast("Password reset success.")
                activity?.finish()
            }
        })
    }

    override fun onValidationSucceeded() {
        super.onValidationSucceeded()
        if(viewModel.newPassword.value != viewModel.confirmPassword.value){
            toast("New password and confirm password does not match")
            return
        }
        viewModel.requestForgotPassword()
    }


    companion object{
        fun newInstance() : ForgotPasswordFragment{
            return ForgotPasswordFragment()
        }
    }
}