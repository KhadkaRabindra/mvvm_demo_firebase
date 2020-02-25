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
import com.maxx.nordvaest.data.remote.Brukere
import com.maxx.nordvaest.databinding.AdapterQrDataBinding

class QRAdapter(private val clickCallback: ((HomeItem) -> Unit?)) :
    BaseAdapter<Brukere>(object : DiffUtil.ItemCallback<Brukere>() {
        override fun areItemsTheSame(oldItem: Brukere, newItem: Brukere): Boolean {
            return oldItem.StempelDato == newItem.StempelDato
        }

        override fun areContentsTheSame(oldItem: Brukere, newItem: Brukere): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val viewModel = QRAdapterViewModel(parent.context.applicationContext as NorvaestApplication)
        val binding = DataBindingUtil.inflate<com.maxx.nordvaest.databinding.AdapterQrDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_qr_data,
            parent,
            false
        )
        binding.viewModel = viewModel
        binding.root.setOnClickListener {
            //binding.viewModel?.let { clickCallback?.invoke(it) }
        }
        return binding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as AdapterQrDataBinding).viewModel?.setQRData(getItem(position))
        binding.executePendingBindings()
    }
}