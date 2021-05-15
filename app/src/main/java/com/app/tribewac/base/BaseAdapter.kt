package com.app.tribewac.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.tribewac.BR
import com.app.tribewac.view.listeners.AdapterClickListener
import kotlin.properties.Delegates

abstract class BaseAdapter<T> :
    RecyclerView.Adapter<BaseAdapter<T>.ViewHolder>() {

    private var mLastClickTime = System.currentTimeMillis()

    private var items: List<T> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> compare(o, n); }
    }
    open val currentList
        get() = items

    fun submitList(newList: List<T>) {
        items = newList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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

    abstract fun compare(oldList: T, newList: T): Boolean

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    abstract fun getAdapterListener(): AdapterClickListener<T>

    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val now = System.currentTimeMillis()
                if (now - mLastClickTime < 500)
                    return@setOnClickListener
                mLastClickTime = now
                getAdapterListener().onItemClicked(items[adapterPosition], adapterPosition)
            }
            binding.setVariable(BR.listener, object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val now = System.currentTimeMillis()
                    if (now - mLastClickTime < 500)
                        return
                    mLastClickTime = now
                    getAdapterListener().onChildClicked(v, items[adapterPosition], adapterPosition)
                }
            })
        }

        fun bind(obj: T) {
            binding.setVariable(BR.item, obj)
            binding.executePendingBindings()
        }

        fun getBinding():ViewDataBinding{
            return binding
        }
    }

    abstract fun getLayoutIdForViewType(viewType: Int): Int

    private fun <T> autoNotify(oldList: List<T>, newList: List<T>, compare: (T, T) -> Boolean) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return compare(oldList[oldItemPosition], newList[newItemPosition])
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }

            override fun getOldListSize() = oldList.size
            override fun getNewListSize() = newList.size
        })
        diff.dispatchUpdatesTo(this)
    }
}