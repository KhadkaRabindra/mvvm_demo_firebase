package com.maxx.nordvaest.ui.home

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Handler
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.maxx.nordvaest.R
import com.maxx.nordvaest.core.BaseFragment
import com.maxx.nordvaest.core.ToolbarCommonActivity
import com.maxx.nordvaest.data.local.HomeItem
import com.maxx.nordvaest.data.local.ViewType
import com.maxx.nordvaest.data.remote.Brukere
import com.maxx.nordvaest.ui.home.adapter.HomeAdapter
import com.maxx.nordvaest.ui.home.adapter.QRAdapter
import com.maxx.nordvaest.utils.Constants.Companion.KEY_SUCCESS
import com.maxx.nordvaest.utils.extensions.toast
import org.jetbrains.anko.support.v4.act


class HomeFragment : BaseFragment<HomeFragmentViewModel,
        com.maxx.nordvaest.databinding.FragmentHomeBinding>(HomeFragmentViewModel::class.java) {

    var homeIconsList = listOf<Int>(
        R.drawable.heart_fill, R.drawable.macciato,
        R.drawable.coffee_milk, R.drawable.americano,
        R.drawable.capuccino, R.drawable.free_coffee
    )

    var newSize: Int? = null
    var startCustomToast: Boolean? = false

    companion object {
        val CAMERA_PERMISSION_QR_SCAN = 2
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun initViewModel(viewModel: HomeFragmentViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun initView() {
        super.initView()

        if (activity is HomeActivity)
            (activity as HomeActivity).setToolbarTitle(getString(R.string.home))

        mBinding.scanIconImageView.setOnClickListener({
            checkCamerPermission()
        })

        observers()

    }

    private fun observers() {

        viewModel.qrDataList.observe(this, Observer {
            setUpHomeTypeRecyclerView(it)

        })
    }

    fun setUpHomeTypeRecyclerView(qrDataList: MutableList<Brukere?>?) {
        viewModel.setHomeData(getData(qrDataList?.size!!))
        val adapter = HomeAdapter {

        }
        val gridLayoutManager = GridLayoutManager(activity, 3)
        mBinding.recyclerView.layoutManager = gridLayoutManager as RecyclerView.LayoutManager?

        /*val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        mBinding.recyclerView.addItemDecoration(SpacesItemDecoration(spacingInPixels))*/
        mBinding.recyclerView.adapter = adapter

        if (startCustomToast!!) {
            showCustomToast("Godkjent stempel")
            if (newSize == 6) {
                Handler().postDelayed(Runnable {
                    showCustomToast("Gratis Kaffe!")
                }, 2000)
            }
            startCustomToast = false
        }

    }

    private fun checkCamerPermission() {
        if (ContextCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        )
            requestPermissions(
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_QR_SCAN
            )
        else {
            openQRScanView()
        }
    }

    private fun openQRScanView() {
        val intent = Intent(act, ToolbarCommonActivity::class.java)
        intent.putExtra(ToolbarCommonActivity.INTENT_EXTRA_VIEW_TYPE, ViewType.QR)
        startActivityForResult(intent, 111)
        //context?.startActivity<ToolbarCommonActivity>(ToolbarCommonActivity.INTENT_EXTRA_VIEW_TYPE to ViewType.QR)
    }

    fun getData(size: Int): ArrayList<HomeItem> {
        val mainData = ArrayList<HomeItem>()
        newSize = size
        /*if (size > 6)
            newSize = newSize % 7 + 1*/

        /*if (size >= 12) {
            //newSize = newSize % 7
            newSize = newSize % 6 + 1
        } else if (size > 6 && size <= 11) {
            newSize = newSize % 7 + 1
        }*/

        if (size > 6) {
            newSize = newSize!! % 6
            if (newSize == 0) {
                newSize = 6
            }
        }

        for (i in homeIconsList.indices) {
            var flag = false
            var showStarFlag = false
            if (newSize!! > i)
                flag = true
            if (i == 5)
                showStarFlag = true
            else
                showStarFlag = false
            mainData.add(HomeItem(homeIconsList[i], flag, showStarFlag, i))
        }
        return mainData
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            when (requestCode) {
                CAMERA_PERMISSION_QR_SCAN -> {
                    if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        openQRScanView()
                }
            }
        } else {
            toast("Please grant permissions to use this feature.")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 111) {
            if (resultCode == Activity.RESULT_OK) {
                val success = data?.getBooleanExtra(KEY_SUCCESS, false) as Boolean

                if (!success) {
                    MaterialDialog.Builder(context!!)
                        .content("Stempel er ikke godkjent")
                        .positiveText(getString(R.string.ok))
                        .positiveColor(ContextCompat.getColor(act, R.color.colorPrimary))
                        .onPositive { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                    startCustomToast = false
                } else {
                    startCustomToast = true
                }
            } else {
                startCustomToast = false
            }
        }
    }


    fun showCustomToast(message: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.toast_layout, null)
        val text = layout.findViewById(R.id.text) as TextView
        text.text = message

        val toast = Toast(act)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }


    override fun onResume() {
        super.onResume()

        /*QR Data*/
        viewModel.getFireBaseData()

        val adapter = QRAdapter {
        }

        mBinding.dataRecyclerView.adapter = adapter
    }


}