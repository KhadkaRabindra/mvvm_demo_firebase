package com.maxx.nordvaest.core

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxx.nordvaest.BuildConfig
import com.maxx.nordvaest.R
import com.maxx.nordvaest.data.local.HomeItem
import com.maxx.nordvaest.utils.extensions.setHintSpinnerAdapter
import com.squareup.picasso.Picasso
import fr.ganfra.materialspinner.MaterialSpinner


object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:set_list")
    fun setList(recyclerView: RecyclerView?, list: List<Nothing>?) {
        val adapter: BaseAdapter<*>? = recyclerView?.adapter as BaseAdapter<*>?
        list?.let {
            adapter?.submitList(null)
            adapter?.submitList(list)
        }
    }

    @JvmStatic
    @BindingAdapter("app:set_list")
    fun setList(spinner: MaterialSpinner, list: List<String>?) {
        list?.let {
            spinner.setHintSpinnerAdapter(list)
        }
    }

    @JvmStatic
    @BindingAdapter("app:setImgResource")
    fun setImgResource(imageView: ImageView?, resourceId: Int?) {
        if (resourceId != null)
            imageView?.setImageResource(resourceId)
        else imageView?.setImageResource(0)
    }

    @JvmStatic
    @BindingAdapter("app:setImgResourceWithFlag")
    fun setImgResourceWithFlag(imageView: ImageView?, homeData: HomeItem?) {
        if (homeData != null && homeData.showIcon) {
            imageView?.visibility = View.VISIBLE
            imageView?.setImageResource(homeData.icon)
        } else {
            //imageView?.setImageResource(0)
            imageView?.visibility = View.INVISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("app:setImgResourceWithFlagForStar")
    fun setImgResourceWithFlagForStar(imageView: ImageView?, homeData: HomeItem?) {
        if (homeData != null && homeData.position == 5) {
            if (homeData.showStarFlag && !homeData.showIcon) {
                imageView?.visibility = View.VISIBLE
            } else {
                //imageView?.setImageResource(0)
                imageView?.visibility = View.INVISIBLE
            }
        }
    }

    @JvmStatic
    @BindingAdapter("app:setImgResource")
    fun setImgResource(imageView: ImageView?, imageUrl: String?) {
        //val imageUrl = bankDetail.iconurl
        if (imageUrl?.isNullOrEmpty() == true)
            return
        val picasso = Picasso.Builder(imageView?.context!!)
            .listener { _, _, e ->
                if (BuildConfig.DEBUG)
                    e.printStackTrace()
            }
            .build()
        picasso.load(imageUrl)
            .into(imageView)

        Log.v("TAG", "testing image: $imageUrl")
    }

}




