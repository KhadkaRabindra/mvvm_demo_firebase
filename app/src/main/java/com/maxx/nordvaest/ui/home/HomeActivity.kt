package com.maxx.nordvaest.ui.home

import android.app.FragmentManager
import android.content.Context
import android.os.Handler
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.maxx.nordvaest.NorvaestApplication
import com.maxx.nordvaest.R
import com.maxx.nordvaest.core.BaseActivity
import com.maxx.nordvaest.core.ToolbarCommonActivity
import com.maxx.nordvaest.data.listners.NavigationDrawerCallbacks
import com.maxx.nordvaest.data.local.ViewType
import com.maxx.nordvaest.databinding.ActivityHomeBinding
import com.maxx.nordvaest.repository.UserRepository
import com.maxx.nordvaest.ui.nav.NavigationDrawerFragment
import com.maxx.nordvaest.ui.profile.ProfileFragment
import com.maxx.nordvaest.utils.extensions.setSafeOnClickListener
import com.maxx.nordvaest.utils.extensions.toast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar_home.*
import kotlinx.android.synthetic.main.toolbar_home.view.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeActivityViewModel, ActivityHomeBinding>(
    HomeActivityViewModel::class.java
), NavigationDrawerCallbacks {

    var selectedDrawerPosition: Int? = 0
    var prevSelectedDrawerPosition: Int? = 0
    var doubleBackToExitPressedOnce = false
    val EXIT_DELAY: Long = 2000

    @Inject
    lateinit var userRepository: UserRepository

    override fun getLayoutRes() = R.layout.activity_home

    override fun initViewModel(viewModel: HomeActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun initView() {
        super.initView()
        (application as? NorvaestApplication)?.component?.inject(this)
        changeFragment(HomeFragment.newInstance(), addToBackStack = false)

        binding.customToolbar.navIconImageVIew.setSafeOnClickListener {
            openDrawer()
        }
        binding.customToolbar.settingsImageView.setSafeOnClickListener {
            openNavigateBackBaseActivity(ViewType.SETTINGS)
        }

        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun setToolbarTitle(title: String) {
        binding.customToolbar.toolbarTitle.text = title
    }


    override fun onResume() {
        super.onResume()
        setupNavigationDrawerView()
    }


    /**
     * intialize navigation drawer layout and fragment
     */
    private fun setupNavigationDrawerView() {
        val frag = NavigationDrawerFragment()
        val ft = supportFragmentManager?.beginTransaction()
        ft?.replace(R.id.fragment_drawer, frag)
        ft?.commitAllowingStateLoss()
        closeDrawer()
    }

    /**
     * opens drawer layout
     */
    private fun openDrawer() {
        if (!drawerLayout.isDrawerOpen(fragment_drawer))
            drawerLayout.openDrawer(fragment_drawer)
    }

    /**
     * closes drawer layout
     */
    private fun closeDrawer() {
        if (drawerLayout.isDrawerOpen(fragment_drawer))
            drawerLayout.closeDrawer(fragment_drawer)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_drawer -> {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }*/

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else if (selectedDrawerPosition != 0) {
            changeFragment(HomeFragment.newInstance(), addToBackStack = false)
            setSelectedNavMenu(0)
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true
            toast(getString(R.string.double_press_exit_string))
            Handler().postDelayed(object : Runnable {
                override fun run() {
                    doubleBackToExitPressedOnce = false
                }
            }, EXIT_DELAY)
        } else {
            super.onBackPressed()
            if (fragmentManager.backStackEntryCount == 1)
                finish()
            else {
                selectedDrawerPosition = 0
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }

        }
    }

    /**
     * for setting nav menu selected in given position
     */
    fun setSelectedNavMenu(position: Int?) {
        selectedDrawerPosition = position   //set selected position
    }

    override fun onNavigationDrawerItemSelected(position: Int?) {
        if (!checkIfSameMenuSelected(position)) {
            prevSelectedDrawerPosition = selectedDrawerPosition
            selectedDrawerPosition = position
            when (position) {
                NavigationDrawerFragment.NavigationIds.Home -> openNavigateBackBaseActivity(ViewType.HOME)
                NavigationDrawerFragment.NavigationIds.Profile -> openNavigateBackBaseActivity(ViewType.PROFILE)
                NavigationDrawerFragment.NavigationIds.PrefundHistory -> openNavigateBackBaseActivity(ViewType.PREFUND_HISTORY)
                NavigationDrawerFragment.NavigationIds.CashInHostory -> openNavigateBackBaseActivity(ViewType.CASH_IN_HISTORY)
                NavigationDrawerFragment.NavigationIds.Statements -> openNavigateBackBaseActivity(ViewType.STATEMENTS)
                NavigationDrawerFragment.NavigationIds.Settings -> openNavigateBackBaseActivity(ViewType.SETTINGS)
                NavigationDrawerFragment.NavigationIds.ContactUs -> openNavigateBackBaseActivity(ViewType.CONTACT_US)
                NavigationDrawerFragment.NavigationIds.Logout -> {
                    userRepository.doLogout(this)
                }
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    /**
     * check if same menu selected or not
     */
    fun checkIfSameMenuSelected(position: Int?): Boolean {
        return selectedDrawerPosition == position
    }

    private fun openNavigateBackBaseActivity(viewType: ViewType) {
        when (viewType) {
            ViewType.HOME -> changeFragment(HomeFragment.newInstance(), addToBackStack = false)
            ViewType.PROFILE -> changeFragment(ProfileFragment.newInstance(), addToBackStack = false)
            ViewType.PREFUND_HISTORY -> startActivity<ToolbarCommonActivity>(ToolbarCommonActivity.INTENT_EXTRA_VIEW_TYPE to ViewType.PREFUND_HISTORY)
            ViewType.STATEMENTS -> startActivity<ToolbarCommonActivity>(ToolbarCommonActivity.INTENT_EXTRA_VIEW_TYPE to ViewType.STATEMENTS)
            ViewType.SETTINGS -> startActivity<ToolbarCommonActivity>(ToolbarCommonActivity.INTENT_EXTRA_VIEW_TYPE to ViewType.SETTINGS)
            else -> {

            }
        }
    }


    companion object {
        fun start(context: Context?) {
            context?.startActivity<HomeActivity>()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
