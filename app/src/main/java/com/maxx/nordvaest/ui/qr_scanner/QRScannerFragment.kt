package com.maxx.nordvaest.ui.qr_scanner

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.maxx.nordvaest.R
import com.maxx.nordvaest.core.BaseFragment
import com.maxx.nordvaest.core.ToolbarCommonActivity
import com.maxx.nordvaest.utils.Constants.Companion.KEY_SUCCESS
import org.jetbrains.anko.support.v4.act
import java.io.IOException


class QRScannerFragment : BaseFragment<QRScannerViewModel, com.maxx.nordvaest.databinding.FragmentQrScannerBinding>(
    QRScannerViewModel::class.java
) {

    var cameraView: SurfaceView? = null
    var barcode: BarcodeDetector? = null
    var cameraSource: CameraSource? = null
    var holder: SurfaceHolder? = null

    override fun initViewModel(viewModel: QRScannerViewModel) {
        mBinding.viewModel = viewModel
    }

    companion object {
        fun newInstance(): QRScannerFragment {
            return QRScannerFragment()
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_qr_scanner
    }

    override fun initView() {
        super.initView()

        if (activity is ToolbarCommonActivity)
            (activity as ToolbarCommonActivity).setToolbarTitle(getString(R.string.skann))


        cameraView = mBinding.cameraView
        cameraView?.setZOrderMediaOverlay(true)

        holder = cameraView?.holder
        barcode = BarcodeDetector.Builder(activity)
            .setBarcodeFormats(Barcode.QR_CODE)
            .build()

        if (!barcode!!.isOperational) {
            Toast.makeText(act, "Sorry, Couldn't setup the detector", Toast.LENGTH_LONG).show()
            this.finish()
        }

        cameraView?.holder?.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    if (ContextCompat.checkSelfPermission(
                            act, Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        cameraSource?.start(cameraView?.holder)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
            }
        })
        barcode?.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {

            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val barcodes = detections.detectedItems
                if (barcodes.size() > 0) {
                    barcode?.release()
                    vibrate()

                    val dataString = barcodes.valueAt(0).displayValue

                    if (dataString == "testjoseph12345")
                        mBinding.viewModel?.sendDataToFireBaseServer(dataString)
                    else {
                        val intent = Intent()
                        intent.putExtra(KEY_SUCCESS, false)
                        act.setResult(Activity.RESULT_OK, intent)
                        finish()
                    }
                }
                //cameraSource?.stop()
            }
        })

        observers()
    }

    private fun vibrate() {
        val v = act.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v!!.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            //deprecated in API 26
            v!!.vibrate(500)
        }
    }

    private fun observers() {

        viewModel.sendDataToFirebaseSuccess.observe(this, Observer {
            if (it)
                openHomeActivity()
        })
    }

    private fun openHomeActivity() {
        val intent = Intent()
        intent.putExtra(KEY_SUCCESS, true)
        act.setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onResume() {
        super.onResume()

        // Start camera on resume

        cameraSource = CameraSource.Builder(act, barcode)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .setRequestedFps(24f)
            .setAutoFocusEnabled(true)
            .setRequestedPreviewSize(320, 240)
            .build()
    }

    /*override fun onPause() {
        super.onPause()
        cameraSource?.stop()
    }*/

}