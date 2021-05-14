package com.antony.mybasepackage.view.adapters

import com.antony.mybasepackage.R
import com.antony.mybasepackage.base.BaseAdapter
import com.antony.mybasepackage.data.models.Post
import com.antony.mybasepackage.view.listeners.AdapterClickListener

/**
 * Created By antony on 6/3/2019.
 */
class PostListAdapter(val listener: AdapterClickListener<Post>) :
    BaseAdapter<Post>() {

    override fun getAdapterListener(): AdapterClickListener<Post> {
        return listener
    }

    override fun getLayoutIdForViewType(viewType: Int): Int {
        return R.layout.item_post
    }

    override fun compare(oldList: Post, newList: Post): Boolean {
        return oldList.id == newList.id
    }
}

