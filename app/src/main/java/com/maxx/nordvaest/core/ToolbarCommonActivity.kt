package com.maxx.nordvaest.core

import android.content.Context
import android.view.MenuItem
import com.maxx.nordvaest.R
import com.maxx.nordvaest.data.local.ViewType
import com.maxx.nordvaest.ui.forgot_password.ForgotPasswordFragment
import com.maxx.nordvaest.ui.profile.ProfileFragment
import com.maxx.nordvaest.ui.qr_scanner.QRScannerFragment
import com.maxx.nordvaest.ui.register.RegistrationFragment
import com.maxx.nordvaest.ui.settings.SettingsFragment
import com.maxx.nordvaest.utils.extensions.hide
import kotlinx.android.synthetic.main.activity_toolbar_common.*
import kotlinx.android.synthetic.main.activity_toolbar_common.view.*
import org.jetbrains.anko.startActivity

class ToolbarCommonActivity : BaseActivity<ToolbarCommonActivityViewModel, com.maxx.nordvaest.databinding.ActivityToolbarCommonBinding>(
    ToolbarCommonActivityViewModel::class.java
) {

    private var mViewType: ViewType? = null

    override fun getLayoutRes() = R.layout.activity_toolbar_common

    override fun initViewModel(viewModel: ToolbarCommonActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun initView() {
        super.initView()
        setupToolbar()
        handleRouter(intent.getSerializableExtra(INTENT_EXTRA_VIEW_TYPE) as ViewType?)
    }

    private fun handleRouter(viewType: ViewType?) {
        when (viewType) {
            ViewType.REGISTRATION -> changeFragment(RegistrationFragment.newInstance(), addToBackStack = false)
            ViewType.FORGOT_PASSWORD -> changeFragment(ForgotPasswordFragment.newInstance(), addToBackStack = false)
            ViewType.QR -> changeFragment(QRScannerFragment.newInstance(), addToBackStack = false)
            ViewType.SETTINGS -> changeFragment(SettingsFragment.newInstance(), addToBackStack = false)
            ViewType.PROFILE -> changeFragment(ProfileFragment.newInstance(), addToBackStack = false)
            /*ViewType.RESET_PASSWORD -> changeFragment(ResetPasswordFragment.newInstance(), addToBackStack = false)
            ViewType.TRANSACTION_DETAIL -> changeFragment(
                TrasactionDetailFragment.newInstance(
                    intent.getParcelableExtra(
                        INTENT_EXTRA_TRANSACTION_DETAIL
                    ) as InitTransactionDetailResponse?
                ), addToBackStack = false
            )
            ViewType.MY_ACCOUNT -> changeFragment(MyAccountFragment.newInstance(), addToBackStack = false)
            ViewType.PREFUND -> changeFragment(PrefundFragment.newInstance(), addToBackStack = false)
            ViewType.PREFUND_HISTORY -> changeFragment(PrefundHistoryFragment.newInstance(), addToBackStack = false)
            ViewType.STATEMENTS -> changeFragment(StatementsFragment.newInstance(), addToBackStack = false)
            ViewType.BANK_LIST -> changeFragment(BanksFragment.newInstance(), addToBackStack = false)*/
        }
    }

    fun setToolbarTitle(title: String) {
        toolBar.toolbarTitle.text = title
    }


    private fun setupToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setDisplayShowTitleEnabled(false)
            setHomeAsUpIndicator(R.drawable.ic_navigate_before)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun hideToolBar() {
        toolBar.hide()
    }

    companion object {

        const val INTENT_EXTRA_VIEW_TYPE = "intent_extra_view_type"
        const val INTENT_EXTRA_TRANSACTION_DETAIL = "intent_extra_transaction_detail"

        fun start(context: Context?, viewType: ViewType) {
            context?.startActivity<ToolbarCommonActivity>(INTENT_EXTRA_VIEW_TYPE to viewType)
        }

    }
}