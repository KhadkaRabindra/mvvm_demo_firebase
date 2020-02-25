package com.maxx.nordvaest.ui.nav.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.maxx.nordvaest.R
import com.maxx.nordvaest.NorvaestApplication
import com.maxx.nordvaest.core.BaseAdapter
import com.maxx.nordvaest.data.local.NavigationItem

class NavigationDrawerListAdapter(private val clickCallback: ((NavigationItem) -> Unit)?) :
    BaseAdapter<NavigationItem>(object : DiffUtil.ItemCallback<NavigationItem>() {
        override fun areItemsTheSame(oldItem: NavigationItem, newItem: NavigationItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: NavigationItem, newItem: NavigationItem): Boolean {
            return oldItem == newItem
        }


    }) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val viewModel = NavigationDrawerItemViewModel(parent.context.applicationContext as NorvaestApplication)

        val binding = DataBindingUtil.inflate<com.maxx.nordvaest.databinding.RowNavigationBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_navigation,
            parent,
            false
        )
        binding.viewModel = viewModel
        binding.root.setOnClickListener {
            binding.viewModel?.let { clickCallback?.invoke(it.navigationItem) }
        }
        return binding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as com.maxx.nordvaest.databinding.RowNavigationBinding).viewModel?.setItem(getItem(position))
        binding.executePendingBindings()
    }
}
