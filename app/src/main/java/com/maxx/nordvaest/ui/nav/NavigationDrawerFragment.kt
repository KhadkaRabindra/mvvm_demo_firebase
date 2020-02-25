package com.maxx.nordvaest.ui.nav

import android.content.Context
import com.maxx.nordvaest.R
import com.maxx.nordvaest.core.BaseFragment
import com.maxx.nordvaest.data.listners.NavigationDrawerCallbacks
import com.maxx.nordvaest.data.local.NavigationItem
import com.maxx.nordvaest.ui.nav.adapter.NavigationDrawerListAdapter
import com.maxx.nordvaest.utils.extensions.logD
import com.maxx.nordvaest.utils.extensions.setSafeOnClickListener

class NavigationDrawerFragment : BaseFragment<NavigationDrawerFragmentViewModel,
        com.maxx.nordvaest.databinding.FragmentNavigationDrawerBinding>(NavigationDrawerFragmentViewModel::class.java) {
    override fun getLayoutRes() = R.layout.fragment_navigation_drawer

    override fun initViewModel(viewModel: NavigationDrawerFragmentViewModel) {
        mBinding.viewModel = viewModel
    }

    private var mCallbacks: NavigationDrawerCallbacks? = null

    override fun initView() {
        super.initView()

        mBinding.logoutButton.setSafeOnClickListener {
            mCallbacks?.onNavigationDrawerItemSelected(NavigationIds.Logout)
        }
        /*mBinding.llProfile.setSafeOnClickListener {
            mCallbacks?.onNavigationDrawerItemSelected(NavigationIds.Profile)
        }*/

        viewModel.setNavigationList(menus)
        viewModel.setFullNameAndUserName()
        mBinding.recyclerView.adapter = NavigationDrawerListAdapter {
            mCallbacks!!.onNavigationDrawerItemSelected(it.id)
        }
    }


    private val menus: ArrayList<NavigationItem>
        get() {
            val items = ArrayList<NavigationItem>()
            items.add(NavigationItem(NavigationIds.Home, getString(R.string.home), R.drawable.ic_home))
            items.add(NavigationItem(NavigationIds.Profile, getString(R.string.profile), R.drawable.ic_profile))
            /*items.add(NavigationItem(NavigationIds.PrefundHistory, getString(R.string.home), R.drawable.ic_file_download_black_24dp))
            items.add(NavigationItem(NavigationIds.CashInHostory, getString(R.string.home), R.drawable.ic_file_download_black_24dp))
            items.add(NavigationItem(NavigationIds.Statements, getString(R.string.home), R.drawable.ic_file_download_black_24dp))
            items.add(NavigationItem(NavigationIds.Settings, getString(R.string.home), R.drawable.ic_file_download_black_24dp))
            items.add(NavigationItem(NavigationIds.ContactUs, getString(R.string.home), R.drawable.ic_file_download_black_24dp))*/
            return items
        }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
        try {
            mCallbacks = mContext as NavigationDrawerCallbacks?
        } catch (ex: Exception) {
            logD("error here : ${ex.localizedMessage}")
            ex.printStackTrace()
        }
    }

    object NavigationIds {
        const val Home: Int = 0
        const val Profile: Int = 1

        const val PrefundHistory: Int = 2
        const val CashInHostory: Int = 3
        const val Statements: Int = 4
        const val Settings: Int = 5
        const val ContactUs: Int = 6
        const val Logout: Int = 7
    }
}