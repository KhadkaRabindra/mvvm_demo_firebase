package com.maxx.nordvaest.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.maxx.nordvaest.NorvaestApplication
import com.maxx.nordvaest.R
import com.maxx.nordvaest.core.BaseAdapter
import com.maxx.nordvaest.data.local.HomeItem
import com.maxx.nordvaest.databinding.AdapterHomeBinding

class HomeAdapter(private val clickCallback: ((HomeItem) -> Unit?)) :
    BaseAdapter<HomeItem>(object : DiffUtil.ItemCallback<HomeItem>() {
        override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
            return oldItem.icon == newItem.icon
        }

        override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
            return oldItem == newItem
        }


    }) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val viewModel = HomeAdapterViewModel(parent.context.applicationContext as NorvaestApplication)
        val binding = DataBindingUtil.inflate<AdapterHomeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_home,
            parent,
            false
        )
        binding.viewModel = viewModel
        binding.root.setOnClickListener {
            binding.viewModel?.let { clickCallback?.invoke(it.homeData.get()!!) }
        }
        return binding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as AdapterHomeBinding).viewModel?.setHomeData(getItem(position))
        binding.executePendingBindings()
    }

}