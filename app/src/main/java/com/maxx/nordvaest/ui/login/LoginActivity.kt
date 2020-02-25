package com.maxx.nordvaest.ui.login

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.maxx.nordvaest.R
import com.maxx.nordvaest.core.BaseActivity
import com.maxx.nordvaest.core.ToolbarCommonActivity
import com.maxx.nordvaest.data.local.ViewType
import com.maxx.nordvaest.ui.home.HomeActivity
import com.maxx.nordvaest.utils.extensions.setSafeOnClickListener
import com.maxx.nordvaest.utils.extensions.showCustomToast
import com.maxx.nordvaest.utils.extensions.toast
import org.jetbrains.anko.sdk27.coroutines.onEditorAction

class LoginActivity : BaseActivity<LoginActivityViewModel, com.maxx.nordvaest.databinding.ActivityLoginBinding>(
        LoginActivityViewModel::class.java
    ) {
    override fun getLayoutRes(): Int {
        return R.layout.activity_login
    }

    override fun initViewModel(viewModel: LoginActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun initView() {
        super.initView()

        checkLoginStatus()
        //listOf(binding.emailLabel, binding.passwordLabel).makeLabelRequired()

        /*binding.agentButton.setSafeOnClickListener {
            openRegistrationActivity()
        }*/

        binding.loginButton.setSafeOnClickListener {
            validator?.toValidate()
        }
        binding.accountLayout.setSafeOnClickListener {
            ToolbarCommonActivity.start(this, ViewType.REGISTRATION)
        }

        binding.passwordEditText.setOnEditorActionListener( object :  TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyBoard(binding.passwordEditText)
                    return true
                }
                return false
            }

        })
        observers()
    }

    private fun observers() {
        viewModel.loginSuccess.observe(this, Observer {
            if (it)
                openHomeActivity()
            else
                showCustomToast("Feil brukernavn eller passord.")
        })
    }

    override fun onValidationSucceeded() {
        super.onValidationSucceeded()
        viewModel.requestLogin()
    }

    private fun checkLoginStatus() {
        if (viewModel.getLoginStatus() == true)
            openHomeActivity()
    }

    private fun openHomeActivity() {
        HomeActivity.start(this)
        finish()
    }

    private fun openRegistrationActivity() {
        ToolbarCommonActivity.start(this, ViewType.REGISTRATION)
    }

}