package com.antony.mybasepackage.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.antony.mybasepackage.BR
import com.antony.mybasepackage.view.listeners.AdapterClickListener

/**
 * Created By antony on 12/18/2019.
 */
abstract class BasePagedAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>) :
    PagedListAdapter<T, BasePagedAdapter.ViewHolder<T>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(
                inflater,
                getLayoutIdForViewType(viewType),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bind(getItem(position)!!, getAdapterListener())
    }

    class ViewHolder<T>(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: T, listener: AdapterClickListener<T>) {
            binding.setVariable(BR.item, obj)
            binding.setVariable(BR.listener, listener)
            binding.executePendingBindings()
        }
    }

    abstract fun getLayoutIdForViewType(viewType: Int): Int
    abstract fun getAdapterListener(): AdapterClickListener<T>
}