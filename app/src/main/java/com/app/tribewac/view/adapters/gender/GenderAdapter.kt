package com.app.tribewac.view.adapters.gender

import com.app.tribewac.R
import com.app.tribewac.base.BaseAdapter
import com.app.tribewac.data.models.selectgender.Gender
import com.app.tribewac.view.listeners.AdapterClickListener

class GenderAdapter(val listener: AdapterClickListener<Gender>) :
    BaseAdapter<Gender>() {
    override fun compare(oldList: Gender, newList: Gender): Boolean {
        return oldList.id == newList.id
    }

    override fun getAdapterListener(): AdapterClickListener<Gender> {
        return listener
    }

    override fun getLayoutIdForViewType(viewType: Int): Int {
        return R.layout.item_gender
    }
}