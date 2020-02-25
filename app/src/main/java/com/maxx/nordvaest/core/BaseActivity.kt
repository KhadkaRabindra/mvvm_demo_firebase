package com.maxx.nordvaest.core

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.maxx.nordvaest.R
import com.maxx.nordvaest.utils.ActivityUtils
import com.maxx.nordvaest.utils.extensions.toast
import com.maxx.nordvaest.utils.validationLib.ValidationListener
import com.maxx.nordvaest.utils.validationLib.Validator
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) :
    AppCompatActivity(),
    ValidationListener {

    private var mProgressDialogMsg: String? = null
    private var mProgressDialog: ProgressDialog? = null
    var validator: Validator? = null

    protected var imm: InputMethodManager? = null

    @LayoutRes
    abstract fun getLayoutRes(): Int

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    val viewModel by lazy {
        ViewModelProviders.of(this).get(mViewModelClass)
    }

    /**
     * If you want to inject Dependency Injection
     * on your activity, you can override this.
     */
    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel(viewModel)

        super.onCreate(savedInstanceState)

        validator = Validator(binding)
        setupValidator()
        onInject()

        baseObservers()

        mProgressDialogMsg = getString(R.string.please_wait)
        setupProgressDialog()

        initView()

        //track fragment's back stack changed listener
        trackFragmentTransactionState()
    }

    private fun trackFragmentTransactionState() {
        //tracks the fragments pops and push stack
        //we need this to update the toolbar title
        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager
                .findFragmentById(R.id.container)
            currentFragment?.onResume()
        }
    }

    private fun baseObservers() {
        viewModel.errorMessage.observe(this, Observer {
            viewModel.toastMessage.postValue(it)
        })

        viewModel.toastMessage.observe(this, Observer {
            toast(it)
        })

        /*viewModel.progressDialogMessage.observe(this, Observer {
            mProgressDialogMsg = it
        })*/

        viewModel.loadingVisibility.observe(this, Observer {
            if (it == View.VISIBLE)
                showProgressDialog()
            else if (it == View.GONE)
                hideProgressDialog()
        })
    }

    open fun initView() {

    }

    /**
     *
     *  You need override this method.
     *  And you need to set viewModel to binding: binding.viewModel = viewModel
     *
     */
    abstract fun initViewModel(viewModel: VM)

    private fun setupValidator() {
        validator = Validator(binding)
        validator?.apply {
            setValidationListener(this@BaseActivity)
            enableFormValidationMode()
        }
    }

    override fun onValidationError() {}

    override fun onValidationSucceeded() {}

    fun changeFragment(fragment: Fragment, cleanStack: Boolean = false, addToBackStack: Boolean = true) {
        ActivityUtils.changeFragment(this, fragment, cleanStack, addToBackStack)
    }

    fun addFragment(fragment: Fragment, cleanStack: Boolean = false, addToBackStack: Boolean = true) {
        ActivityUtils.addFragment(this, fragment, cleanStack, addToBackStack)
    }

    protected override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }


    private fun setupProgressDialog() {
        if (applicationContext != null && !isDestroyed) {
            val message = /*message*/getString(R.string.please_wait)
            mProgressDialog = ProgressDialog(this, R.style.MyAlertDialogStyle)
//        if (TextUtils.isEmpty(message))
//            message = "Please Wait..."
            mProgressDialog!!.setMessage(message)
            mProgressDialog!!.setCancelable(false)
        }
    }

    private fun showProgressDialog() {
        mProgressDialog?.show()
    }

    private fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing)
            mProgressDialog?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgressDialog()
    }

    fun hideKeyBoard(editText: EditText?) {
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(editText?.windowToken, 0)//hide keyboard
    }

}